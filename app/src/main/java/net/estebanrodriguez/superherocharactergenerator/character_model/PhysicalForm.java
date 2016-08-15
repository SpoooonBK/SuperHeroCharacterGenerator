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

    public int getFormID() {
        return mFormID;
    }

    public void setFormID(int formID) {
        mFormID = formID;
    }

    public String getFormType() {
        return mFormType;
    }

    public void setFormType(String formType) {
        mFormType = formType;
    }

    public String getSubFormType() {
        return mSubFormType;
    }

    public void setSubFormType(String subFormType) {
        mSubFormType = subFormType;
    }

    public String getFormdescription() {
        return mFormdescription;
    }

    public void setFormdescription(String formdescription) {
        mFormdescription = formdescription;
    }
}
