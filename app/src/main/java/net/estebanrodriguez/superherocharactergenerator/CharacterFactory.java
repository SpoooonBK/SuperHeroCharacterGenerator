package net.estebanrodriguez.superherocharactergenerator;

import android.content.Context;

import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.Power;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;
import net.estebanrodriguez.superherocharactergenerator.databasehelper.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

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

    public Character generatePoweredCharacter(){
        PoweredCharacter character = new PoweredCharacter();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);

        //set initial and max amounts
        character.setAmounts(databaseAccess.getAmounts(DieRoller.roll(100)));

        //add powers equaling initial power amount
        List<Power> powers = new ArrayList<>();
        for(int i =0; i < character.getInitialAmountofPowers(); i++){
            powers.add(databaseAccess.getPower(DieRoller.roll(100)));
        }
        character.setPowers(powers);

        //set form
        character.setForm(databaseAccess.getForm(DieRoller.roll(100)));

        return character;
    }



}
