package net.estebanrodriguez.superherocharactergenerator.character_model;

/**
 * Created by Esteban Rodriguez on 8/11/2016.
 */
public class Power {

    String mPowerClass;
    String mPower;
    String mPowerCode;
    String mPowerDescription;

    public Power(String powerClass, String power, String powerCode) {
        mPowerClass = powerClass;
        mPower = power;
        mPowerCode = powerCode;
    }

    public String getPowerClass() {
        return mPowerClass;
    }

    public void setPowerClass(int d100) {
    }

    public String getPower() {
        return mPower;
    }

    public void setPower(String power) {
        mPower = power;
    }

    public String getPowerDescription() {
        return mPowerDescription;
    }

    public void setPowerDescription(String powerDescription) {
        mPowerDescription = powerDescription;
    }

    public void setPowerClass(String powerClass) {
        mPowerClass = powerClass;
    }

    public String getPowerCode() {
        return mPowerCode;
    }

    public void setPowerCode(String powerCode) {
        mPowerCode = powerCode;
    }
}
