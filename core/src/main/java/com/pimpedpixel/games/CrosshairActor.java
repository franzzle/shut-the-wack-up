package com.pimpedpixel.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class CrosshairActor extends Actor implements Disposable {
    private Sprite sprite;


    public CrosshairActor() {
        Texture texture = new Texture(Gdx.files.internal("hud/crosshair.png"));
        sprite = new Sprite(texture);
        setSize(sprite.getWidth(), sprite.getHeight());
        this.setPosition(Gdx.graphics.getWidth() * 0.5f,Gdx.graphics.getHeight() * 0.5f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        sprite.setPosition(this.getX(), this.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void dispose() {


    }
}

