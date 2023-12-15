package com.pimpedpixel.games.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.List;

public class AnimationParser {
    public List<Sprite> initAnimation(String name, String animName, TextureAtlas atlas) {
        List<Sprite> sprites = new ArrayList<>(atlas.getRegions().size);
        for (TextureAtlas.AtlasRegion atlasRegion : atlas.getRegions()) {
            final String regionName = atlasRegion.name;
            final String regionNameStripped = regionName.split("-")[1];
            if (regionName.contains(name) && regionNameStripped.endsWith(animName)) {
                // Create a new Sprite instance and copy attributes from the AtlasRegion
                Sprite sprite = new Sprite(atlasRegion);
                if(sprite != null){
                    sprite.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
                    sprites.add(sprite);
                }
            }
        }
        return sprites;
    }
}
