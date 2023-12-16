package com.pimpedpixel.games;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class CrosshairActor extends Actor implements Disposable {
    private float width;
    private float height;
    private Color color;

    private TextureRegion blankTextureRegion;

    public CrosshairActor(float width, float height) {
        this.width = width;
        this.height = height;
        this.color = Color.RED;

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        blankTextureRegion = new TextureRegion(new Texture(pixmap));
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(color);
        batch.draw(blankTextureRegion, getX(), getY() + (height / 2), getWidth(), 1);
        batch.draw(blankTextureRegion, getX() + (width / 2), getY(), 1, getHeight());
        batch.setColor(Color.RED);
    }

    @Override
    public void dispose() {
        blankTextureRegion.getTexture().dispose();
    }
}

