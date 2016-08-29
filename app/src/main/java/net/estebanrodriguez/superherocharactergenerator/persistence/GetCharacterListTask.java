package net.estebanrodriguez.superherocharactergenerator.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;

import java.util.List;

/**
 * Created by Esteban Rodriguez on 8/23/2016.
 */
public class GetCharacterListTask extends AsyncTask<Context, Void, List<Character>> {
    SQLiteDatabase mDatabase;


    @Override
    protected List<Character> doInBackground(Context... contexts) {

        CharacterVaultOpenHelper characterVaultOpenHelper = new CharacterVaultOpenHelper(contexts[0]);
        mDatabase = characterVaultOpenHelper.getReadableDatabase();

        String query = "SELECT * FROM " + CharacterVaultContract.CharacterTable.CHARACTER_TABLE;
        Log.d("HERO", "Get Character List Task: " + query );
        Cursor cursor = mDatabase.rawQuery(query, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            PoweredCharacter poweredCharacter = new PoweredCharacter();

            poweredCharacter.setCharacterID(cursor.getInt(cursor.getColumnIndex(CharacterVaultContract.CharacterTable.COLUMN_CHARACTER_ID)));
            poweredCharacter.setCharacterName(cursor.getString(cursor.getColumnIndex(CharacterVaultContract.CharacterTable.COLUMN_NAME)));


            cursor.moveToNext();
        }

        return null;
    }
}
