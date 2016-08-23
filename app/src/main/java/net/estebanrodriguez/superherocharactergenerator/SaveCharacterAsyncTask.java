package net.estebanrodriguez.superherocharactergenerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import net.estebanrodriguez.superherocharactergenerator.character_model.Ability;
import net.estebanrodriguez.superherocharactergenerator.character_model.AbilityNamesEnum;
import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.Power;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;
import net.estebanrodriguez.superherocharactergenerator.persistance.CharacterVaultContract;
import net.estebanrodriguez.superherocharactergenerator.persistance.CharacterVaultOpenHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by Esteban Rodriguez on 8/22/2016.
 */
public class SaveCharacterAsyncTask extends AsyncTask<Character, Void, Long> {

    Context mContext;
    SQLiteDatabase mDatabase;

    public SaveCharacterAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected Long doInBackground(Character... characters) {
        PoweredCharacter character = (PoweredCharacter)characters[0];
        CharacterVaultOpenHelper vaultOpenHelper = new CharacterVaultOpenHelper(mContext);
        mDatabase = vaultOpenHelper.getWritableDatabase();
        return savePoweredCharacter(character);
    }

    private long savePoweredCharacter(PoweredCharacter character){

        ContentValues values = new ContentValues();
//
//        String name = character.getCharacterName();
//        String form = character.getForm().getFormType();
//        String subform = character.getForm().getSubFormType();

        values.put(CharacterVaultContract.CharacterTable.COLUMN_NAME, character.getCharacterName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_FORM, character.getForm().getFormType());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_SUBFORM, character.getForm().getSubFormType());

        Map<AbilityNamesEnum, Ability> abilityMap = character.getAbilityMap();
        Ability fighting = abilityMap.get(AbilityNamesEnum.FIGHTING);
        Ability agility = abilityMap.get(AbilityNamesEnum.AGILITY);
        Ability strength = abilityMap.get(AbilityNamesEnum.STRENGTH);
        Ability endurance = abilityMap.get(AbilityNamesEnum.ENDURANCE);
        Ability reason  = abilityMap.get(AbilityNamesEnum.REASON);
        Ability intuition = abilityMap.get(AbilityNamesEnum.INTUITION);
        Ability psyche = abilityMap.get(AbilityNamesEnum.PSYCHE);


        
        values.put(CharacterVaultContract.CharacterTable.COLUMN_FIGHTING_INITIAL_RANK_NAME, fighting.getInitialRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_FIGHTING_CURRENT_RANK_NAME, fighting.getCurrentRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_FIGHTING_INITIAL_RANK_NUMBER, fighting.getInitialRankNumber());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_FIGHTING_CURRENT_RANK_NUMBER,fighting.getCurrentranknumber());

        values.put(CharacterVaultContract.CharacterTable.COLUMN_AGILITY_INITIAL_RANK_NAME, agility.getInitialRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_AGILITY_CURRENT_RANK_NAME, agility.getCurrentRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_AGILITY_INITIAL_RANK_NUMBER, agility.getInitialRankNumber());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_AGILITY_CURRENT_RANK_NUMBER,agility.getCurrentranknumber());

        values.put(CharacterVaultContract.CharacterTable.COLUMN_STRENGTH_INITIAL_RANK_NAME, strength.getInitialRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_STRENGTH_CURRENT_RANK_NAME, strength.getCurrentRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_STRENGTH_INITIAL_RANK_NUMBER, strength.getInitialRankNumber());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_STRENGTH_CURRENT_RANK_NUMBER,strength.getCurrentranknumber());

        values.put(CharacterVaultContract.CharacterTable.COLUMN_ENDURANCE_INITIAL_RANK_NAME, endurance.getInitialRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_ENDURANCE_CURRENT_RANK_NAME, endurance.getCurrentRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_ENDURANCE_INITIAL_RANK_NUMBER, endurance.getInitialRankNumber());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_ENDURANCE_CURRENT_RANK_NUMBER,endurance.getCurrentranknumber());

        values.put(CharacterVaultContract.CharacterTable.COLUMN_REASON_INITIAL_RANK_NAME, reason.getInitialRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_REASON_CURRENT_RANK_NAME, reason.getCurrentRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_REASON_INITIAL_RANK_NUMBER, reason.getInitialRankNumber());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_REASON_CURRENT_RANK_NUMBER,reason.getCurrentranknumber());

        values.put(CharacterVaultContract.CharacterTable.COLUMN_INTUITION_INITIAL_RANK_NAME, intuition.getInitialRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_INTUITION_CURRENT_RANK_NAME, intuition.getCurrentRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_INTUITION_INITIAL_RANK_NUMBER, intuition.getInitialRankNumber());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_INTUITION_CURRENT_RANK_NUMBER,intuition.getCurrentranknumber());

        values.put(CharacterVaultContract.CharacterTable.COLUMN_PSYCHE_INITIAL_RANK_NAME, psyche.getInitialRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_PSYCHE_CURRENT_RANK_NAME, psyche.getCurrentRankName());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_PSYCHE_INITIAL_RANK_NUMBER, psyche.getInitialRankNumber());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_PSYCHE_CURRENT_RANK_NUMBER,psyche.getCurrentranknumber());
        

//        String agilityInitialRank = agility.getCurrentRankName();
//        String agilityCurrentRank = agility.getCurrentRankName();
//        int agilityInitialRankNumber = agility.getInitialRankNumber();
//        int agilityCurrentRankNUmber = agility.getCurrentranknumber();
//
//        String strengthInitialRank = strength.getCurrentRankName();
//        String strengthCurrentRank = strength.getCurrentRankName();
//        int strengthInitialRankNumber = strength.getInitialRankNumber();
//        int strengthCurrentRankNUmber = strength.getCurrentranknumber();
//
//        String enduranceInitialRank = endurance.getCurrentRankName();
//        String enduranceCurrentRank = endurance.getCurrentRankName();
//        int enduranceInitialRankNumber = endurance.getInitialRankNumber();
//        int enduranceCurrentRankNUmber = endurance.getCurrentranknumber();
//
//        String reasonInitialRank = reason.getCurrentRankName();
//        String reasonCurrentRank = reason.getCurrentRankName();
//        int reasonInitialRankNumber = reason.getInitialRankNumber();
//        int reasonCurrentRankNUmber = reason.getCurrentranknumber();
//
//        String intuitionInitialRank = intuition.getCurrentRankName();
//        String intuitionCurrentRank = intuition.getCurrentRankName();
//        int intuitionInitialRankNumber = intuition.getInitialRankNumber();
//        int intuitionCurrentRankNUmber = intuition.getCurrentranknumber();
//
//        String psycheInitialRank = psyche.getCurrentRankName();
//        String psycheCurrentRank = psyche.getCurrentRankName();
//        int psycheInitialRankNumber = psyche.getInitialRankNumber();
//        int psycheCurrentRankNUmber = psyche.getCurrentranknumber();
//
//        int initialAmountofPowers = character.getInitialAmountofPowers();
//        int currentAmountofPowers = character.getCurrentAmountofPowers();
//        int maxAmountofPowers = character.getMaxAmountofPowers();
        
        values.put(CharacterVaultContract.CharacterTable.COLUMN_POWERS_INITIAL_AMOUNT,character.getInitialAmountofPowers());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_POWERS_MAX_AMOUNT,character.getMaxAmountofPowers());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_POWERS_CURRENT_AMOUNT, character.getCurrentAmountofPowers());

//        int initialAmountofContacts = character.getInitialAmountofContacts();
//        int currentAmountofContacts = character.getCurrentAmountofContacts();
//        int maxAmountofContacts = character.getMaxAmountofContacts();

        values.put(CharacterVaultContract.CharacterTable.COLUMN_CONTACTS_INITIAL_AMOUNT,character.getInitialAmountofContacts());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_CONTACTS_MAX_AMOUNT,character.getMaxAmountofContacts());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_CONTACTS_CURRENT_AMOUNT, character.getCurrentAmountofContacts());

//        int initialAmountofTalents = character.getInitialAmountofTalents();
//        int currentAmountofTalents = character.getCurrentAmountofTalents();
//        int maxAmountofTalents = character.getMaxAmountofTalents();

        values.put(CharacterVaultContract.CharacterTable.COLUMN_TALENTS_INITIAL_AMOUNT,character.getInitialAmountofTalents());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_TALENTS_MAX_AMOUNT,character.getMaxAmountofTalents());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_TALENTS_CURRENT_AMOUNT, character.getCurrentAmountofTalents());

//        int currentHealth = character.getCurrentHealth();
//        int currentKarma = character.getCurrentKarma();

        values.put(CharacterVaultContract.CharacterTable.COLUMN_HEALTH_CURRENT, character.getCurrentHealth());
        values.put(CharacterVaultContract.CharacterTable.COLUMN_KARMA_CURRENT, character.getCurrentKarma());

//        String origin = character.getOrigin().getOriginString();

        values.put(CharacterVaultContract.CharacterTable.COLUMN_ORIGIN, character.getOrigin().getOriginString());

        List<Power> powers = character.getPowers();
        int count = 0;
        for(Power power: powers){
            String powerNameColumn = "power_name_" + count;
            String powerClassColumn = "power_class_" + count;
            String powerCodeColumn = "power_code_" + count;
//            String powerDescriptionColumn = "power_description_"+ count;

//            String powerName = power.getPowerName();
//            String powerClass = power.getPowerClass();
//            String powerCode = power.getPowerCode();

            values.put(powerNameColumn, power.getPowerName());
            values.put(powerClassColumn, power.getPowerClass());
            values.put(powerCodeColumn, power.getPowerCode());
//            String powerDescription = power.getPowerDescription();

        }

        return mDatabase.insert(CharacterVaultContract.CharacterTable.CHARACTER_TABLE, null, values);

    }


}
