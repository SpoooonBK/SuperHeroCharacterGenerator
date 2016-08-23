package net.estebanrodriguez.superherocharactergenerator.persistence;

import android.content.Context;

import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
        GetCharacterListTask getCharacterListTask = new GetCharacterListTask();
        getCharacterListTask.execute(mContext);

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
    public long saveCharacter(Character character) throws ExecutionException, InterruptedException {
        SaveCharacterAsyncTask saveCharacterAsyncTask = new SaveCharacterAsyncTask(mContext);
        saveCharacterAsyncTask.execute(character);

        //REMOVE
        getAllCharacters();
        //REMOVE

        return saveCharacterAsyncTask.get();
    }

    @Override
    public void updateCharacter(int characterID) {

    }
}
