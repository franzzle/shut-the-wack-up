package com.pimpedpixel.games.animation.lipsync;

import java.io.Serializable;
import java.util.List;

public class LipsyncSequence implements Serializable {
    private Metadata metadata;
    private List<Mouthcue> mouthCues;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Mouthcue> getMouthCues() {
        return mouthCues;
    }

    public void setMouthCues(List<Mouthcue> mouthCues) {
        this.mouthCues = mouthCues;
    }
}
