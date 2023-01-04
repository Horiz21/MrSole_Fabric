package com.frankie.mr_sole.entity.client;

import com.frankie.mr_sole.MrSole;
import com.frankie.mr_sole.entity.custom.MoleEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class MoleModel extends DefaultedEntityGeoModel<MoleEntity> {
    public MoleModel() {
        super(new Identifier(MrSole.MOD_ID, "mole"), true);
    }
}
