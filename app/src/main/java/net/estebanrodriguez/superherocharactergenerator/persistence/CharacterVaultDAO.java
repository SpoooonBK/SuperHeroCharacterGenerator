package net.estebanrodriguez.superherocharactergenerator.persistence;

import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Esteban Rodriguez on 8/22/2016.
 */
public interface CharacterVaultDAO {

    public List<Character> getAllCharacters();
    public PoweredCharacter getPoweredCharacter();
    public Character getCharacter();
    public long saveCharacter(Character character) throws ExecutionException, InterruptedException;
    public void updateCharacter(int characterID);
}

