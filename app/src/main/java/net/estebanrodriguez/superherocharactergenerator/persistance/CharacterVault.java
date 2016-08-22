package net.estebanrodriguez.superherocharactergenerator.persistance;

import android.content.Context;

import net.estebanrodriguez.superherocharactergenerator.CharacterRollerAsyncTask;
import net.estebanrodriguez.superherocharactergenerator.SaveCharacterAsyncTask;
import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;

import java.util.List;

/**
 * Created by Esteban Rodriguez on 8/22/2016.
 */
public class CharacterVault implements CharacterVaultDAO {

    Context mContext;

    public CharacterVault(Context context) {
        mContext = context;
    }

    @Override
    public List<Character> getAllCharacters() {
        return null;
    }

    @Override
    public PoweredCharacter getPoweredCharacter() {
        return null;
    }

    @Override
    public Character getCharacter() {
        return null;
    }

    @Override
    public void saveCharacter(Character character) {
        SaveCharacterAsyncTask saveCharacterAsyncTask = new SaveCharacterAsyncTask(mContext);
        saveCharacterAsyncTask.execute(character);
    }

    @Override
    public void updateCharacter(int characterID) {

    }
}
