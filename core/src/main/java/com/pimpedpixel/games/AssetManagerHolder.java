package com.pimpedpixel.games;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

public class AssetManagerHolder {
    public final static AssetManager assetManager = new AssetManager();

    public static void load(){
        assetManager.load(new FileHandle("music/libertyBellMarch.ogg").path(), Music.class);
        assetManager.load(new FileHandle("sounds/splat.wav").path(), Sound.class);
        assetManager.load(new FileHandle("fonts/baskic28.fnt").path(), Font.class);




        assetManager.finishLoading();
    }
}
