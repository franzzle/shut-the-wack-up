package com.pimpedpixel.games.animation.lipsync;

public class VoicedCharacter {
    private final String name;
    private final String sentence;

    private final int row;
    private final int column;

    public VoicedCharacter(String name, String sentence, int row, int column) {
        this.name = name;
        this.sentence = sentence;
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getName() {
        return name;
    }

    public String getSentence() {
        return sentence;
    }
}
