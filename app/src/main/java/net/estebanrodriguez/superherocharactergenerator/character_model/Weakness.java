package net.estebanrodriguez.superherocharactergenerator.character_model;

/**
 * Created by Esteban Rodriguez on 8/19/2016.
 */
public class Weakness {

    String effect;
    String duration;
    String stimulus;

    public Weakness(String effect, String duration, String stimulus) {
        this.effect = effect;
        this.duration = duration;
        this.stimulus = stimulus;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStimulus() {
        return stimulus;
    }

    public void setStimulus(String stimulus) {
        this.stimulus = stimulus;
    }
}
