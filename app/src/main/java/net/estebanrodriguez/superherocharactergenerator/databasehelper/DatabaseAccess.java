package net.estebanrodriguez.superherocharactergenerator.databasehelper;

/**
 * Created by Esteban Rodriguez on 8/12/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.estebanrodriguez.superherocharactergenerator.DieRoller;
import net.estebanrodriguez.superherocharactergenerator.character_model.Origin;
import net.estebanrodriguez.superherocharactergenerator.character_model.PhysicalForm;
import net.estebanrodriguez.superherocharactergenerator.character_model.Power;

import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;






    /**
     * Private constructor to avoid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public PhysicalForm rollForm(int roll) {
        Log.d ("HERO", "Roll: " + roll);
        String query = "SELECT "
                + DatabaseValues.COLUMN_FORM +
                ", " + DatabaseValues.COLUMN_SUBFORM +
                " FROM " + DatabaseValues.TABLE_PHYSICAL_FORM +
                " WHERE " + DatabaseValues.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                DatabaseValues.COLUMN_HIGH_ROLL + " >= " + roll;


        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String form = cursor.getString(cursor.getColumnIndex(DatabaseValues.COLUMN_FORM));
        String subform = cursor.getString(cursor.getColumnIndex(DatabaseValues.COLUMN_SUBFORM));

        cursor.close();
        return new PhysicalForm(form, subform);
    }

    public Origin rollOrigin(int roll) {
        String query = "SELECT " +
                DatabaseValues.COLUMN_ORIGIN +
                " FROM " + DatabaseValues.TABLE_ORIGIN +
                " WHERE " + DatabaseValues.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                DatabaseValues.COLUMN_HIGH_ROLL + " >= " + roll;

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String origin = cursor.getString(cursor.getColumnIndex(DatabaseValues.COLUMN_ORIGIN));
        cursor.close();
        return new Origin(origin);
    }

    public Map<String, Integer> rollAmounts(int roll) {
        Map<String, Integer> map = new HashMap<>();
        String query = "SELECT " +
                DatabaseValues.COLUMN_POWERS_INITIAL +
                ", " + DatabaseValues.COLUMN_POWERS_MAX +
                ", " + DatabaseValues.COLUMN_TALENTS_INITIAL +
                ", " + DatabaseValues.COLUMN_TALENTS_MAX +
                ", " + DatabaseValues.COLUMN_CONTACTS_INITIAL +
                ", " + DatabaseValues.COLUMN_CONTACTS_MAX +
                " WHERE " + DatabaseValues.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                DatabaseValues.COLUMN_HIGH_ROLL + " >= " + roll;

        Log.d ("HER0", "QUERY: "+query);
        Cursor cursor = database.rawQuery(query, null);

        String[] colNames = cursor.getColumnNames();
        for(String s: colNames){
            Log.d("HERO",s);
        }

        cursor.moveToFirst();
        map.put(DatabaseValues.COLUMN_POWERS_INITIAL, cursor.getInt(cursor.getColumnIndex(DatabaseValues.COLUMN_POWERS_INITIAL)));
        map.put(DatabaseValues.COLUMN_POWERS_MAX, cursor.getInt(cursor.getColumnIndex(DatabaseValues.COLUMN_POWERS_MAX)));
        map.put(DatabaseValues.COLUMN_TALENTS_INITIAL, cursor.getInt(cursor.getColumnIndex(DatabaseValues.COLUMN_TALENTS_INITIAL)));
        map.put(DatabaseValues.COLUMN_TALENTS_MAX, cursor.getInt(cursor.getColumnIndex(DatabaseValues.COLUMN_TALENTS_MAX)));
        map.put(DatabaseValues.COLUMN_CONTACTS_INITIAL, cursor.getInt(cursor.getColumnIndex(DatabaseValues.COLUMN_CONTACTS_INITIAL)));
        map.put(DatabaseValues.COLUMN_CONTACTS_MAX, cursor.getInt(cursor.getColumnIndex(DatabaseValues.COLUMN_CONTACTS_MAX)));

        return map;
    }

    public Map<String, String> rollPowerClass(int roll){

        Map<String, String> map = new HashMap<>();
        String query = "SELECT " +
                DatabaseValues.COLUMN_POWER_CLASS +
                ", " + DatabaseValues.COLUMN_POWER_TABLE_NAME +
                " FROM " +DatabaseValues.TABLE_POWER_CLASS +
                " WHERE " + DatabaseValues.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                DatabaseValues.COLUMN_HIGH_ROLL + " >= " + roll;

        Log.d("HERO", "rollPowerClass: "+query);
        Cursor cursor = database.rawQuery(query, null);

        for(String s: cursor.getColumnNames()){
            Log.d("HERO",s);
        }

        cursor.moveToFirst();

        map.put(DatabaseValues.COLUMN_POWER_CLASS,cursor.getString(cursor.getColumnIndex(DatabaseValues.COLUMN_POWER_CLASS)));
        map.put(DatabaseValues.COLUMN_POWER_TABLE_NAME, cursor.getString(cursor.getColumnIndex(DatabaseValues.COLUMN_POWER_TABLE_NAME)));
        cursor.close();
        return map;
    }

    public Power rollPower(int roll, Map<String, String> map) {

        String powerClass = map.get(DatabaseValues.COLUMN_POWER_CLASS);
        String powerTableName = map.get(DatabaseValues.COLUMN_POWER_TABLE_NAME);

        String query = "SELECT " +
                DatabaseValues.COLUMN_POWER_ID +
                ", " + DatabaseValues.COLUMN_POWER_CODE +
                " FROM " + powerTableName +
                " WHERE " + DatabaseValues.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                DatabaseValues.COLUMN_HIGH_ROLL + " >= " + roll;

        Cursor cursor = database.rawQuery(query, null);

        Log.d("HERO", "rollPower:" + query);


        for(String s: cursor.getColumnNames()){
            Log.d("HERO","rollPower: "+ s );
        }
        cursor.moveToFirst();
        String powerName = cursor.getString(cursor.getColumnIndex(DatabaseValues.COLUMN_POWER));
        String code = cursor.getString(cursor.getColumnIndex(DatabaseValues.COLUMN_POWER_CODE));
        int powerid = cursor.getInt(cursor.getColumnIndex(DatabaseValues.COLUMN_POWER_ID));
        String powerCode = code + powerid;
        cursor.close();

        return new Power(powerClass, powerName, powerCode);
    }
}
