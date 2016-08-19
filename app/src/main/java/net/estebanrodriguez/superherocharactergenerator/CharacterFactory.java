package net.estebanrodriguez.superherocharactergenerator;

import android.content.Context;
import android.util.Log;

import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.Power;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;
import net.estebanrodriguez.superherocharactergenerator.databasehelper.DatabaseAccess;
import net.estebanrodriguez.superherocharactergenerator.utilities.DieRoller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Tara on 8/11/2016.
 */
public class CharacterFactory {

    private Context mContext;

    public CharacterFactory(Context context){
        mContext = context;
    }

    public Character generateNPC(){

        Character character = new Character();

        return character;
    }

    public PoweredCharacter generatePoweredCharacter(){
        PoweredCharacter character = new PoweredCharacter();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
        databaseAccess.open();
        Log.d("HERO","databaseAccess created");

        //set Name
        character.setCharacterName(databaseAccess.rollName());

        //set form
        character.setForm(databaseAccess.rollForm(DieRoller.roll(100)));

        //set origin
        character.setOrigin(databaseAccess.rollOrigin(DieRoller.roll(100)));

        //set initial and max amounts
        character.setAmounts(databaseAccess.rollAmounts(DieRoller.roll(100)));

        //add powers equaling initial power amount
        List<Power> powers = new ArrayList<>();
        for(int i =0; i < character.getInitialAmountofPowers(); i++){
            Map<String, String> map = databaseAccess.rollPowerClass(DieRoller.roll(100));
            powers.add(databaseAccess.rollPower(DieRoller.roll(100), map));
        }
        character.setPowers(powers);

        character.setAbilityMap(databaseAccess.rollAbilities(character.getForm().getRandomRanksRollColumn()));

        character.setWeakness(databaseAccess.rollWeakness());
        character.setMaxHealth();
        character.setMaxKarma();

        databaseAccess.close();
        return character;
    }



}
