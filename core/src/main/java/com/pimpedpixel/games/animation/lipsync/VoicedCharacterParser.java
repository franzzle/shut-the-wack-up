package com.pimpedpixel.games.animation.lipsync;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class VoicedCharacterParser {

    public static Map<String, VoicedCharacter> parseByFile(String filePath){
        FileHandle internal = Gdx.files.internal(filePath);
        return parseJson(internal.reader());
    }

    private static Map<String, VoicedCharacter> parseJson(Reader reader) {
        JsonReader jsonReader = new JsonReader();
        JsonValue jsonValue = jsonReader.parse(reader);
        JsonValue.JsonIterator lsRootArray = jsonValue.iterator();

        Map<String, VoicedCharacter> characterMap = new HashMap<>();

        while (lsRootArray.hasNext()) {
            JsonValue characterValue = lsRootArray.next();
            String name = characterValue.getString("name");
            String sentence = characterValue.getString("sentence");
            int row = characterValue.getInt("row");
            int column = characterValue.getInt("column");

            VoicedCharacter voicedCharacter = new VoicedCharacter(name, sentence, row, column);

            characterMap.put(name, voicedCharacter);
        }

        return characterMap;
    }
}

