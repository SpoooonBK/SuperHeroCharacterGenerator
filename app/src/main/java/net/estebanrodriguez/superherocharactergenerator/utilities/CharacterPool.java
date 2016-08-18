package net.estebanrodriguez.superherocharactergenerator.utilities;

import net.estebanrodriguez.superherocharactergenerator.CharacterFactory;
import net.estebanrodriguez.superherocharactergenerator.CharacterRollerAsyncTask;
import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Esteban Rodriguez on 8/18/2016.
 */
public class CharacterPool {


    private List<PoweredCharacter> mPoweredCharacters = new ArrayList<>();
    private CharacterFactory mCharacterFactory;
    private boolean isFull = false;

    private CharacterPool(){
    }

    private static class CharacterPoolHelper{
        private static final CharacterPool instance = new CharacterPool();
    }

    public static CharacterPool getInstance(){
        return CharacterPoolHelper.instance;
    }

    public Boolean checkIfFull(){
        return isFull;
    }

    public void fillPoweredCharacterPool(CharacterFactory characterFactory){
        mCharacterFactory = characterFactory;
            mPoweredCharacters.add((mCharacterFactory.generatePoweredCharacter()));
        isFull = true;
    }

    public void fillPoweredCharacterPool(int numberOfCharacters) {
        for (int i = 0; i < numberOfCharacters; i++) {

        }
    }

    public PoweredCharacter getCharacter(){
        PoweredCharacter poweredCharacter = null;
        if(mPoweredCharacters.size()>0) {
            poweredCharacter = mPoweredCharacters.get(0);
            mPoweredCharacters.remove(0);
        }
        return poweredCharacter;
    }

    public void replenishPoweredCharacterPool(){
        mPoweredCharacters.add(mCharacterFactory.generatePoweredCharacter());
    }

    public int getSize(){
        return mPoweredCharacters.size();
    }

}
