package com.pimpedpixel.games.animation.lipsync;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

public class LipsyncSequenceParserImpl implements LipsyncSequenceParser {
    @Override
    public LipsyncSequence parseByFile(String lipSyncValuePath) {
        return parseLipsyncSequence(Gdx.files.internal(lipSyncValuePath));
    }

    private LipsyncSequence parseLipsyncSequence(FileHandle lipsyncFileHandle) {
        final LipsyncSequence lipsyncSequence = new LipsyncSequence();
        final JsonReader jsonReader = new JsonReader();
        final JsonValue jsonValue = jsonReader.parse(lipsyncFileHandle.reader());
        final JsonValue.JsonIterator lsRootArray = jsonValue.iterator();

        while (lsRootArray.hasNext()) {
            final JsonValue childOfRoot = lsRootArray.next();
            if (childOfRoot.name.equalsIgnoreCase("metadata")) {
                final Metadata metadata = new Metadata();
                final String soundFile = childOfRoot.getString("soundFile");
                final Float duration = childOfRoot.getFloat("duration");
                metadata.setSoundFile(soundFile);
                metadata.setDuration(duration);
                lipsyncSequence.setMetadata(metadata);
            }
            if (childOfRoot.name.equalsIgnoreCase("mouthCues")) {
                final ArrayList<Mouthcue> mouthCues = new ArrayList<Mouthcue>();
                lipsyncSequence.setMouthCues(mouthCues);
                final JsonValue.JsonIterator iterator = childOfRoot.iterator();
                while (iterator.hasNext()) {
                    final JsonValue mouthCueJsonValue = iterator.next();
                    final Float start = mouthCueJsonValue.getFloat("start");
                    final Float end = mouthCueJsonValue.getFloat("end");
                    final String value = mouthCueJsonValue.getString("value");
                    final Mouthcue mouthcue = new Mouthcue();
                    mouthcue.setStart(start);
                    mouthcue.setEnd(end);
                    mouthcue.setValue(value);
                    mouthCues.add(mouthcue);
                }
            }
        }
        return lipsyncSequence;
    }


}
