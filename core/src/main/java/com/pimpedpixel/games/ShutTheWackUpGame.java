package com.pimpedpixel.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pimpedpixel.games.animation.AnimatedBody;
import com.pimpedpixel.games.animation.WalkAnimationAction;
import com.pimpedpixel.games.animation.WalkingDirection;
import com.pimpedpixel.games.character.AnimatedCharacter;

import static com.pimpedpixel.games.animation.WalkingDirection.*;

public class ShutTheWackUpGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture background;

	private AnimatedCharacter animatedCharacter;

	private Stage stage;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		animatedCharacter = new AnimatedCharacter("borisjohnson");
		animatedCharacter.getAnimatedBody().setActive(true);

		final int duration = 3;
		final MoveByAction moveToTheRightAction = new MoveByAction();
		moveToTheRightAction.setAmount(0.75f * Gdx.graphics.getWidth(), 0);
		moveToTheRightAction.setDuration(duration);

		final MoveByAction moveDownAction = new MoveByAction();
		moveDownAction.setAmount(0, -0.5f * Gdx.graphics.getHeight());
		moveDownAction.setDuration(duration);

		final MoveByAction moveLeftAction = new MoveByAction();
		moveLeftAction.setAmount(-0.75f * Gdx.graphics.getWidth(), 0);
		moveLeftAction.setDuration(duration);

		final MoveByAction moveUpAction = new MoveByAction();
		moveUpAction.setAmount(0, 0.5f * Gdx.graphics.getHeight());
		moveUpAction.setDuration(duration);

		final SequenceAction walkAroundTheRoom = new SequenceAction();
		final AnimatedBody animatedBody = animatedCharacter.getAnimatedBody();
		walkAroundTheRoom.addAction(moveToTheRightAction);
		walkAroundTheRoom.addAction(WalkAnimationAction.create(animatedBody, WalkingDirection.DOWN));
		walkAroundTheRoom.addAction(moveDownAction);
		walkAroundTheRoom.addAction(WalkAnimationAction.create(animatedBody, LEFT));
		walkAroundTheRoom.addAction(moveLeftAction);
		walkAroundTheRoom.addAction(WalkAnimationAction.create(animatedBody, UP));
		walkAroundTheRoom.addAction(moveUpAction);
		walkAroundTheRoom.addAction(WalkAnimationAction.create(animatedBody, RIGHT));

		final RepeatAction forever = Actions.forever(walkAroundTheRoom);
		animatedCharacter.getAnimatedBody().addAction(forever);

		stage = new Stage();
		final Viewport viewportLoading = new FitViewport(
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(),
				new OrthographicCamera());
		this.stage.setViewport(viewportLoading);

		animatedCharacter.getAnimatedBody().setX(0.1f * Gdx.graphics.getWidth());
		animatedCharacter.getAnimatedBody().setY(0.64f * Gdx.graphics.getHeight());

		this.stage.addActor(animatedCharacter.getAnimatedBody());
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.end();

		animatedCharacter.getAnimatedBody().act(Gdx.graphics.getDeltaTime());
		stage.getBatch().begin();
		animatedCharacter.getAnimatedBody().draw(stage.getBatch(), 1.0f);
		stage.getBatch().end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
