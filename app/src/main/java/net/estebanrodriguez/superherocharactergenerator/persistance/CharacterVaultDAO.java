package net.estebanrodriguez.superherocharactergenerator.persistance;

import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;

import java.util.List;

/**
 * Created by Esteban Rodriguez on 8/22/2016.
 */
public interface CharacterVaultDAO {

    public List<Character> getAllCharacters();
    public PoweredCharacter getPoweredCharacter();
    public Character getCharacter();
    public void saveCharacter(Character character);
    public void updateCharacter(int characterID);
}

