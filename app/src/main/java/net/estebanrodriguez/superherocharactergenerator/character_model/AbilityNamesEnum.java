package net.estebanrodriguez.superherocharactergenerator.character_model;

/**
 * Created by Esteban Rodriguez on 8/18/2016.
 */
public enum AbilityNamesEnum {
    FIGHTING("Fighting"),
    AGILITY("Agility"),
    STRENGTH("Strength"),
    ENDURANCE("Endurance"),
    REASON("Reason"),
    INTUITION("Intuition"),
    PSYCHE("Psyche");

    private final String abilityName;

    AbilityNamesEnum(String abilityName){
        this.abilityName =abilityName;
    }

    public String getAbilityName() {
        return abilityName;
    }
}
