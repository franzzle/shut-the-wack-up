package com.pimpedpixel.games.animation.lipsync;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.pimpedpixel.games.PathBuilder;
import com.pimpedpixel.games.animation.AnimatedSprite;
import com.pimpedpixel.games.animation.CharacterPathUtil;
import com.pimpedpixel.games.animation.SpeakingState;
import com.pimpedpixel.games.animation.WalkingDirection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LipSyncAnimationsComponentImpl extends Actor implements LipSyncAnimationComponent {
    private SpeakingState speakingState;
    private AnimatedSprite currentLipsyncSprite;
    private Map<String, Animation> lipsyncAnimationLookup;
    private Set<WalkingDirection> supportedWalkingDirections;
    private final String characterName;
    private boolean hasLipsyncAnimation;

    public LipSyncAnimationsComponentImpl(String characterName) {
        this.characterName = characterName;
        this.lipsyncAnimationLookup = new HashMap<>();
        this.supportedWalkingDirections = new HashSet<>();
        this.hasLipsyncAnimation = false;

        TextureAtlas textureAtlas = getTextureAtlas(characterName);
        if(textureAtlas != null){
            for (Texture texture : textureAtlas.getTextures()) {
                texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
            }

            Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();
            for(TextureAtlas.AtlasRegion region : regions){
                boolean needFlip = false;
                if(region.name.contains(WalkingDirection.DOWN.getType())){
                    supportedWalkingDirections.add(WalkingDirection.DOWN);
                }
                if(region.name.contains(WalkingDirection.UP.getType())){
                    supportedWalkingDirections.add(WalkingDirection.UP);
                }
                if(region.name.contains(WalkingDirection.RIGHT.getType())){
                    supportedWalkingDirections.add(WalkingDirection.RIGHT);
                }
                if(region.name.contains(WalkingDirection.LEFT.getType())){
                    supportedWalkingDirections.add(WalkingDirection.LEFT);
                    needFlip = true;
                }
                Sprite sprite = textureAtlas.createSprite(region.name);
                if(needFlip){
                    sprite.flip(true, false);
                }
                Animation lipsyncAnimation = new Animation<>(10, sprite);
                lipsyncAnimationLookup.put(region.name, lipsyncAnimation);
            }

            String restingLipsyncCueue = characterName + "-lipsync-down-" +"X";
            Animation lipsyncRestCueueAnimation = lipsyncAnimationLookup.get(restingLipsyncCueue);
            if(lipsyncRestCueueAnimation == null){
                throw new GdxRuntimeException("Lipsync animation for character" +  characterName + " is not present");
            }
            this.currentLipsyncSprite = new AnimatedSprite(lipsyncRestCueueAnimation);
            hasLipsyncAnimation = true;
        }
    }

    private TextureAtlas getTextureAtlas(String characterName) {
        PathBuilder pathBuilder = initPathBuilder(characterName);
        if (pathBuilder.pathExists()) {
            return new TextureAtlas(pathBuilder.build());
        }
        throw new GdxRuntimeException("Character with name " + characterName + " is not present");
    }

    public static PathBuilder initPathBuilder(String characterName) {
        PathBuilder pathBuilder = new PathBuilder();

        pathBuilder.append("characters");
        pathBuilder.append( characterName  + "LipSyncAnimations" + ".txt");
        return pathBuilder;
    }

    @Override
    public void updateLipsyncSprite(String currentCueValueIndex) {
        if(hasLipsyncAnimation){
            String lipsyncRegionName = characterName + "-lipsync-" + WalkingDirection.DOWN.getType() + "-" + currentCueValueIndex;
            setActiveAnimationName(lipsyncRegionName);
        }
    }

    public void setActiveAnimationName(String lipsyncRegionName) {
        Animation animation = this.lipsyncAnimationLookup.get(lipsyncRegionName);

        this.currentLipsyncSprite.stop();
        this.currentLipsyncSprite.setAnimation(animation);
        this.currentLipsyncSprite.play();
    }

    @Override
    public SpeakingState getSpeakingState() {
        return speakingState;
    }

    @Override
    public void setSpeakingState(SpeakingState speakingState) {
        this.speakingState = speakingState;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (currentLipsyncSprite != null && getSpeakingState() == SpeakingState.SPEAKING) {
            currentLipsyncSprite.draw(batch);
        }
    }

}
