package net.estebanrodriguez.superherocharactergenerator.persistance;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.estebanrodriguez.superherocharactergenerator.roll_tables_database.RollTablesContract;

/**
 * Created by Esteban Rodriguez on 8/22/2016.
 */


public class CharacterVaultOpenHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "character_vault.db";


    private static String SQL_CREATE_STATEMENT =
            "CREATE " + CharacterVaultContract.CharacterTable.CHARACTER_TABLE + " (" +
                    CharacterVaultContract.CharacterTable.COLUMN_CHARACTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CharacterVaultContract.CharacterTable.COLUMN_CHARACTER + " BLOB" + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CharacterVaultContract.CharacterTable.CHARACTER_TABLE;

    public CharacterVaultOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STATEMENT);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
