package com.frankie.mr_sole;

import com.frankie.mr_sole.entity.ModEntities;
import com.frankie.mr_sole.entity.client.MoleRenderer;
import com.frankie.mr_sole.entity.client.SoleRenderer;
import com.frankie.mr_sole.entity.custom.MoleEntity;
import com.frankie.mr_sole.entity.custom.SoleEntity;
import com.frankie.mr_sole.item.ModItems;
import com.frankie.mr_sole.world.gen.ModEntitySpawn;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import software.bernie.geckolib.GeckoLib;

public class MrSoleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GeckoLib.initialize();
        ModItems.init();
        ModEntities.init();
        ModEntitySpawn.addEntitySpawn();
        FabricDefaultAttributeRegistry.register(ModEntities.SOLE, SoleEntity.setAttributes());
        EntityRendererRegistry.register(ModEntities.SOLE, SoleRenderer::new);
        FabricDefaultAttributeRegistry.register(ModEntities.MOLE, MoleEntity.setAttributes());
        EntityRendererRegistry.register(ModEntities.MOLE, MoleRenderer::new);
    }
}