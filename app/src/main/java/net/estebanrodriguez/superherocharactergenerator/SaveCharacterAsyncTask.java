package net.estebanrodriguez.superherocharactergenerator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import net.estebanrodriguez.superherocharactergenerator.character_model.Ability;
import net.estebanrodriguez.superherocharactergenerator.character_model.AbilityNamesEnum;
import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;
import net.estebanrodriguez.superherocharactergenerator.persistance.CharacterVaultDAO;
import net.estebanrodriguez.superherocharactergenerator.persistance.CharacterVaultOpenHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.util.Map;

/**
 * Created by Esteban Rodriguez on 8/22/2016.
 */
public class SaveCharacterAsyncTask extends AsyncTask<Character, Void, Boolean> {

    Context mContext;

    public SaveCharacterAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected Boolean doInBackground(Character... characters) {

        CharacterVaultOpenHelper vaultOpenHelper = new CharacterVaultOpenHelper(mContext);

        SQLiteDatabase database = vaultOpenHelper.getWritableDatabase();

        return null;
    }

    private void saveCharacter(PoweredCharacter character){

        String name = character.getCharacterName();
        String form = character.getForm().getFormType();
        String subform = character.getForm().getSubFormType();

        Map<AbilityNamesEnum, Ability> abilityMap = character.getAbilityMap();
        Ability fighting = abilityMap.get(AbilityNamesEnum.FIGHTING);
        Ability agility = abilityMap.get(AbilityNamesEnum.AGILITY);
        Ability strength = abilityMap.get(AbilityNamesEnum.STRENGTH);
        Ability endurance = abilityMap.get(AbilityNamesEnum.ENDURANCE);
        Ability reason  = abilityMap.get(AbilityNamesEnum.REASON);
        Ability intuition = abilityMap.get(AbilityNamesEnum.INTUITION);
        Ability psyche = abilityMap.get(AbilityNamesEnum.PSYCHE);

        String fightingInitialRank = fighting.getCurrentRankName();
        String fightingCurrentRank = fighting.getCurrentRankName();
        int fightingInitialRankNumber = fighting.getInitialRankNumber();
        int fightingCurrentRankNUmber = fighting.getCurrentranknumber();

        String agilityInitialRank = agility.getCurrentRankName();
        String agilityCurrentRank = agility.getCurrentRankName();
        int agilityInitialRankNumber = agility.getInitialRankNumber();
        int agilityCurrentRankNUmber = agility.getCurrentranknumber();

        String strengthInitialRank = strength.getCurrentRankName();
        String strengthCurrentRank = strength.getCurrentRankName();
        int strengthInitialRankNumber = strength.getInitialRankNumber();
        int strengthCurrentRankNUmber = strength.getCurrentranknumber();

        String enduranceInitialRank = endurance.getCurrentRankName();
        String enduranceCurrentRank = endurance.getCurrentRankName();
        int enduranceInitialRankNumber = endurance.getInitialRankNumber();
        int enduranceCurrentRankNUmber = endurance.getCurrentranknumber();

        String reasonInitialRank = reason.getCurrentRankName();
        String reasonCurrentRank = reason.getCurrentRankName();
        int reasonInitialRankNumber = reason.getInitialRankNumber();
        int reasonCurrentRankNUmber = reason.getCurrentranknumber();

        String intuitionInitialRank = intuition.getCurrentRankName();
        String intuitionCurrentRank = intuition.getCurrentRankName();
        int intuitionInitialRankNumber = intuition.getInitialRankNumber();
        int intuitionCurrentRankNUmber = intuition.getCurrentranknumber();

        String psycheInitialRank = psyche.getCurrentRankName();
        String psycheCurrentRank = psyche.getCurrentRankName();
        int psycheInitialRankNumber = psyche.getInitialRankNumber();
        int psycheCurrentRankNUmber = psyche.getCurrentranknumber();




    }


}
