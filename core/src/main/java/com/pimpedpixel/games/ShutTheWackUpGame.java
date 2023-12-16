package com.pimpedpixel.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pimpedpixel.games.animation.AnimatedBody;
import com.pimpedpixel.games.animation.AnimatedCharacter;
import com.pimpedpixel.games.animation.lipsync.VoicedCharacter;
import com.pimpedpixel.games.animation.lipsync.VoicedCharacterParser;

import java.util.HashMap;
import java.util.Map;

import static com.pimpedpixel.games.GameSettings.RESPAWN_INTERVAL;
import static com.pimpedpixel.games.GameSettings.SPAWN_INTERVAL;
import static com.pimpedpixel.games.animation.WalkingDirection.DOWN;

public class ShutTheWackUpGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture background;
	private Map<String, AnimatedCharacter> animatedCharactersMap;
	private Stage stage;
    private GameInput gameInput;

	@Override
	public void create () {
		batch = new SpriteBatch();
        AssetManagerHolder.load();
        background = AssetManagerHolder.assetManager.get("background.png");
        animatedCharactersMap = new HashMap<>();

        Map<String, VoicedCharacter> stringVoicedCharacterMap =
            VoicedCharacterParser.parseByFile("lines.json");
        System.out.println(stringVoicedCharacterMap);

        AnimatedCharacter animatedCharacter= new AnimatedCharacter("borisjohnson");
        animatedCharacter.getAnimatedBody().animateHeroWalkingInDirection(DOWN);
        animatedCharactersMap.put("borisjohnson", animatedCharacter);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                final AnimatedCharacter animatedCharacter = animatedCharactersMap.get("borisjohnson");
                final AnimatedBody animatedBody = animatedCharacter.getAnimatedBody();
                animatedBody.setActive(true);

                SpawnPos spawnPos = spawnCharacter();

                float columnSpacing = Gdx.graphics.getWidth() / (float)spawnPos.numberOfColumns; // Recalculate spacing
                float characterX = spawnPos.column * columnSpacing;
                float characterY = spawnPos.row * (Gdx.graphics.getHeight() / 3f); // 3 rows evenly spaced vertically

                if(characterX > Gdx.graphics.getWidth()){
                    System.out.println("Brr");
                }

                animatedCharacter.getAnimatedBody().setX(characterX);
                animatedCharacter.getAnimatedBody().setY(characterY);

                // Schedule the character to be removed after spawnInterval
                animatedBody.addAction(Actions.sequence(
                    Actions.delay(SPAWN_INTERVAL),
                    Actions.run(() -> {
                        // Code to remove the character
                        animatedBody.setActive(false);
                    }
                        )
                ));
            }
        }, 0, RESPAWN_INTERVAL);


		stage = new Stage();
		final Viewport viewportLoading = new ScreenViewport();
		this.stage.setViewport(viewportLoading);
		this.stage.addActor(animatedCharacter.getAnimatedBody());

        final CrosshairActor crosshairActor = new CrosshairActor();
        final BigFootActor bigFootActor = new BigFootActor();
        this.stage.addActor(bigFootActor);
        this.stage.addActor(crosshairActor);
        this.gameInput = new GameInput(crosshairActor, bigFootActor);
        Gdx.input.setInputProcessor(this.gameInput);
	}

    private SpawnPos spawnCharacter() {
        int row = MathUtils.random(0, 2); // 0, 1, or 2 for the three rows
        int col = MathUtils.random(1, row == 1 ? 3 : 4);
        SpawnPos spawnPos = new SpawnPos();
        spawnPos.row = row;
        spawnPos.column = col;
        spawnPos.numberOfColumns = row == 1 ? 3 : 4;
        return spawnPos;
    }

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
        this.gameInput.updatePosition();

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        stage.act();
        stage.draw();
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}

    public void resize (int width, int height) {
        // See below for what true means.
        stage.getViewport().update(width, height, true);
    }
}
