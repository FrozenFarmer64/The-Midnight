package com.mushroom.midnight.common.entity.task;

import com.mushroom.midnight.common.entity.creature.RifterEntity;
import com.mushroom.midnight.common.util.MidnightUtil;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class RifterReturnGoal extends Goal {
    protected static final int RETURN_EARLY_TIME = 80;
    protected static final int INVALIDATE_TIME = 20;

    protected final RifterEntity owner;
    protected final double returnSpeed;

    protected int invalidCount = 0;
    protected Path path;

    public RifterReturnGoal(RifterEntity owner, double returnSpeed) {
        this.owner = owner;
        this.returnSpeed = returnSpeed;

        this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    @Override
    public boolean shouldExecute() {
        return this.shouldReturn();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.shouldReturn() && invalidCount <= INVALIDATE_TIME;
    }

    public boolean shouldReturn() {
        if (MidnightUtil.isMidnightDimension(this.owner.world)) {
            return false;
        }
        return this.owner.spawnedThroughRift && this.owner.getRiftPosition() != null && this.owner.getCapturedEntity() != null && !this.owner.world.isDaytime();
    }

    @Override
    public void startExecuting() {
        this.path = this.computeFollowPath();
        this.owner.getNavigator().setPath(this.path, this.returnSpeed);
    }

    @Override
    public void tick() {
        Path targetPath = this.computeFollowPath();
        if (targetPath == null) {
            this.path = null;
            return;
        }

        Path currentPath = this.owner.getNavigator().getPath();
        if (currentPath != targetPath) {
            this.owner.getNavigator().setPath(targetPath, this.returnSpeed);
        }
        invalidCount = this.path == null ? invalidCount++ : 0;
    }

    @Nullable
    private Path computeFollowPath() {
        if (this.isPathComplete(this.path)) {
            this.path = null;
            this.invalidCount = 0;
        }

        if (this.path == null) {
            this.owner.getNavigator().clearPath();

            if (this.owner.getRiftPosition() == null) {
                return null;
            }

            this.path = this.owner.getNavigator().getPathToPos(this.owner.getRiftPosition(), 0);

            if (this.path == null) {
                this.path = this.computePathTowards(this.owner.getRiftPosition());
            }
        }
        return this.path;
    }

    @Nullable
    private Path computePathTowards(BlockPos surface) {
        Vec3d target = new Vec3d(surface);
        for (int i = 0; i < 16; i++) {
            Vec3d pathPos = RandomPositionGenerator.findRandomTargetBlockTowards(this.owner, 24, 4, target);
            if (pathPos == null) {
                continue;
            }
            Path path = this.owner.getNavigator().getPathToPos(pathPos.x, pathPos.y, pathPos.z, 0);
            if (path != null) {
                return path;
            }
        }
        return null;
    }

    private boolean isPathComplete(Path path) {
        if (path == null || path.isFinished()) {
            return true;
        }
        PathPoint finalPoint = path.getFinalPathPoint();
        return finalPoint == null || this.owner.getDistanceSq(finalPoint.x, finalPoint.y, finalPoint.z) < 1.5 * 1.5;
    }

    @Override
    public void resetTask() {
        this.owner.getNavigator().clearPath();

        this.path = null;
        this.invalidCount = 0;
    }
}