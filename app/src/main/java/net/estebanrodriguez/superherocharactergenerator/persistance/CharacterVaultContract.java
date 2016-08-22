package net.estebanrodriguez.superherocharactergenerator.persistance;

import android.provider.BaseColumns;

/**
 * Created by Esteban Rodriguez on 8/22/2016.
 */
public final class CharacterVaultContract {

    public CharacterVaultContract() {}

    public static abstract class CharacterTable implements BaseColumns{
        public static final String CHARACTER_TABLE = "characters";
        public static final String COLUMN_CHARACTER_ID ="id_character";
        public static final String COLUMN_CHARACTER ="character";
    }


}
