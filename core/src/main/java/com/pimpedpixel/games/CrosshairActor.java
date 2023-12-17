package com.pimpedpixel.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class CrosshairActor extends Actor implements Disposable, BoundingRectSupport {
    private Sprite sprite;

    public CrosshairActor() {
        Texture texture = AssetManagerHolder.assetManager.get("hud/crosshair.png");
        sprite = new Sprite(texture);
        setSize(sprite.getWidth(), sprite.getHeight());
        this.setPosition(Gdx.graphics.getWidth() * 0.5f,Gdx.graphics.getHeight() * 0.5f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setPosition(this.getX(), this.getY());
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


    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void dispose() {


    }
}

