package com.pimpedpixel.games.animation;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pimpedpixel.games.animation.lipsync.LipSyncAnimationsComponentImpl;

public class AnimatedCharacter extends Group{
    private final String characterName;
    private final AnimatedBody animatedBody;

    private final LipSyncAnimationsComponentImpl faceComponent;

    private final Group bodyPlusFace;

    public AnimatedCharacter(String characterName) {
        this.characterName = characterName;
        this.animatedBody = new AnimatedBody(characterName);
        this.faceComponent = new LipSyncAnimationsComponentImpl(characterName);
        bodyPlusFace = new Group();
        bodyPlusFace.addActor(this.animatedBody);
        bodyPlusFace.addActor(this.faceComponent);
        this.faceComponent.setSpeakingState(SpeakingState.SPEAKING);
        bodyPlusFace.setName("bodyPlusFace");

        this.addActor(bodyPlusFace);

        setWidth(animatedBody.getWidth());
        setHeight(animatedBody.getHeight());
    }

    public String getCharacterName() {
        return characterName;
    }

    public Group getBodyPlusFace() {
        return bodyPlusFace;
    }

    public AnimatedBody getAnimatedBody() {
        return animatedBody;
    }

    public LipSyncAnimationsComponentImpl getFaceComponent() {
        return faceComponent;
    }

    public Rectangle getBoundingRectangle() {
        final Rectangle boundingRectangle = new Rectangle();
        boundingRectangle.set(
            getX(),
            getY(),
            getWidth(),
            getHeight()
        );
        return boundingRectangle;
    }
}
