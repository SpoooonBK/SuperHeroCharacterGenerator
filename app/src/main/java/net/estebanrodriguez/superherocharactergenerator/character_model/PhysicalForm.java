package net.estebanrodriguez.superherocharactergenerator.character_model;

/**
 * Created by Tara on 8/10/2016.
 */
public class PhysicalForm {

    private int mFormID;
    private String mFormType;
    private String mSubFormType;
    private String mFormdescription;

    public PhysicalForm(String formType, String subFormType) {
        mFormType = formType;
        mSubFormType = subFormType;
    }
}
