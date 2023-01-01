package com.frankie.mr_sole.entity.client;

import com.frankie.mr_sole.MrSole;
import com.frankie.mr_sole.entity.custom.SoleEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SoleModel extends DefaultedEntityGeoModel<SoleEntity> {
    public SoleModel() {
        super(new Identifier(MrSole.MOD_ID, "sole"));
    }

    @Override
    public Identifier getModelResource(SoleEntity animatable) {
        return new Identifier(MrSole.MOD_ID, "geo/sole.geo.json");
    }

    @Override
    public Identifier getTextureResource(SoleEntity animatable) {
        return new Identifier(MrSole.MOD_ID, "textures/entity/sole.png");
    }

    @Override
    public Identifier getAnimationResource(SoleEntity animatable) {
        return new Identifier(MrSole.MOD_ID, "animations/sole.animation.json");
    }
}
