package com.frankie.mr_sole.item;

import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.sound.SoundEvent;

public class ModEntityBucketItem extends EntityBucketItem {
    private final EntityType<?> entityType;
    private final SoundEvent emptyingSound;

    public ModEntityBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Settings settings) {
        super(type, fluid, emptyingSound, settings);
        this.entityType = type;
        this.emptyingSound = emptyingSound;
    }
}
