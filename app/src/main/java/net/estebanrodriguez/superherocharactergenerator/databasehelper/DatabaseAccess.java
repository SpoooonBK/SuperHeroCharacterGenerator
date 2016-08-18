package net.estebanrodriguez.superherocharactergenerator.databasehelper;

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
import net.estebanrodriguez.superherocharactergenerator.utilities.DieRoller;
import net.estebanrodriguez.superherocharactergenerator.character_model.Origin;
import net.estebanrodriguez.superherocharactergenerator.character_model.PhysicalForm;
import net.estebanrodriguez.superherocharactergenerator.character_model.Power;

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
                ", " + DatabaseValues.COLUMN_RANDOM_RANKS_ROLL_COL +
                " FROM " + DatabaseValues.TABLE_PHYSICAL_FORM +
                " WHERE " + DatabaseValues.COLUMN_LOW_ROLL + " <= " + roll + " AND " +
                DatabaseValues.COLUMN_HIGH_ROLL + " >= " + roll;


        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String form = cursor.getString(cursor.getColumnIndex(DatabaseValues.COLUMN_FORM));
        String subform = cursor.getString(cursor.getColumnIndex(DatabaseValues.COLUMN_SUBFORM));
        int rollColumn = cursor.getInt(cursor.getColumnIndex(DatabaseValues.COLUMN_RANDOM_RANKS_ROLL_COL));

        cursor.close();
        return new PhysicalForm(form, subform, rollColumn);
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
                " FROM " + DatabaseValues.TABLE_NUMBER_OF_POWERS +
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

        String powerClass = map.get(DatabaseValues.COLUMN_POWER_CLASS);
        String powerTableName = map.get(DatabaseValues.COLUMN_POWER_TABLE_NAME);

        String query = "SELECT " +
                DatabaseValues.COLUMN_POWER_ID +
                ", " + DatabaseValues.COLUMN_POWER_CODE +
                ", " + DatabaseValues.COLUMN_POWER +
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

    public Map<AbilityNamesEnum, Ability> rollAbilities(int rollColumn) {

        Map<AbilityNamesEnum, Ability> abilityMap = new HashMap<>();
        String lowRollColumn = "lowRoll_col_"+rollColumn;
        String highRollColumn = "highRoll_col"+rollColumn;

        for(AbilityNamesEnum abilityEnum : AbilityNamesEnum.values()){
            String abilityName = abilityEnum.getAbilityName();
            String initialRankName;
            int initialRankNumber;

            int roll = DieRoller.roll(100);


            String query = "SELECT " + DatabaseValues.COLUMN_RANK_NAME +
                    ", " + DatabaseValues.COLUMN_INITIAL_RANK_NUMBER +
                    " FROM " + DatabaseValues.TABLE_RANDOM_RANKS +
                    " WHERE " + lowRollColumn + " <= " + roll +
                    " AND " + highRollColumn + " >= " + roll;
            Log.d("HERO", "ABILITY ROLL: " + query);

            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            initialRankName = cursor.getString(cursor.getColumnIndex(DatabaseValues.COLUMN_RANK_NAME));
            initialRankNumber = cursor.getInt(cursor.getColumnIndex(DatabaseValues.COLUMN_INITIAL_RANK_NUMBER));

            abilityMap.put(abilityEnum, new Ability(abilityName, initialRankName,initialRankNumber));
        }


        return abilityMap;
    }

}
