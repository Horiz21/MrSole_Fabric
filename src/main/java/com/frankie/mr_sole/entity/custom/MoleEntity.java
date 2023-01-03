package com.frankie.mr_sole.entity.custom;

import com.frankie.mr_sole.entity.ModEntities;
import com.frankie.mr_sole.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MoleEntity extends AnimalEntity implements GeoEntity {

    // 繁殖用的食物：甜菜根和胡萝卜（都是根）
    private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(
            new ItemConvertible[]{Items.BEETROOT, Items.CARROT});
    // 动画相关
    private static final RawAnimation ANIMATION_MOLE_IDLE = RawAnimation.begin().thenPlay("animation.sole.idle");
    private static final RawAnimation ANIMATION_MOLE_SWIM = RawAnimation.begin().thenPlay("animation.sole.swim");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // 构造方法
    public MoleEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    // 非常重要：设置基础属性（血量和移动速度）
    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f);
    }

    protected void initGoals() {
        this.goalSelector.add(0, new AvoidSunlightGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.0, BREEDING_INGREDIENT, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 10, state -> state.setAndContinue(this.getMovementSpeed() > 1f ? ANIMATION_MOLE_WALK : ANIMATION_MOLE_IDLE)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public void tickMovement() {
        super.tickMovement();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_MOLE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_MOLE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_MOLE_HURT;
    }

    @Nullable
    @Override
    public MoleEntity createChild(ServerWorld world, PassiveEntity entity) {
        return (MoleEntity) ModEntities.MOLE.create(world);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

}