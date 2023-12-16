package com.pimpedpixel.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class BigFootActor extends Actor implements Disposable {
    private Sprite sprite;

    public BigFootActor() {
        Texture texture = AssetManagerHolder.assetManager.get("bigfoot.png");
        sprite = new Sprite(texture);
        setSize(sprite.getWidth(), sprite.getHeight());
    }

    public void reset(){
        this.setPosition(
            this.getX(),
            Gdx.graphics.getHeight()  + sprite.getHeight());
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

