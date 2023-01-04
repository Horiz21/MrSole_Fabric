package com.frankie.mr_sole.entity.client;

import com.frankie.mr_sole.MrSole;
import com.frankie.mr_sole.entity.custom.SoleEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SoleModel extends DefaultedEntityGeoModel<SoleEntity> {
    public SoleModel() {
        super(new Identifier(MrSole.MOD_ID, "sole"));
    }
}
