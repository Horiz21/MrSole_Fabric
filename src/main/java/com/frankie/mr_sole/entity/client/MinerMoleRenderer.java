package com.frankie.mr_sole.entity.client;

import com.frankie.mr_sole.entity.custom.MinerMoleEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

public class MinerMoleRenderer extends DynamicGeoEntityRenderer<MinerMoleEntity> {
    public MinerMoleRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new MinerMoleModel());
        this.shadowRadius = 0.4f;
    }
}
