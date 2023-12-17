package com.pimpedpixel.games.animation.lipsync;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.pimpedpixel.games.AssetManagerHolder;

public class PlaySoundAction extends RunnableAction {
    private final String charactername;
    public PlaySoundAction(String charactername) {
        this.charactername = charactername;
    }

    @Override
    public boolean act(float delta) {
        final String soundPath = "speech/" + charactername + "/sentence.wav";
        Sound lipsyncSound = AssetManagerHolder.assetManager.get(soundPath, Sound.class);
        lipsyncSound.play();
        return true;
    }
}
