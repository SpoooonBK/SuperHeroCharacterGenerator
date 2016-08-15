package net.estebanrodriguez.superherocharactergenerator.character_model;

import net.estebanrodriguez.superherocharactergenerator.DieRoller;
import net.estebanrodriguez.superherocharactergenerator.databasehelper.DatabaseAccess;

import java.util.List;

/**
 * Created by Tara on 8/11/2016.
 */
public class PoweredCharacter extends Character {
    List<Power> mPowers;

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


    public void setAmounts(List<Integer> amounts){
        setInitialAmountofPowers(amounts.get(0));
        setMaxAmountofPowers(amounts.get(1));
        setCurrentAmountofPowers(mInitialAmountofPowers);

        setInitialAmountofTalents(amounts.get(2));
        setMaxAmountofTalents(amounts.get(3));
        setCurrentAmountOfTalents(mInitialAmountofTalents);

        setInitialAmountofContacts(amounts.get(4));
        setMaxAmountofContacts(amounts.get(5));
        setCurrentAmountofContacts(mInitialAmountofContacts);
    }

    public void setPowers(List<Power> powers) {
        mPowers = powers;
    }

    public int getInitialAmountofPowers() {
        return mInitialAmountofPowers;
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
}
