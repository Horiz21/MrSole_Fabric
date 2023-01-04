package com.frankie.mr_sole.sound;

import com.frankie.mr_sole.MrSole;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent MUSIC_DISC_DEEP_OCEAN = registerSoundEvent("music_disc_deep_ocean");
    public static final SoundEvent MUSIC_DISC_IMAGINATION = registerSoundEvent("music_disc_imagination");
    public static final SoundEvent MUSIC_DISC_SEA_LEVEL = registerSoundEvent("music_disc_sea_level");
    public static final SoundEvent ENTITY_SOLE_AMBIENT = registerSoundEvent("entity_sole_ambient");
    public static final SoundEvent ENTITY_SOLE_DEATH = registerSoundEvent("entity_sole_death");
    public static final SoundEvent ENTITY_SOLE_FLOP = registerSoundEvent("entity_sole_flop");
    public static final SoundEvent ENTITY_SOLE_HURT = registerSoundEvent("entity_sole_hurt");
    public static final SoundEvent ENTITY_MOLE_AMBIENT = registerSoundEvent("entity_mole_ambient");
    public static final SoundEvent ENTITY_MOLE_HURT = registerSoundEvent("entity_mole_hurt");
    public static final SoundEvent ENTITY_MOLE_DEATH = registerSoundEvent("entity_mole_death");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(MrSole.MOD_ID, name);
        SoundEvent event = SoundEvent.of(id);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
