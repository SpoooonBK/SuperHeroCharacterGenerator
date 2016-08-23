package net.estebanrodriguez.superherocharactergenerator.character_model;

/**
 * Created by Tara on 8/11/2016.
 */
public class Origin {
    private String mOrigin;
    private String mOriginDescription;

    public Origin(String origin) {
        mOrigin = origin;
    }

    public String getOriginString() {
        return mOrigin;
    }

    public void setOrigin(String origin) {
        this.mOrigin = origin;
    }

    public String getOriginDescription() {
        return mOriginDescription;
    }

    public void setOriginDescription(String originDescription) {
        this.mOriginDescription = originDescription;
    }
}
