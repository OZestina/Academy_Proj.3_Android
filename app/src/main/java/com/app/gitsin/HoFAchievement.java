package com.app.gitsin;

public class HoFAchievement {

    private int momo;
    private int friends;
    private String achDrawable;
    private String achTitle;
    private String achDetail;
    private int achMax;

    public HoFAchievement() {
    }

    public HoFAchievement(String achDrawable, String achTitle, String achDetail, int achMax) {
        this.achDrawable = achDrawable;
        this.achTitle = achTitle;
        this.achDetail = achDetail;
        this.achMax = achMax;
    }

    public HoFAchievement(int momo, int friends) {
        this.momo = momo;
        this.friends = friends;
    }

    public int getMomo() {
        return momo;
    }

    public void setMomo(int momo) {
        this.momo = momo;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public String getAchDrawable() {
        return achDrawable;
    }

    public void setAchDrawable(String achDrawable) {
        this.achDrawable = achDrawable;
    }

    public String getAchTitle() {
        return achTitle;
    }

    public void setAchTitle(String achTitle) {
        this.achTitle = achTitle;
    }

    public String getAchDetail() {
        return achDetail;
    }

    public void setAchDetail(String achDetail) {
        this.achDetail = achDetail;
    }

    public int getAchMax() {
        return achMax;
    }

    public void setAchMax(int achMax) {
        this.achMax = achMax;
    }

    @Override
    public String toString() {
        return "HoFAchievement{" +
                "momo=" + momo +
                ", friends=" + friends +
                ", achDrawable='" + achDrawable + '\'' +
                ", achTitle='" + achTitle + '\'' +
                ", achDetail='" + achDetail + '\'' +
                ", achMax=" + achMax +
                '}';
    }
}
