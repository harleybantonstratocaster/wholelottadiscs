package com.example.wholelottadiscs.item.boombox;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.RecordItem;

public class BoomboxSoundInstance extends AbstractTickableSoundInstance {

    private final Player player;

    protected BoomboxSoundInstance(RecordItem record, Player player) {
        super(record.getSound(), SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.player = player;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.2F;
        this.x = ((float)player.getX());
        this.y = ((float)player.getY());
        this.z = ((float)player.getZ());
    }

    public boolean canPlaySound() {
        return !this.player.isSilent();
    }

    public boolean canStartSilent() {
        return true;
    }

    @Override
    public void tick() {
        if (this.player.isRemoved()) {
            this.stop();
        } else {
            this.x = ((float)this.player.getX());
            this.y = ((float)this.player.getY());
            this.z = ((float)this.player.getZ());



        }
    }
}
