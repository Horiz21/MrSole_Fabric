package com.frankie.mr_sole.entity.client;

import com.frankie.mr_sole.entity.custom.SoleEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SoleRenderer extends GeoEntityRenderer<SoleEntity> {
    public SoleRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SoleModel());
        this.shadowRadius = 0.3f;
    }
}
