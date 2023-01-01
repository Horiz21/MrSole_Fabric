package com.frankie.mr_sole.world.gen;

import com.frankie.mr_sole.entity.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawn {
    public static void addEntitySpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.WARM_OCEAN), SpawnGroup.WATER_CREATURE, ModEntities.SOLE, 50, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.LUKEWARM_OCEAN), SpawnGroup.WATER_CREATURE, ModEntities.SOLE, 50, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DEEP_LUKEWARM_OCEAN), SpawnGroup.WATER_CREATURE, ModEntities.SOLE, 40, 1, 4);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN), SpawnGroup.WATER_CREATURE, ModEntities.SOLE, 30, 1, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DEEP_OCEAN), SpawnGroup.WATER_CREATURE, ModEntities.SOLE, 20, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.COLD_OCEAN), SpawnGroup.WATER_CREATURE, ModEntities.SOLE, 20, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DEEP_COLD_OCEAN), SpawnGroup.WATER_CREATURE, ModEntities.SOLE, 10, 1, 1);
        SpawnRestriction.register(ModEntities.SOLE, SpawnRestriction.Location.IN_WATER, Heightmap.Type.OCEAN_FLOOR, FishEntity::canMobSpawn);
    }
}