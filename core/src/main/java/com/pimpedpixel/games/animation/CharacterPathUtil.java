package com.pimpedpixel.games.animation;

import com.pimpedpixel.games.PathBuilder;

public class CharacterPathUtil {
    public static PathBuilder initPathBuilder(String characterName) {
        PathBuilder pathBuilder = new PathBuilder();

        pathBuilder.append("characters");
        pathBuilder.append( characterName  + "Animations" + ".txt");
        return pathBuilder;
    }
}
