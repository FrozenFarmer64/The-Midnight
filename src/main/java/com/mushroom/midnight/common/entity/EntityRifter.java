package com.mushroom.midnight.common.entity;

import com.mushroom.midnight.common.entity.task.EntityTaskRifterCapture;
import com.mushroom.midnight.common.entity.task.EntityTaskRifterMelee;
import com.mushroom.midnight.common.entity.task.EntityTaskRifterTransport;
import com.mushroom.midnight.common.registry.ModDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;

public class EntityRifter extends EntityMob {
    private static final double RIFT_SEARCH_RADIUS = 48.0;

    private final EntityReference<EntityRift> homeRift;

    public EntityRifter(World world) {
        super(world);
        this.moveHelper = new HookedMoveHelper(this);

        this.homeRift = new EntityReference<>(world);
    }

    @Override
    protected PathNavigate createNavigator(World world) {
        return new HookedNavigator(this, world);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityTaskRifterTransport(this, 1.0));
        this.tasks.addTask(2, new EntityTaskRifterCapture(this, 1.0));
        this.tasks.addTask(3, new EntityTaskRifterMelee(this, 1.0));

        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));

        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityLivingBase.class, 2, true, false, e -> {
            if (e == null || e.isRiding()) {
                return false;
            }
            return !(e instanceof EntityRifter);
        }));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0);
    }

    @Override
    public void onLivingUpdate() {
        if (!this.world.isRemote) {
            this.checkBurn();

            if (this.world.provider.getDimensionType() == DimensionType.OVERWORLD) {
                this.updateHomeRift();
            }
        }

        super.onLivingUpdate();
    }

    public boolean shouldCapture() {
        if (this.world.isDaytime() || this.world.provider.getDimensionType() == ModDimensions.MIDNIGHT) {
            return false;
        }
        return this.homeRift.isPresent();
    }

    private void updateHomeRift() {
        if (this.ticksExisted % 20 == 0 && !this.homeRift.isPresent()) {
            AxisAlignedBB searchBounds = this.getEntityBoundingBox().grow(RIFT_SEARCH_RADIUS);
            List<EntityRift> rifts = this.world.getEntitiesWithinAABB(EntityRift.class, searchBounds);
            if (!rifts.isEmpty()) {
                rifts.sort(Comparator.comparingDouble(this::getDistanceSq));
                this.homeRift.set(rifts.get(0));
            }
        }
    }

    private void checkBurn() {
        if (this.world.isDaytime()) {
            float brightness = this.getBrightness();
            if (brightness > 0.5F && this.rand.nextFloat() * 30.0F < (brightness - 0.4F) * 2.0F) {
                if (!this.world.canSeeSky(this.getPosition())) {
                    return;
                }
                this.setFire(8);
                this.dropCaptured();
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (super.attackEntityFrom(source, amount)) {
            this.dropCaptured();
            return true;
        }
        return false;
    }

    public void dropCaptured() {
        for (Entity passenger : this.getPassengers()) {
            passenger.dismountRidingEntity();
        }
    }

    public EntityReference<EntityRift> getHomeRift() {
        return this.homeRift;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setTag("home_rift", this.homeRift.serialize(new NBTTagCompound()));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.homeRift.deserialize(compound.getCompoundTag("home_rift"));
    }

    protected static boolean isCallingFromRider() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length <= 3) {
            return false;
        }
        StackTraceElement caller = stackTrace[3];
        if (caller.getClassName().equals(EntityLiving.class.getName())) {
            String methodName = caller.getMethodName();
            return methodName.equals("updateEntityActionState") || methodName.equals("func_70626_be");
        }
        return false;
    }

    private static class HookedNavigator extends PathNavigateGround {
        HookedNavigator(EntityLiving entity, World world) {
            super(entity, world);
        }

        @Override
        public boolean setPath(@Nullable Path path, double speed) {
            if (isCallingFromRider()) {
                return false;
            }
            return super.setPath(path, speed);
        }
    }

    private static class HookedMoveHelper extends EntityMoveHelper {
        HookedMoveHelper(EntityLiving entity) {
            super(entity);
        }

        @Override
        public void read(EntityMoveHelper that) {
            if (isCallingFromRider()) {
                return;
            }
            super.read(that);
        }
    }
}
