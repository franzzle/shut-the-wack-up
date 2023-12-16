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
            return true;
        }
        if (keycode == Input.Keys.UP) {
            upKeyPressed = true;
            return true;
        }
        if (keycode == Input.Keys.DOWN) {
            downKeyPressed = true;
            return true;
        }
        if (keycode == Input.Keys.LEFT) {
            leftKeyPressed = true;
            return true;
        }
        if (keycode == Input.Keys.RIGHT) {
            rightKeyPressed = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.UP :
                upKeyPressed = false;
                break;
            case Input.Keys.DOWN :
                downKeyPressed = false;
                break;
            case Input.Keys.LEFT:
                leftKeyPressed = false;
                break;
            case Input.Keys.RIGHT:
                rightKeyPressed = false;
                break;
        }
        return false;
    }

    public void updatePosition(){
        if(upKeyPressed){
            this.crosshairActor.setPosition(this.crosshairActor.getX(),
                this.crosshairActor.getY() + CROSSHAIR_DISPLACEMENT_UNIT);
        }
        if(downKeyPressed){
            this.crosshairActor.setPosition(this.crosshairActor.getX(),
                this.crosshairActor.getY() - CROSSHAIR_DISPLACEMENT_UNIT);
        }
        if(leftKeyPressed){
            this.crosshairActor.setPosition(this.crosshairActor.getX() - CROSSHAIR_DISPLACEMENT_UNIT,
                crosshairActor.getY());
        }
        if(rightKeyPressed){
            this.crosshairActor.setPosition(this.crosshairActor.getX() + CROSSHAIR_DISPLACEMENT_UNIT,
                crosshairActor.getY());
        }

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
