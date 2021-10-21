package com.app.gitsin;

public class HoFAchievement {

    private int maxStreak;
    private String star;
    private String achDrawable;
    private String achTitle;
    private String achDetail;
    private String achMax;
    private String userId;

    public HoFAchievement() {
    }

    public HoFAchievement(int maxStreak, String star, String userId) {
        this.maxStreak = maxStreak;
        this.star = star;
        this.userId = userId;
    }

    public HoFAchievement(String achDrawable, String achTitle, String achDetail, int achMax) {
        this.achDrawable = achDrawable;
        this.achTitle = achTitle;
        this.achDetail = achDetail;
        this.achMax = String.valueOf(achMax);
    }

    public int getMaxStreak() {
        return maxStreak;
    }

    public void setMaxStreak(int maxStreak) {
        this.maxStreak = maxStreak;
    }

    public int getStar() {
        return star.split(",").length;
    }

    public void setStar(String star) {
        this.star = star;
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

    public String getAchMax() {
        return achMax;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAchMax(int achMax) {
        this.achMax = String.valueOf(achMax);
    }


}
