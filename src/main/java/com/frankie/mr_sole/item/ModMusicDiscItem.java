package com.frankie.mr_sole.item;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;

public class ModMusicDiscItem extends MusicDiscItem {
    public ModMusicDiscItem(int comparatorOutput, SoundEvent sound, Settings settings, int lengthInSeconds) {
        super(comparatorOutput, sound, settings, lengthInSeconds);
    }
}
