package com.frankie.mr_sole.entity.client;

import com.frankie.mr_sole.MrSole;
import com.frankie.mr_sole.entity.custom.MinerMoleEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class MinerMoleModel extends DefaultedEntityGeoModel<MinerMoleEntity> {
    public MinerMoleModel() {
        super(new Identifier(MrSole.MOD_ID, "miner_mole"), true);
    }
}
