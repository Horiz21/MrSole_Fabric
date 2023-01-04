package com.frankie.mr_sole.entity.client;

import com.frankie.mr_sole.entity.custom.MoleEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

public class MoleRenderer extends DynamicGeoEntityRenderer<MoleEntity> {
    public MoleRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new MoleModel());
        this.shadowRadius = 0.4f;
    }
}
