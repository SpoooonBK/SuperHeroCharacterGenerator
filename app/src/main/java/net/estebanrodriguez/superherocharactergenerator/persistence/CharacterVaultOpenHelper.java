package net.estebanrodriguez.superherocharactergenerator.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Esteban Rodriguez on 8/22/2016.
 */


public class CharacterVaultOpenHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "character_vault.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ", ";
    private String SQL_CREATE_STATEMENT;
    private final String SQL_PARTIAL_CREATE_STATEMENT =
                    CharacterVaultContract.CharacterTable.COLUMN_CHARACTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_FORM + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_SUBFORM + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_ORIGIN + TEXT_TYPE + COMMA_SEP +

                    CharacterVaultContract.CharacterTable.COLUMN_FIGHTING_CURRENT_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_FIGHTING_INITIAL_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_FIGHTING_CURRENT_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_FIGHTING_INITIAL_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +

                    CharacterVaultContract.CharacterTable.COLUMN_AGILITY_CURRENT_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_AGILITY_INITIAL_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_AGILITY_CURRENT_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_AGILITY_INITIAL_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +

                    CharacterVaultContract.CharacterTable.COLUMN_STRENGTH_CURRENT_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_STRENGTH_INITIAL_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_STRENGTH_CURRENT_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_STRENGTH_INITIAL_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +

                    CharacterVaultContract.CharacterTable.COLUMN_ENDURANCE_CURRENT_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_ENDURANCE_INITIAL_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_ENDURANCE_CURRENT_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_ENDURANCE_INITIAL_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +

                    CharacterVaultContract.CharacterTable.COLUMN_REASON_CURRENT_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_REASON_INITIAL_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_REASON_CURRENT_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_REASON_INITIAL_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +

                    CharacterVaultContract.CharacterTable.COLUMN_INTUITION_CURRENT_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_INTUITION_INITIAL_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_INTUITION_CURRENT_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_INTUITION_INITIAL_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +

                    CharacterVaultContract.CharacterTable.COLUMN_PSYCHE_CURRENT_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_PSYCHE_INITIAL_RANK_NAME + TEXT_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_PSYCHE_CURRENT_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_PSYCHE_INITIAL_RANK_NUMBER + INTEGER_TYPE + COMMA_SEP +

                    CharacterVaultContract.CharacterTable.COLUMN_POWERS_INITIAL_AMOUNT + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_POWERS_CURRENT_AMOUNT + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_POWERS_MAX_AMOUNT + INTEGER_TYPE + COMMA_SEP +

                    CharacterVaultContract.CharacterTable.COLUMN_TALENTS_INITIAL_AMOUNT + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_TALENTS_CURRENT_AMOUNT + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_TALENTS_MAX_AMOUNT + INTEGER_TYPE + COMMA_SEP +

                    CharacterVaultContract.CharacterTable.COLUMN_CONTACTS_INITIAL_AMOUNT + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_CONTACTS_CURRENT_AMOUNT + INTEGER_TYPE + COMMA_SEP +
                    CharacterVaultContract.CharacterTable.COLUMN_CONTACTS_MAX_AMOUNT + INTEGER_TYPE + COMMA_SEP +
                            CharacterVaultContract.CharacterTable.COLUMN_KARMA_CURRENT + INTEGER_TYPE+ COMMA_SEP +
                            CharacterVaultContract.CharacterTable.COLUMN_HEALTH_CURRENT + INTEGER_TYPE +
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CharacterVaultContract.CharacterTable.CHARACTER_TABLE;

    public CharacterVaultOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        StringBuilder powerStringBuilder = new StringBuilder();
        for (int i = 0; i < 18; i++){
            powerStringBuilder.append(CharacterVaultContract.CharacterTable.COLUMN_BASE_POWER_NAME + i);
            powerStringBuilder.append(INTEGER_TYPE + COMMA_SEP);
            powerStringBuilder.append(CharacterVaultContract.CharacterTable.COLUMN_BASE_POWER_CLASS + i);
            powerStringBuilder.append(INTEGER_TYPE + COMMA_SEP);
            powerStringBuilder.append(CharacterVaultContract.CharacterTable.COLUMN_BASE_POWER_CODE + i);
            powerStringBuilder.append(INTEGER_TYPE + COMMA_SEP);
        }
        String powerTableString = powerStringBuilder.toString();


        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("CREATE TABLE " + CharacterVaultContract.CharacterTable.CHARACTER_TABLE);
        queryStringBuilder.append("(");
        queryStringBuilder.append(powerTableString);
        queryStringBuilder.append(SQL_PARTIAL_CREATE_STATEMENT);
        SQL_CREATE_STATEMENT = queryStringBuilder.toString();



    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("HERO", SQL_CREATE_STATEMENT);
        db.execSQL(SQL_CREATE_STATEMENT);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
