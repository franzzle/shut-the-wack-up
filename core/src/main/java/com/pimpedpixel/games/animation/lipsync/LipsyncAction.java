package com.pimpedpixel.games.animation.lipsync;

import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.pimpedpixel.games.animation.AnimatedCharacter;

import java.util.Iterator;

// TODO Make poolable
public class LipsyncAction extends RunnableAction {
    private final AnimatedCharacter animatedCharacter;
    private float timePassed;
    private final Iterator<Mouthcue> iterator;
    private Mouthcue currentMouthCue;

    public LipsyncAction(AnimatedCharacter animatedCharacter, LipsyncSequence lipsyncSequence) {
        this.animatedCharacter = animatedCharacter;
        this.iterator = lipsyncSequence.getMouthCues().iterator();

        currentMouthCue = iterator.next();
        updateLipsyncFrame();
    }

    @Override
    public boolean act(float delta) {
        //TODO How to get rid of this lipsyncHack
        final float lipsyncHack = delta * 0.5f;
        if (timePassed > currentMouthCue.getEnd() - currentMouthCue.getStart() - lipsyncHack) {
            timePassed = 0;
            currentMouthCue = iterator.next();
            updateLipsyncFrame();
        }
        timePassed += delta;
        return !iterator.hasNext();
    }

    private void updateLipsyncFrame() {
        animatedCharacter.getFaceComponent().updateLipsyncSprite(currentMouthCue.getValue());
    }
}
