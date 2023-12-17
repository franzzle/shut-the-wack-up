package com.pimpedpixel.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
import com.pimpedpixel.games.animation.lipsync.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.pimpedpixel.games.GameSettings.RESPAWN_INTERVAL;
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

        Map<String, VoicedCharacter> voicedCharacterMap = AssetManagerHolder.load();
        System.out.println(voicedCharacterMap);

        Music music = AssetManagerHolder.assetManager.get("music/libertyBellMarch.ogg", Music.class);
        music.setVolume(0.02f);
        music.play();

        stage = new Stage();
        final Viewport viewportLoading = new ScreenViewport();
        this.stage.setViewport(viewportLoading);


        for(String key :  voicedCharacterMap.keySet()){
            VoicedCharacter voicedCharacter = voicedCharacterMap.get(key);
            AnimatedCharacter animatedCharacter= new AnimatedCharacter(voicedCharacter.getName());
            animatedCharacter.getAnimatedBody().animateHeroWalkingInDirection(DOWN);
            animatedCharactersMap.put(voicedCharacter.getName(), animatedCharacter);

            AnimatedBody animatedBody = animatedCharacter.getAnimatedBody();
            animatedBody.setActive(true);
            animatedCharacter.setX(voicedCharacter.getColumn() * 204 - animatedCharacter.getWidth());
            animatedCharacter.setY(voicedCharacter.getRow() * 200 - 100);
            this.stage.addActor(animatedCharacter);
        }

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                String[] keysArray = voicedCharacterMap.keySet().toArray(new String[0]);

                // Create a random number generator
                Random random = new Random();

                // Generate a random index
                int randomIndex = random.nextInt(keysArray.length);

                // Get the random key
                String randomKey = keysArray[randomIndex];

                final AnimatedCharacter animatedCharacter = animatedCharactersMap.get(randomKey);

                // Schedule the character to be removed after spawnInterval
                animatedCharacter.addAction(Actions.sequence(
                    new PlaySoundAction(animatedCharacter.getCharacterName())
                ));
            }
        }, 0, RESPAWN_INTERVAL);




        final CrosshairActor crosshairActor = new CrosshairActor();
        final BigFootActor bigFootActor = new BigFootActor();
        this.stage.addActor(bigFootActor);
        this.stage.addActor(crosshairActor);
        this.gameInput = new GameInput(crosshairActor, bigFootActor);
        Gdx.input.setInputProcessor(this.gameInput);
	}

    private SpawnPos spawnCharacter() {
        int row = MathUtils.random(1, 3); // 0, 1, or 2 for the three rows
        int col = MathUtils.random(1, row == 2 ? 3 : 4);
        SpawnPos spawnPos = new SpawnPos();
        spawnPos.row = row;
        spawnPos.column = col;
        spawnPos.numberOfColumns = row == 2 ? 3 : 4;
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
