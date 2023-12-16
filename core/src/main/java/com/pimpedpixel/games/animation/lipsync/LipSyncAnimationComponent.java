package com.pimpedpixel.games.animation.lipsync;

import com.pimpedpixel.games.animation.SpeakingState;

public interface LipSyncAnimationComponent {
    /**
     * currentCueValueIndex represents A through H for spoken cueues. X is the resting cueueu
     * @param currentCueValueIndex A through H and X
     */
    void updateLipsyncSprite(String currentCueValueIndex);
    SpeakingState getSpeakingState();
    void setSpeakingState(SpeakingState speakingState);
}
