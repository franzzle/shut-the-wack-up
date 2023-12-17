package com.pimpedpixel.games;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class BoundingRectRenderer implements Renderable {
    private final ShapeRenderer shapeRenderer;
    private final Camera orthographicCamera;
    private final BoundingRectSupport boundingRectSupport;

    public BoundingRectRenderer(BoundingRectSupport boundingRectSupport, Camera orthographicCamera) {
        this.boundingRectSupport = boundingRectSupport;
        this.orthographicCamera = orthographicCamera;
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        Rectangle boundingRect = boundingRectSupport.getBoundingRectangle();
        shapeRenderer.rect(
            boundingRect.getX(),
                boundingRect.getY(),
                boundingRect.getWidth(),
                boundingRect.getHeight());
        shapeRenderer.end();
    }
}
