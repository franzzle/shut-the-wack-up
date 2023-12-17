package com.pimpedpixel.games;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class BoundingRectRenderer implements Renderable {
    private final ShapeRenderer shapeRenderer;
    private final Camera orthographicCamera;
    private final Rectangle boundingRect;

    public BoundingRectRenderer(Rectangle boundingRect, Camera orthographicCamera) {
        this.boundingRect = boundingRect;
        this.orthographicCamera = orthographicCamera;
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(
                boundingRect.getX(),
                boundingRect.getY(),
                boundingRect.getWidth(),
                boundingRect.getHeight());
        shapeRenderer.end();
    }
}
