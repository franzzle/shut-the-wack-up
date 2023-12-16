package com.pimpedpixel.games.animation.lipsync;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class LipsyncSequenceAssetLoader extends AsynchronousAssetLoader<LipsyncSequence, LipsyncSequenceAssetLoader.LipsyncSequenceParameter> {
    private final LipsyncSequenceParser lipsyncSequenceParser;
    public LipsyncSequenceAssetLoader(FileHandleResolver resolver) {
        super(resolver);
        lipsyncSequenceParser = new LipsyncSequenceParserImpl();
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, LipsyncSequenceParameter parameter) {
        lipsyncSequenceParser.parseByFile(fileName);
    }

    @Override
    public LipsyncSequence loadSync(AssetManager manager, String fileName, FileHandle file, LipsyncSequenceParameter parameter) {
        return lipsyncSequenceParser.parseByFile(fileName);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName,
                                                  FileHandle file,
                                                  LipsyncSequenceParameter parameter) {
        return null;
    }

    static public class LipsyncSequenceParameter extends AssetLoaderParameters<LipsyncSequence> {
    }
}
