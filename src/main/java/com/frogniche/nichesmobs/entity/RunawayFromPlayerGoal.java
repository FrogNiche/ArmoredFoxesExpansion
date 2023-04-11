package com.frogniche.nichesmobs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.util.RandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class RunawayFromPlayerGoal extends Goal {

    protected final double distFromPlayer;
    protected final double avgRunDistance, speed;
    protected final PathfinderMob owner;
    protected Player inFear;

    /**
     *
     * @param distFromPlayer the distance , when a player is to near it will run away
     * @param avgRunDistance the average distance that the entity will run away from the player, this is clamped between 10 and 100
     */
    public RunawayFromPlayerGoal(PathfinderMob owner, double distFromPlayer, double avgRunDistance, double speed) {
        this.distFromPlayer = distFromPlayer;
        this.avgRunDistance = Mth.clamp(avgRunDistance, 10, 100);
        this.owner = owner;
        this.speed = speed;
    }

    @Override
    public boolean canUse() {
        this.inFear = owner.level.getNearestPlayer(owner, distFromPlayer);
        return inFear != null;
    }

    @Override
    public void start() {
        Vec3 dist = new Vec3(inFear.getX(), inFear.getY(), inFear.getZ()).subtract(new Vec3(owner.getX(), owner.getY(), owner.getZ())).normalize().scale(avgRunDistance);
        Vec3 pos = new Vec3(owner.getX(), owner.getY(), owner.getZ()).add(dist);
        BlockPos randPos = RandomPos.generateRandomPosTowardDirection(owner, 10, owner.getRandom(), new BlockPos(pos));
        owner.getNavigation().moveTo(randPos.getX(), randPos.getY(), randPos.getZ(), speed);
    }
}
