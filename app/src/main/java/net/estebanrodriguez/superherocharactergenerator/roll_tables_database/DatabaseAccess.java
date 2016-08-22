package net.estebanrodriguez.superherocharactergenerator.roll_tables_database;

/**
 * Created by Esteban Rodriguez on 8/12/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.estebanrodriguez.superherocharactergenerator.character_model.Ability;
import net.estebanrodriguez.superherocharactergenerator.character_model.AbilityNamesEnum;
import net.estebanrodriguez.superherocharactergenerator.character_model.Weakness;
import net.estebanrodriguez.superherocharactergenerator.utilities.DieRoller;
import net.estebanrodriguez.superherocharactergenerator.character_model.Origin;
import net.estebanrodriguez.superherocharactergenerator.character_model.PhysicalForm;
import net.estebanrodriguez.superherocharactergenerator.character_model.Power;

import java.util.HashMap;
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
        this.openHelper = new RollTablesOpenHelper(context);
    }

    /**
     * Return a singleton instance of RollTablesAccess.
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
                + RollTablesContract.COLUMN_FORM +
                ", " + RollTablesContract.COLUMN_SUBFORM +
                ", " + RollTablesContract.COLUMN_RANDOM_RANKS_ROLL_COL +
                " FROM " + RollTablesContract.TABLE_PHYSICAL_FORM +
                " WHERE " + RollTablesContract.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                RollTablesContract.COLUMN_HIGH_ROLL + " >= " + roll;


        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String form = cursor.getString(cursor.getColumnIndex(RollTablesContract.COLUMN_FORM));
        String subform = cursor.getString(cursor.getColumnIndex(RollTablesContract.COLUMN_SUBFORM));
        int rollColumn = cursor.getInt(cursor.getColumnIndex(RollTablesContract.COLUMN_RANDOM_RANKS_ROLL_COL));

        cursor.close();
        return new PhysicalForm(form, subform, rollColumn);
    }

    public Origin rollOrigin(int roll) {
        String query = "SELECT " +
                RollTablesContract.COLUMN_ORIGIN +
                " FROM " + RollTablesContract.TABLE_ORIGIN +
                " WHERE " + RollTablesContract.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                RollTablesContract.COLUMN_HIGH_ROLL + " >= " + roll;

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String origin = cursor.getString(cursor.getColumnIndex(RollTablesContract.COLUMN_ORIGIN));
        cursor.close();
        return new Origin(origin);
    }

    public Map<String, Integer> rollAmounts(int roll) {
        Map<String, Integer> map = new HashMap<>();
        String query = "SELECT " +
                RollTablesContract.COLUMN_POWERS_INITIAL +
                ", " + RollTablesContract.COLUMN_POWERS_MAX +
                ", " + RollTablesContract.COLUMN_TALENTS_INITIAL +
                ", " + RollTablesContract.COLUMN_TALENTS_MAX +
                ", " + RollTablesContract.COLUMN_CONTACTS_INITIAL +
                ", " + RollTablesContract.COLUMN_CONTACTS_MAX +
                " FROM " + RollTablesContract.TABLE_NUMBER_OF_POWERS +
                " WHERE " + RollTablesContract.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                RollTablesContract.COLUMN_HIGH_ROLL + " >= " + roll;

        Log.d ("HER0", "QUERY: "+query);
        Cursor cursor = database.rawQuery(query, null);

        String[] colNames = cursor.getColumnNames();
        for(String s: colNames){
            Log.d("HERO",s);
        }

        cursor.moveToFirst();
        map.put(RollTablesContract.COLUMN_POWERS_INITIAL, cursor.getInt(cursor.getColumnIndex(RollTablesContract.COLUMN_POWERS_INITIAL)));
        map.put(RollTablesContract.COLUMN_POWERS_MAX, cursor.getInt(cursor.getColumnIndex(RollTablesContract.COLUMN_POWERS_MAX)));
        map.put(RollTablesContract.COLUMN_TALENTS_INITIAL, cursor.getInt(cursor.getColumnIndex(RollTablesContract.COLUMN_TALENTS_INITIAL)));
        map.put(RollTablesContract.COLUMN_TALENTS_MAX, cursor.getInt(cursor.getColumnIndex(RollTablesContract.COLUMN_TALENTS_MAX)));
        map.put(RollTablesContract.COLUMN_CONTACTS_INITIAL, cursor.getInt(cursor.getColumnIndex(RollTablesContract.COLUMN_CONTACTS_INITIAL)));
        map.put(RollTablesContract.COLUMN_CONTACTS_MAX, cursor.getInt(cursor.getColumnIndex(RollTablesContract.COLUMN_CONTACTS_MAX)));
        cursor.close();
        return map;
    }

    public Map<String, String> rollPowerClass(int roll){

        Map<String, String> map = new HashMap<>();
        String query = "SELECT " +
                RollTablesContract.COLUMN_POWER_CLASS +
                ", " + RollTablesContract.COLUMN_POWER_TABLE_NAME +
                " FROM " + RollTablesContract.TABLE_POWER_CLASS +
                " WHERE " + RollTablesContract.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                RollTablesContract.COLUMN_HIGH_ROLL + " >= " + roll;

        Log.d("HERO", "rollPowerClass: "+query);
        Cursor cursor = database.rawQuery(query, null);

        for(String s: cursor.getColumnNames()){
            Log.d("HERO",s);
        }

        cursor.moveToFirst();

        map.put(RollTablesContract.COLUMN_POWER_CLASS,cursor.getString(cursor.getColumnIndex(RollTablesContract.COLUMN_POWER_CLASS)));
        map.put(RollTablesContract.COLUMN_POWER_TABLE_NAME, cursor.getString(cursor.getColumnIndex(RollTablesContract.COLUMN_POWER_TABLE_NAME)));
        cursor.close();
        return map;
    }

    public String rollName(){
        String fullName;
        String tableName;

        //choose between male name  or female name tables
        if (DieRoller.roll(100)>50){
            tableName = "us_male_names_2015";
        } else tableName = "us_female_names_2015";


        int firstNameTotalRows = database.rawQuery("SELECT id_name FROM " + tableName, null).getCount();
        String query = "SELECT first_name FROM "+ tableName +
                " WHERE id_name = " + DieRoller.roll(firstNameTotalRows);
        Cursor firstNameCursor = database.rawQuery(query, null);
        firstNameCursor.moveToFirst();
        String firstName = firstNameCursor.getString(firstNameCursor.getColumnIndex("first_name"));
        firstNameCursor.close();

        int surameTotalRows = database.rawQuery("SELECT id_surname FROM us_surnames", null).getCount();
        String querySurname = "SELECT surname FROM us_surnames WHERE id_surname = " + DieRoller.roll(surameTotalRows);
        Log.d("HERO", "SURNAME: "+ querySurname);
        Cursor surnameCursor = database.rawQuery(querySurname, null);

        surnameCursor.moveToFirst();
        String surname = surnameCursor.getString(surnameCursor.getColumnIndex("surname"));
        surnameCursor.close();

        fullName = firstName + " " + surname;

        return fullName;
    }

    public Power rollPower(int roll, Map<String, String> map) {

        String powerClass = map.get(RollTablesContract.COLUMN_POWER_CLASS);
        String powerTableName = map.get(RollTablesContract.COLUMN_POWER_TABLE_NAME);

        String query = "SELECT " +
                RollTablesContract.COLUMN_POWER_ID +
                ", " + RollTablesContract.COLUMN_POWER_CODE +
                ", " + RollTablesContract.COLUMN_POWER +
                " FROM " + powerTableName +
                " WHERE " + RollTablesContract.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                RollTablesContract.COLUMN_HIGH_ROLL + " >= " + roll;

        Cursor cursor = database.rawQuery(query, null);

        Log.d("HERO", "rollPower:" + query);


        for(String s: cursor.getColumnNames()){
            Log.d("HERO","rollPower: "+ s );
        }
        cursor.moveToFirst();
        String powerName = cursor.getString(cursor.getColumnIndex(RollTablesContract.COLUMN_POWER));
        String code = cursor.getString(cursor.getColumnIndex(RollTablesContract.COLUMN_POWER_CODE));
        int powerid = cursor.getInt(cursor.getColumnIndex(RollTablesContract.COLUMN_POWER_ID));
        String powerCode = code + powerid;
        cursor.close();

        return new Power(powerClass, powerName, powerCode);
    }

    public Map<AbilityNamesEnum, Ability> rollAbilities(int rollColumn) {

        Map<AbilityNamesEnum, Ability> abilityMap = new HashMap<>();
        String lowRollColumn = "lowRoll_col_"+rollColumn;
        String highRollColumn = "highRoll_col_"+rollColumn;

        for(AbilityNamesEnum abilityEnum : AbilityNamesEnum.values()){
            String abilityName = abilityEnum.getAbilityName();
            String initialRankName;
            int initialRankNumber;

            int roll = DieRoller.roll(100);


            String query = "SELECT " + RollTablesContract.COLUMN_RANK_NAME +
                    ", " + RollTablesContract.COLUMN_INITIAL_RANK_NUMBER +
                    " FROM " + RollTablesContract.TABLE_RANDOM_RANKS +
                    " WHERE " + lowRollColumn + " <= " + roll +
                    " AND " + highRollColumn + " >= " + roll;
            Log.d("HERO", "ABILITY ROLL: " + query);

            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            initialRankName = cursor.getString(cursor.getColumnIndex(RollTablesContract.COLUMN_RANK_NAME));
            initialRankNumber = cursor.getInt(cursor.getColumnIndex(RollTablesContract.COLUMN_INITIAL_RANK_NUMBER));
            cursor.close();
            abilityMap.put(abilityEnum, new Ability(abilityName, initialRankName,initialRankNumber));
        }


        return abilityMap;
    }

    public Weakness rollWeakness(){

        String effect = rollWeaknessEffect();
        String duration = rollWeaknessDuration();
        String stimulus = rollWeaknessStimulus();

        return new Weakness(effect, duration,stimulus);
    }

    public String rollWeaknessEffect(){

        int roll = DieRoller.roll(100);
        String query = "SELECT " + RollTablesContract.COLUMN_WEAKNESS_EFFECT +
                " FROM " + RollTablesContract.TABLE_WEAKNESS_EFFECT +
                " WHERE " + RollTablesContract.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                RollTablesContract.COLUMN_HIGH_ROLL + " >= " + roll;

        Log.d("HERO", "WEAKNESS EFFECT: " + query);

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String effect = cursor.getString(cursor.getColumnIndex(RollTablesContract.COLUMN_WEAKNESS_EFFECT));
        return effect;
    }

    public String rollWeaknessDuration(){
        int roll = DieRoller.roll(100);
        String query = "SELECT " + RollTablesContract.COLUMN_WEAKNESS_DURATION +
                " FROM " + RollTablesContract.TABLE_WEAKNESS_DURATION +
                " WHERE " + RollTablesContract.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                RollTablesContract.COLUMN_HIGH_ROLL + " >= " + roll;

        Log.d("HERO", "WEAKNESS DURATION: "+ query);

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String duration = cursor.getString(cursor.getColumnIndex(RollTablesContract.COLUMN_WEAKNESS_DURATION));
        return duration;
    }

    public String rollWeaknessStimulus(){
        int roll = DieRoller.roll(100);

        String query = "SELECT " + RollTablesContract.COLUMN_WEAKNESS_STIMULUS +
                " FROM " + RollTablesContract.TABLE_WEAKNESS_STIMULUS +
                " WHERE " + RollTablesContract.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                RollTablesContract.COLUMN_HIGH_ROLL + " >= " + roll;

        Log.d("HERO", "WEAKNESS STIMULUS: "+ query);

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String stimulus = cursor.getString(cursor.getColumnIndex(RollTablesContract.COLUMN_WEAKNESS_STIMULUS));
        return stimulus;
    }

}
