package net.estebanrodriguez.superherocharactergenerator.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import net.estebanrodriguez.superherocharactergenerator.character_model.Character;

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
        Cursor cursor = mDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()){
            String name = cursor.getString(cursor.getColumnIndex(CharacterVaultContract.CharacterTable.COLUMN_NAME));
            Log.d("HERO", "Retrieved saved character " + name);
        }

        return null;
    }
}
