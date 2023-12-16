package com.pimpedpixel.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import static com.pimpedpixel.games.GameSettings.CROSSHAIR_DISPLACEMENT_UNIT;

public class GameInput implements InputProcessor {
    private final CrosshairActor crosshairActor;
    public boolean upKeyPressed;
    public boolean downKeyPressed;
    public boolean leftKeyPressed;
    public boolean rightKeyPressed;

    public GameInput(CrosshairActor crosshairActor){
        this.crosshairActor = crosshairActor;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            //TODO
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
        float newX = this.crosshairActor.getX();
        float newY = this.crosshairActor.getY();

        if (upKeyPressed) {
            newY += CROSSHAIR_DISPLACEMENT_UNIT;
        }
        if (downKeyPressed) {
            newY -= CROSSHAIR_DISPLACEMENT_UNIT;
        }
        if (leftKeyPressed) {
            newX -= CROSSHAIR_DISPLACEMENT_UNIT;
        }
        if (rightKeyPressed) {
            newX += CROSSHAIR_DISPLACEMENT_UNIT;
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
