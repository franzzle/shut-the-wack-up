package com.pimpedpixel.games.animation;


import com.pimpedpixel.shutthewackup.PathBuilder;

class CharacterPathUtil {
    public static PathBuilder initPathBuilder(String characterName) {
        PathBuilder pathBuilder = new PathBuilder();

        pathBuilder.append("characters");
        pathBuilder.append( characterName  + "Animations" + ".txt");
        return pathBuilder;
    }
}
