package net.estebanrodriguez.superherocharactergenerator.character_model;

import net.estebanrodriguez.superherocharactergenerator.roll_tables_database.RollTablesContract;

import java.util.List;
import java.util.Map;

/**
 * Created by Tara on 8/11/2016.
 */
public class PoweredCharacter extends Character {
    List<Power> mPowers;
    Origin mOrigin;
    Map<AbilityNamesEnum, Ability> mAbilityMap;
    Weakness mWeakness;

    int mInitialAmountofPowers;
    int mCurrentAmountofPowers;
    int mMaxAmountofPowers;

    int mInitialAmountofTalents;
    int mCurrentAmountOfTalents;
    int mMaxAmountofTalents;

    int mInitialAmountofContacts;
    int mCurrentAmountofContacts;
    int mMaxAmountofContacts;

    int mMaxHealth;
    int mCurrentHealth;

    int mMaxKarma;
    int mCurrentKarma;

    public List<Power> getPowers() {
        return mPowers;
    }


    public void setAmounts(Map<String, Integer> amounts){
        setInitialAmountofPowers(amounts.get(RollTablesContract.COLUMN_POWERS_INITIAL));
        setMaxAmountofPowers(amounts.get(RollTablesContract.COLUMN_POWERS_MAX));
        setCurrentAmountofPowers(mInitialAmountofPowers);

        setInitialAmountofTalents(amounts.get(RollTablesContract.COLUMN_TALENTS_INITIAL));
        setMaxAmountofTalents(amounts.get(RollTablesContract.COLUMN_TALENTS_MAX));
        setCurrentAmountOfTalents(mInitialAmountofTalents);

        setInitialAmountofContacts(amounts.get(RollTablesContract.COLUMN_CONTACTS_INITIAL));
        setMaxAmountofContacts(amounts.get(RollTablesContract.COLUMN_CONTACTS_MAX));
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

    public int getCurrentAmountofTalents() {
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

    public Weakness getWeakness() {
        return mWeakness;
    }

    public void setWeakness(Weakness weakness) {
        mWeakness = weakness;
    }

    public int getMaxHealth() {
        return mMaxHealth;
    }


    public void setMaxHealth() {

        int fightingRank = mAbilityMap.get(AbilityNamesEnum.FIGHTING).getCurrentranknumber();
        int agilityRank = mAbilityMap.get(AbilityNamesEnum.AGILITY).getCurrentranknumber();
        int strengthRank = mAbilityMap.get(AbilityNamesEnum.STRENGTH).getCurrentranknumber();
        int enduranceRank = mAbilityMap.get(AbilityNamesEnum.ENDURANCE).getCurrentranknumber();

        mMaxHealth= fightingRank + agilityRank + strengthRank + enduranceRank;
        setCurrentHealth(mMaxHealth);
    }

    public int getCurrentHealth() {
        return mCurrentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        mCurrentHealth = currentHealth;
    }

    public int getMaxKarma() {
        return mMaxKarma;
    }

    public void setMaxKarma() {
        int reasonRank = mAbilityMap.get(AbilityNamesEnum.REASON).getCurrentranknumber();
        int intuitionRank = mAbilityMap.get(AbilityNamesEnum.INTUITION).getCurrentranknumber();
        int psycheRank = mAbilityMap.get(AbilityNamesEnum.PSYCHE).getCurrentranknumber();
        mMaxKarma = reasonRank + intuitionRank + psycheRank;
        setCurrentKarma(mMaxKarma);
    }

    public int getCurrentKarma() {
        return mCurrentKarma;
    }

    public void setCurrentKarma(int currentKarma) {
        mCurrentKarma = currentKarma;
    }
}


