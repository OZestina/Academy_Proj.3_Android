package com.app.gitsin;

public class HoFAchievement {

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


}
