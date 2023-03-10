package com.example.wholelottadiscs.item.recordsleeve.sounds;

import com.example.wholelottadiscs.WholeLottaDiscs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Sounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, WholeLottaDiscs.MOD_ID);

    public static RegistryObject<SoundEvent> DOORS = registerSoundEvent("doors");
    public static RegistryObject<SoundEvent> SOAD = registerSoundEvent("soad");
    public static RegistryObject<SoundEvent> AGATHA = registerSoundEvent("agatha");
    public static RegistryObject<SoundEvent> MCR = registerSoundEvent("mcr");
    public static RegistryObject<SoundEvent> SLAYER = registerSoundEvent("slayer");
    public static RegistryObject<SoundEvent> METALLICA = registerSoundEvent("metallica");
    public static RegistryObject<SoundEvent> PANTERA = registerSoundEvent("pantera");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(WholeLottaDiscs.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
    }