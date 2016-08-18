package net.estebanrodriguez.superherocharactergenerator.character_model;

import net.estebanrodriguez.superherocharactergenerator.databasehelper.DatabaseValues;

import java.util.List;
import java.util.Map;

/**
 * Created by Tara on 8/11/2016.
 */
public class PoweredCharacter extends Character {
    List<Power> mPowers;
    Origin mOrigin;
    Map<AbilityNamesEnum, Ability> mAbilityMap;

    int mInitialAmountofPowers;
    int mCurrentAmountofPowers;
    int mMaxAmountofPowers;

    int mInitialAmountofTalents;
    int mCurrentAmountOfTalents;
    int mMaxAmountofTalents;

    int mInitialAmountofContacts;
    int mCurrentAmountofContacts;
    int mMaxAmountofContacts;

    public List<Power> getPowers() {
        return mPowers;
    }


    public void setAmounts(Map<String, Integer> amounts){
        setInitialAmountofPowers(amounts.get(DatabaseValues.COLUMN_POWERS_INITIAL));
        setMaxAmountofPowers(amounts.get(DatabaseValues.COLUMN_POWERS_MAX));
        setCurrentAmountofPowers(mInitialAmountofPowers);

        setInitialAmountofTalents(amounts.get(DatabaseValues.COLUMN_TALENTS_INITIAL));
        setMaxAmountofTalents(amounts.get(DatabaseValues.COLUMN_TALENTS_MAX));
        setCurrentAmountOfTalents(mInitialAmountofTalents);

        setInitialAmountofContacts(amounts.get(DatabaseValues.COLUMN_CONTACTS_INITIAL));
        setMaxAmountofContacts(amounts.get(DatabaseValues.COLUMN_CONTACTS_MAX));
        setCurrentAmountofContacts(mInitialAmountofContacts);
    }

    public void setPowers(List<Power> powers) {
        mPowers = powers;
    }

    public int getInitialAmountofPowers() {
        return mInitialAmountofPowers;
    }

    public Origin getOrigin() {
        return mOrigin;
    }

    public void setOrigin(Origin origin) {
        mOrigin = origin;
    }

    public void setInitialAmountofPowers(int initialAmountofPowers) {
        mInitialAmountofPowers = initialAmountofPowers;
    }

    public int getCurrentAmountofPowers() {
        return mCurrentAmountofPowers;
    }

    public void setCurrentAmountofPowers(int currentAmountofPowers) {
        mCurrentAmountofPowers = currentAmountofPowers;
    }

    public int getMaxAmountofPowers() {
        return mMaxAmountofPowers;
    }

    public void setMaxAmountofPowers(int maxAmountofPowers) {
        mMaxAmountofPowers = maxAmountofPowers;
    }

    public int getInitialAmountofTalents() {
        return mInitialAmountofTalents;
    }

    public void setInitialAmountofTalents(int initialAmountofTalents) {
        mInitialAmountofTalents = initialAmountofTalents;
    }

    public int getCurrentAmountOfTalents() {
        return mCurrentAmountOfTalents;
    }

    public void setCurrentAmountOfTalents(int currentAmountOfTalents) {
        mCurrentAmountOfTalents = currentAmountOfTalents;
    }

    public int getMaxAmountofTalents() {
        return mMaxAmountofTalents;
    }

    public void setMaxAmountofTalents(int maxAmountofTalents) {
        mMaxAmountofTalents = maxAmountofTalents;
    }

    public int getInitialAmountofContacts() {
        return mInitialAmountofContacts;
    }

    public void setInitialAmountofContacts(int initialAmountofContacts) {
        mInitialAmountofContacts = initialAmountofContacts;
    }

    public int getCurrentAmountofContacts() {
        return mCurrentAmountofContacts;
    }

    public void setCurrentAmountofContacts(int currentAmountofContacts) {
        mCurrentAmountofContacts = currentAmountofContacts;
    }

    public int getMaxAmountofContacts() {
        return mMaxAmountofContacts;
    }

    public void setMaxAmountofContacts(int maxAmountofContacts) {
        mMaxAmountofContacts = maxAmountofContacts;
    }

    public Map<AbilityNamesEnum, Ability> getAbilityMap() {
        return mAbilityMap;
    }

    public void setAbilityMap(Map<AbilityNamesEnum, Ability> abilityMap) {
        mAbilityMap = abilityMap;
    }
}
