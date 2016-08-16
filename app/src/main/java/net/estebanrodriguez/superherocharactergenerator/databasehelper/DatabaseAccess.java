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
import java.util.List;



public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    private final String TABLE_NUMBER_OF_POWERS = "number_of_powers";
    private final String TABLE_ORIGIN = "origin";
    private final String TABLE_PHYSICAL_FORM = "physicalForm";
    private final String TABLE_POWER_CLASS = "power_class";
    private final String TABLE_POWER_DEFENSIVE = "power_defensive";
    private final String TABLE_POWER_DETECTION = "power_detection";
    private final String TABLE_POWER_ENERGY_CONTROL = "power_energy_control";
    private final String TABLE_POWER_ENERGY_EMISSION = "power_energy_emission";
    private final String TABLE_POWER_FIGHTING = "power_fighting";
    private final String TABLE_POWER_ILLUSORY = "power_illusory";
    private final String TABLE_POWER_LIFEFORM_CONTROL = "power_lifeform_control";
    private final String TABLE_POWER_MAGICAL = "power_magical";
    private final String TABLE_POWER_MATTER_CONTROL = "power_matter_control";
    private final String TABLE_POWER_MATTER_CONVERSION = "power_matter_conversion";
    private final String TABLE_POWER_MATTER_CREATION = "power_matter_creation";
    private final String TABLE_POWER_MENTAL_ENHANCEMENT = "power_mental_enhancement";
    private final String TABLE_POWER_PHYSICAL_ENHANCEMENT = "power_physical_enchancement";
    private final String TABLE_POWER_POWER_CONTROL = "power_power_control";
    private final String TABLE_POWER_SELF_ALTERATION = "power_self_alteration";
    private final String TABLE_POWER_TRAVEL = "power_travel";
    private final String TABLE_WEAKNESS_DURATION = "weakness_duration";
    private final String TABLE_WEAKNESS_EFFECT = "weakness_effect";



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
    public PhysicalForm getForm(int roll) {
        Log.d ("HERO", "Roll: " + roll);
        String query = "SELECT form, subForm FROM " + TABLE_PHYSICAL_FORM +
                " WHERE lowRoll <= " + roll + " AND highRoll >= " + roll;


        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String form = cursor.getString(cursor.getColumnIndex("form"));
        String subform = cursor.getString(cursor.getColumnIndex("subForm"));

        cursor.close();
        return new PhysicalForm(form, subform);
    }

    public Origin getOrigin(int roll) {
        String query = "SELECT origin FROM " + TABLE_ORIGIN+
                " WHERE lowRoll <= " + roll + " AND highRoll >= " + roll;

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String origin = cursor.getString(cursor.getColumnIndex("origin"));
        cursor.close();
        return new Origin(origin);
    }

    public List<Integer> getAmounts(int roll) {
        List<Integer> list = new ArrayList<>();
        String query = "SELECT powersInitialAmount, powersMaxAmount,  talentsInitialAmount, " +
                "talentsMaxAmount, contactsInitialAmount, contactsMaxAmount " +
                "FROM " + TABLE_NUMBER_OF_POWERS +" WHERE lowRoll <=" + roll +
                " AND highRoll >= " + roll;

        Log.d ("HER0", "QUERY: "+query);
        Cursor cursor = database.rawQuery(query, null);

        String[] colNames = cursor.getColumnNames();
        for(String s: colNames){
            Log.d("HERO",s);
        }

        cursor.moveToFirst();
        list.add(cursor.getInt(cursor.getColumnIndex("powersInitialAmount")));
        list.add(cursor.getInt(cursor.getColumnIndex("powersMaxAmount")));
        list.add(cursor.getInt(cursor.getColumnIndex("talentsInitialAmount")));
        list.add(cursor.getInt(cursor.getColumnIndex("talentsMaxAmount")));
        list.add(cursor.getInt(cursor.getColumnIndex("contactsInitialAmount")));
        list.add(cursor.getInt(cursor.getColumnIndex("contactsMaxAmount")));
        cursor.close();
        return list;
    }

    public Power getPower(int roll) {

        List<String> list = new ArrayList<>();
        String powerClassquery = "SELECT powerClass, powerTableName FROM " +TABLE_POWER_CLASS +
                "WHERE lowRoll <= " + roll + " AND highRoll >= " + roll;

        Cursor cursor = database.rawQuery(powerClassquery, null);
        cursor.moveToFirst();
        String powerClass = cursor.getString(cursor.getColumnIndex("powerClass"));
        String powerTableName = cursor.getString(cursor.getColumnIndex("powerTableName"));
        cursor.close();

        int powerRoll = DieRoller.roll(100);

        String powerQuery = "SELECT id_power, power, powerCode FROM " + powerTableName +
                "WHERE lowRoll <= " + powerRoll + " AND highRoll >= " + powerRoll;
        Cursor powerCursor = database.rawQuery(powerClassquery, null);

        cursor.moveToFirst();
        String powerName = powerCursor.getString(powerCursor.getColumnIndex("power"));
        String powerCode = powerCursor.getString(powerCursor.getColumnIndex("powerCode") +
                powerCursor.getInt(powerCursor.getColumnIndex("id_power")));
        powerCursor.close();

        return new Power(powerClass, powerName, powerCode);
    }
}
