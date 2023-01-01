package com.frankie.mr_sole.entity.client;

import com.frankie.mr_sole.MrSole;
import com.frankie.mr_sole.entity.custom.SoleEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SoleRenderer extends GeoEntityRenderer<SoleEntity> {
    public SoleRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SoleModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(SoleEntity instance) {
        return new Identifier(MrSole.MOD_ID, "textures/entity/sole.png");
    }
}
