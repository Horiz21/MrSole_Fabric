package com.frankie.mr_sole.entity.custom;

import com.frankie.mr_sole.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;

import static java.lang.Math.random;

public class MinerMoleEntity extends MoleEntity implements GeoEntity {
    // 构造方法
    public MinerMoleEntity(EntityType<? extends MoleEntity> type, World world) {
        super(type, world);
    }

    // 非常重要：设置基础属性（血量和移动速度）
    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f);
    }

    @Override
    public MoleEntity createChild(ServerWorld world, PassiveEntity entity) {
        if (entity instanceof MoleEntity && random() < 0.8f)
            return (MoleEntity) ModEntities.MOLE.create(world);
        else
            return (MinerMoleEntity) ModEntities.MINER_MOLE.create(world);
    }
}