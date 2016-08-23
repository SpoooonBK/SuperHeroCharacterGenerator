package net.estebanrodriguez.superherocharactergenerator.character_model;

import java.io.Serializable;

/**
 * Created by Tara on 8/10/2016.
 */
public class Character implements Serializable{

    private int mCharacterID;
    private String mCharacterName;
    private PhysicalForm form;

    public Character(){

    }

    public Character(String characterName) {
        mCharacterName = characterName;
    }

    public int getCharacterID() {
        return mCharacterID;
    }

    public void setCharacterID(int characterID) {
        this.mCharacterID = characterID;
    }

    public String getCharacterName() {
        return mCharacterName;
    }

    public void setCharacterName(String characterName) {
        this.mCharacterName = characterName;
    }

    public PhysicalForm getForm() {
        return form;
    }

    public void setForm(PhysicalForm form) {
        this.form = form;
    }
}
