package com.pimpedpixel.games;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;
import com.pimpedpixel.games.animation.AnimatedCharacter;
import com.pimpedpixel.games.animation.lipsync.PlaySoundAction;

public class StopSpeakingAction extends RunnableAction {
    private final AnimatedCharacter currentAnimatedCharacter;
    private final CrosshairActor crosshairActor;

    public StopSpeakingAction(AnimatedCharacter currentAnimatedCharacter,
                              CrosshairActor crosshairActor) {
        this.currentAnimatedCharacter = currentAnimatedCharacter;
        this.crosshairActor = crosshairActor;
    }

    @Override
    public void run() {
        if(currentAnimatedCharacter.getBoundingRectangle().contains(crosshairActor.getX(), crosshairActor.getY())){
            System.out.println("YES");
        }

        stopSpeaking();
    }

    private void stopSpeaking() {
        if(currentAnimatedCharacter != null){
            Array<Action> actions = currentAnimatedCharacter.getActions();
            if(actions != null && actions.size > 0){
                Action action = actions.get(0);
                if(action instanceof SequenceAction){
                    SequenceAction sequenceAction = (SequenceAction) action;
                    Array<Action> innerActions = sequenceAction.getActions();
                    for(Action actionsInsideSequence : innerActions){
                        if(actionsInsideSequence instanceof PlaySoundAction){
                            PlaySoundAction playSoundAction = (PlaySoundAction) actionsInsideSequence;
                            playSoundAction.stop();
                        }
                    }
                }
            }
            currentAnimatedCharacter.clearActions();
        }
    }
}
