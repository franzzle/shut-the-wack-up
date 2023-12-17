package com.pimpedpixel.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;
import com.pimpedpixel.games.animation.AnimatedCharacter;
import com.pimpedpixel.games.animation.lipsync.PlaySoundAction;

import static com.pimpedpixel.games.GameSettings.CROSSHAIR_DISPLACEMENT_UNIT;

public class GameInput implements InputProcessor {
    private final CrosshairActor crosshairActor;
    private final BigFootActor bigFootActor;
    public boolean upKeyPressed;
    public boolean downKeyPressed;
    public boolean leftKeyPressed;
    public boolean rightKeyPressed;
    public boolean cursorMovementAllowed = true;
    private float spaceKeyPressedTime = 0f;

    public AnimatedCharacter currentAnimatedCharacter;

    public GameInput(CrosshairActor crosshairActor, BigFootActor bigFootActor){
        this.crosshairActor = crosshairActor;
        this.bigFootActor = bigFootActor;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            cursorMovementAllowed = false;
            spaceKeyPressedTime = 0f;
            bigFootActor.reset();
            bigFootActor.addAction(getSequenceAction());
            return true;
        }

        if (keycode == Input.Keys.W) {
            upKeyPressed = true;
            return true;
        }
        if (keycode == Input.Keys.S) {
            downKeyPressed = true;
            return true;
        }
        if (keycode == Input.Keys.A) {
            leftKeyPressed = true;
            return true;
        }
        if (keycode == Input.Keys.D) {
            rightKeyPressed = true;
            return true;
        }

        return false;
    }

    private SequenceAction getSequenceAction() {
        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setPosition(
            crosshairActor.getX() - crosshairActor.getWidth() * 0.5f,
            crosshairActor.getY() - crosshairActor.getHeight() * 0.5f);
        moveToAction.setDuration(0.2f);

        SequenceAction sequenceAction = new SequenceAction(
            moveToAction,
            new StopSpeakingAction(currentAnimatedCharacter, crosshairActor),
            new RunnableAction() {
            @Override
            public void run() {
                Sound sound = AssetManagerHolder.assetManager.get("sounds/splat.wav", Sound.class);
                sound.play();
            }
        });
        return sequenceAction;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.W :
                upKeyPressed = false;
                break;
            case Input.Keys.S :
                downKeyPressed = false;
                break;
            case Input.Keys.A:
                leftKeyPressed = false;
                break;
            case Input.Keys.D:
                rightKeyPressed = false;
                break;
        }
        return false;
    }

    public void updatePosition() {
        spaceKeyPressedTime += Gdx.graphics.getDeltaTime();
        float newX = this.crosshairActor.getX();
        float newY = this.crosshairActor.getY();

        if(cursorMovementAllowed){
            bigFootActor.setPosition(newX - crosshairActor.getWidth() * 0.5f, bigFootActor.getY());
        }

        if (upKeyPressed) {
            newY += CROSSHAIR_DISPLACEMENT_UNIT;
        }
        if (downKeyPressed) {
            newY -= CROSSHAIR_DISPLACEMENT_UNIT;
        }

        // Check if cursor movement is allowed in the X direction
        if (leftKeyPressed) {
            newX -= CROSSHAIR_DISPLACEMENT_UNIT;
        }
        if (rightKeyPressed) {
            newX += CROSSHAIR_DISPLACEMENT_UNIT;
        }

        if (spaceKeyPressedTime >= 1.0f) {
            bigFootActor.reset();
            cursorMovementAllowed = true;
        }



        // Ensure the new position stays within the bounds
        newX = Math.max(0, Math.min(newX, Gdx.graphics.getWidth() - crosshairActor.getWidth()));
        newY = Math.max(0, Math.min(newY, Gdx.graphics.getHeight() - crosshairActor.getHeight()));

        this.crosshairActor.setPosition(newX, newY);
    }


    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
