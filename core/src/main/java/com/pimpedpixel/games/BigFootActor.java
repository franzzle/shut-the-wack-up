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
            Gdx.graphics.getWidth() * 0.5f,
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

    public float distanceTo(Actor otherActor) {
        float dx = getX() - otherActor.getX();
        float dy = getY() - otherActor.getY();
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public void dispose() {


    }
}

