package com.app.gitsin;

public class HoFListItem {

    private int achImage;
    private String achTitle;
    private String achDetail;
    private int achProgress;
    private int achMax;

    public HoFListItem(int achImage, String achTitle, String achDetail, int achProgress, int achMax) {
        this.achImage = achImage;
        this.achTitle = achTitle;
        this.achDetail = achDetail;
        this.achProgress = achProgress;
        this.achMax = achMax;
    }

    public int getAchImage() {
        return achImage;
    }

    public void setAchImage(int achImage) {
        this.achImage = achImage;
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

    public int getAchProgress() {
        return achProgress;
    }

    public void setAchProgress(int achProgress) {
        this.achProgress = achProgress;
    }

    public int getAchMax() {
        return achMax;
    }

    public void setAchMax(int achMax) {
        this.achMax = achMax;
    }
}
