package com.pimpedpixel.games;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.pimpedpixel.games.animation.lipsync.LipsyncSequence;
import com.pimpedpixel.games.animation.lipsync.LipsyncSequenceAssetLoader;


public class AssetManagerHolder {
    public final static AssetManager assetManager = new AssetManager();

    public static void load(){
        assetManager.load(new FileHandle("background.png").path(), Texture.class);
        assetManager.load(new FileHandle("bigfoot.png").path(), Texture.class);
        assetManager.load(new FileHandle("hud/crosshair.png").path(), Texture.class);

        assetManager.load(new FileHandle("music/libertyBellMarch.ogg").path(), Music.class);
        assetManager.load(new FileHandle("sounds/splat.wav").path(), Sound.class);

        assetManager.setLoader(LipsyncSequence.class, new LipsyncSequenceAssetLoader(new InternalFileHandleResolver()));


        final String fontFile = "fonts/baskic28.fnt";

        final BitmapFontLoader.BitmapFontParameter bitmapFontParameter = new BitmapFontLoader.BitmapFontParameter();
        bitmapFontParameter.flip = false;

        assetManager.setLoader(BitmapFont.class, new BitmapFontLoader(new InternalFileHandleResolver()));
        assetManager.load(fontFile, BitmapFont.class, bitmapFontParameter);

        assetManager.finishLoading();
    }
}
