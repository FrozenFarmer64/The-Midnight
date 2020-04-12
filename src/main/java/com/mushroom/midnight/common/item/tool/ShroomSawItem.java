package com.mushroom.midnight.common.item.tool;

import com.mushroom.midnight.common.registry.MidnightSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ShroomSawItem extends Item {
    public ShroomSawItem(Properties properties) {
        super(properties);
        this.addPropertyOverride(new ResourceLocation("working"), (p_210309_0_, p_210309_1_, p_210309_2_) -> {
            return p_210309_2_ != null && p_210309_2_.isHandActive() && p_210309_2_.getActiveItemStack() == p_210309_0_ ? 1.0F : 0.0F;
        });
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity livingEntity, int count) {
        Vec3d vec3d = livingEntity.getLook(1.0F);

        for (LivingEntity livingentity2 : livingEntity.world.getEntitiesWithinAABB(LivingEntity.class, livingEntity.getBoundingBox().grow(0.35D, -livingEntity.getHeight() / 2, 0.35D).offset(vec3d.x * 1.65D, vec3d.y * 1.65D, vec3d.z * 1.65D))) {
            if (livingentity2 != livingEntity && (livingEntity.getControllingPassenger() == null || livingEntity.getControllingPassenger() != null && livingentity2 != livingEntity.getControllingPassenger()) && !livingEntity.isOnSameTeam(livingentity2) && (!(livingentity2 instanceof ArmorStandEntity) || !((ArmorStandEntity) livingentity2).hasMarker())) {
                if (livingentity2.attackEntityFrom(DamageSource.causeMobDamage(livingEntity), 4.0F)) {
                    stack.damageItem(1, livingEntity, (p_220045_0_) -> {
                        p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
                    });
                }
                livingEntity.playSound(MidnightSounds.BLADESHROOM_CAP_HIT, 0.8F, 1.0F + livingEntity.getRNG().nextFloat() * 0.1F);
            }
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemstack);
    }
}
