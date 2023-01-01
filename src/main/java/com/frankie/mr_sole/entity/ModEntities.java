package com.frankie.mr_sole.entity;

import com.frankie.mr_sole.MrSole;
import com.frankie.mr_sole.entity.custom.SoleEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<SoleEntity> SOLE = registerMob("sole", SoleEntity::new, 0.7f, 0.2f, SpawnGroup.WATER_CREATURE);

    public static <T extends MobEntity> EntityType<T> registerMob(String name, EntityType.EntityFactory<T> entity, float width, float height, SpawnGroup spawnGroup) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(MrSole.MOD_ID, name), FabricEntityTypeBuilder.create(spawnGroup, entity).dimensions(EntityDimensions.changing(width, height)).build());
    }

    public static void init() {
    }
}
