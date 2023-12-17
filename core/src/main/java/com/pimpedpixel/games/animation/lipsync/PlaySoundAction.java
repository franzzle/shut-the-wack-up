package com.pimpedpixel.games.animation.lipsync;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.pimpedpixel.games.AssetManagerHolder;

public class PlaySoundAction extends RunnableAction {
    private final String characterName;
    private Sound lipsyncSound;
    private Long soundPlayingId;

    public PlaySoundAction(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public boolean act(float delta) {
        final String soundPath = "speech/" + characterName + "/sentence.wav";
        lipsyncSound = AssetManagerHolder.assetManager.get(soundPath, Sound.class);
        soundPlayingId = lipsyncSound.play();
        return true;
    }

    public void stop(){
        if(lipsyncSound != null && soundPlayingId != null){
            lipsyncSound.stop(soundPlayingId);
        }
    }
}
