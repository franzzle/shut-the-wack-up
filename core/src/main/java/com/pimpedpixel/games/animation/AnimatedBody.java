package com.pimpedpixel.games.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.pimpedpixel.games.PathBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimatedBody extends Actor {
    private Animation<Sprite> currentWalkAnimation;
    private final Map<String, Animation<Sprite>> walkAnimsMap = new HashMap<>();
    private final String characterName;
    private boolean active;
    private float time;

    public AnimatedBody(String characterName) {
        this.characterName = characterName;
        AnimationParser animationParser = new AnimationParser();
        this.setActive(false);

        final TextureAtlas characterAtlas = getTextureAtlas(characterName);

        for (Texture texture : characterAtlas.getTextures()) {
            texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }

        final List<Sprite> lipsyncDownSprites = animationParser.initAnimation(characterName, "lipsyncdown", characterAtlas);

        // Since there's only one row, we only get the first array
        final float frameDuration = 0.1f;
        Animation heroWalkDownRestAnimation = new Animation<>(frameDuration, lipsyncDownSprites.toArray(new Sprite[0]));
        walkAnimsMap.put(WalkingDirection.DOWN.getType(), heroWalkDownRestAnimation);
        currentWalkAnimation = heroWalkDownRestAnimation;

        int width = animationParser.maxWidth(characterName, "lipsyncdown", characterAtlas);
        int height = animationParser.maxHeight(characterName, "lipsyncdown", characterAtlas);
        this.setWidth(width);
        this.setHeight(height);

        this.setName("body");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
            // Check if the animation is not null and draw it
        if (getCurrentAnimation() != null) {
            Sprite currentFrame = currentWalkAnimation.getKeyFrame(time += Gdx.graphics.getDeltaTime(), true);
            if(currentFrame != null){
                currentFrame.draw(batch);
            }else{
                Gdx.app.log("", currentWalkAnimation.toString());
            }
        }
    }

    public void animateHeroWalkingInDirection(WalkingDirection walkingDirection) {
        Animation<Sprite> spriteAnimation = this.walkAnimsMap.get(walkingDirection.getType());
        if (spriteAnimation != null) {
            if (walkingDirection == WalkingDirection.RIGHT || walkingDirection == WalkingDirection.LEFT) {
                // Iterate through all frames and flip each sprite horizontally
                for(Object spriteFrame : spriteAnimation.getKeyFrames()){
                    if(spriteFrame instanceof Sprite){
                        Sprite sprite = (Sprite) spriteFrame;
                        sprite.setFlip(!sprite.isFlipX(), false);
                    }
                }
            }
            this.currentWalkAnimation = spriteAnimation;
        }
    }

    private TextureAtlas getTextureAtlas(String characterName) {
        PathBuilder pathBuilder = CharacterPathUtil.initPathBuilder(characterName);
        if (pathBuilder.pathExists()) {
            return new TextureAtlas(pathBuilder.build());
        }
        throw new GdxRuntimeException("Character with name " + characterName + " is not present");
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Animation<Sprite> getCurrentAnimation() {
        return currentWalkAnimation;
    }

    @Override
    public String toString() {
        return characterName;
    }
}
