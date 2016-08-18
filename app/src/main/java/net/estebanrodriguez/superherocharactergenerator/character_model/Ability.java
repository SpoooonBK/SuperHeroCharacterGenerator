package net.estebanrodriguez.superherocharactergenerator.character_model;

/**
 * Created by Esteban Rodriguez on 8/18/2016.
 */
public class Ability {

    private String mAbilityName;
    private String mInitialRankName;
    private String mCurrentRankName;
    private int mInitialRankNumber;
    private int mCurrentranknumber;

    public Ability(String abilityName, String initialRankName, int initialRankNumber) {
        mAbilityName = abilityName;
        mInitialRankName = initialRankName;
        mCurrentRankName = initialRankName;
        mInitialRankNumber = initialRankNumber;
        mCurrentranknumber = initialRankNumber;
    }

    public String getAbilityName() {
        return mAbilityName;
    }

    public void setAbilityName(String abilityName) {
        mAbilityName = abilityName;
    }

    public String getInitialRankName() {
        return mInitialRankName;
    }

    public void setInitialRankName(String initialRankName) {
        mInitialRankName = initialRankName;
    }

    public String getCurrentRankName() {
        return mCurrentRankName;
    }

    public void setCurrentRankName(String currentRankName) {
        mCurrentRankName = currentRankName;
    }

    public int getInitialRankNumber() {
        return mInitialRankNumber;
    }

    public void setInitialRankNumber(int initialRankNumber) {
        mInitialRankNumber = initialRankNumber;
    }

    public int getCurrentranknumber() {
        return mCurrentranknumber;
    }

    public void setCurrentranknumber(int currentranknumber) {
        mCurrentranknumber = currentranknumber;
    }
}
