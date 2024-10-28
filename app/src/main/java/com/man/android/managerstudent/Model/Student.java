package com.man.android.managerstudent.Model;

public class Student {
    private String mID;
    private String mName;
    private String mCMND;
    private String mLevel;
    private String mNoted;

    public Student() {
    }


    public Student(String mID, String mName, String mCMND, String mLevel, String mNoted) {
        this.mID = mID;
        this.mName = mName;
        this.mCMND = mCMND;
        this.mLevel = mLevel;
        this.mNoted = mNoted;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCMND() {
        return mCMND;
    }

    public void setmCMND(String mCMND) {
        this.mCMND = mCMND;
    }

    public String getmLevel() {
        return mLevel;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }

    public String getmNoted() {
        return mNoted;
    }

    public void setmNoted(String mNoted) {
        this.mNoted = mNoted;
    }
}
