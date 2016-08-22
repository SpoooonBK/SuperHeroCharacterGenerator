package net.estebanrodriguez.superherocharactergenerator.roll_tables_database;

/**
 * Created by Esteban Rodriguez on 8/12/2016.
 */
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class RollTablesOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "superherogendb.db";
    private static final int DATABASE_VERSION = 1;

    public RollTablesOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}