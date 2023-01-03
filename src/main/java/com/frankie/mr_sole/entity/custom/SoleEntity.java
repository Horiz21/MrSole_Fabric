package com.frankie.mr_sole.entity.custom;

import com.frankie.mr_sole.item.ModItems;
import com.frankie.mr_sole.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Objects;
import java.util.function.Predicate;

public class SoleEntity extends FishEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // 构造方法
    public SoleEntity(EntityType<? extends FishEntity> type, World world) {
        super(type, world);
        this.moveControl = new SoleMoveControl(this);
    }

    private static final RawAnimation ANIMATION_SOLE_IDLE = RawAnimation.begin().thenPlay("animation.sole.idle");
    private static final RawAnimation ANIMATION_SOLE_SWIM = RawAnimation.begin().thenPlay("animation.sole.swim");

    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(this, 10, state -> state.setAndContinue(this.getMovementSpeed() > 0.3f ? ANIMATION_SOLE_SWIM : ANIMATION_SOLE_IDLE))
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.SOLE_BUCKET);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_SOLE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_SOLE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_SOLE_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return ModSounds.ENTITY_SOLE_FLOP;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        // 逃跑目标
        this.goalSelector.add(0, new EscapeDangerGoal(this, 1.25));
        Predicate var10009 = EntityPredicates.EXCEPT_SPECTATOR;
        Objects.requireNonNull(var10009);
        this.goalSelector.add(2, new FleeEntityGoal(this, PlayerEntity.class, 4.0F, 0.8, 0.7, var10009::test));
        // 随便游泳目标
        this.goalSelector.add(4, new SoleSwimToRandomPlaceGoal(this));
    }

    @Override
    public void tickMovement() {
        //跳出水面就蹦跶，但是比普通鱼类蹦跶得慢一点
        if (!this.isTouchingWater() && this.onGround && this.verticalCollision) {
            this.setVelocity(this.getVelocity().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.025F, 0.3f, (this.random.nextFloat() * 2.0F - 1.0F) * 0.025F));
            this.onGround = false;
            this.velocityDirty = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getSoundPitch());
        }

        super.tickMovement();
    }

    static class SoleMoveControl extends MoveControl {
        private final SoleEntity sole;

        SoleMoveControl(SoleEntity owner) {
            super(owner);
            this.sole = owner;
        }

        public void tick() {
            if (this.sole.isSubmergedIn(FluidTags.WATER)) {
                this.sole.setVelocity(this.sole.getVelocity().add(0.0, 0.001, 0.0));
            }

            if (this.state == State.MOVE_TO && !this.sole.getNavigation().isIdle()) {
                float f = (float) (this.speed * this.sole.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                this.sole.setMovementSpeed(MathHelper.lerp(0.125F, this.sole.getMovementSpeed(), f));
                double d = this.targetX - this.sole.getX();
                double e = this.targetY - this.sole.getY();
                double g = this.targetZ - this.sole.getZ();
                if (e != 0.0) {
                    double h = Math.sqrt(d * d + e * e + g * g);
                    this.sole.setVelocity(this.sole.getVelocity().add(0.0, (double) this.sole.getMovementSpeed() * (e / h) * 0.1, 0.0));
                }

                if (d != 0.0 || g != 0.0) {
                    float i = (float) (MathHelper.atan2(g, d) * 57.2957763671875) - 90.0F;
                    this.sole.setYaw(this.wrapDegrees(this.sole.getYaw(), i, 90.0F));
                    this.sole.bodyYaw = this.sole.getYaw();
                }

            } else {
                this.sole.setMovementSpeed(0.0F);
            }
        }
    }

    static class SoleSwimToRandomPlaceGoal extends SwimAroundGoal {
        private final SoleEntity sole;

        public SoleSwimToRandomPlaceGoal(SoleEntity sole) {
            super(sole, 1, 10);
            this.sole = sole;
        }

        @Override
        @Nullable
        protected Vec3d getWanderTarget() {
            return LookTargetUtil.find(this.mob, 5, 1);
        }

        public boolean canStart() {
            return this.sole.hasSelfControl() && super.canStart();
        }
    }
}