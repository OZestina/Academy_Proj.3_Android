package com.app.gitsin;

public class ChaListItem {

    private String chaName;
    private String chaCategory;
    private String chaStartDate;
    private String chaPart;
    //private int chaBtn;

    public ChaListItem(String chaName, String chaCategory, String chaStartDate, String chaPart) {
        this.chaName = chaName;
        this.chaCategory = chaCategory;
        this.chaStartDate = chaStartDate;
        this.chaPart = chaPart;
        //this.chaBtn = chaBtn;
    }

    public String getChaName() {
        return chaName;
    }

    public void setChaName(String chaName) {
        this.chaName = chaName;
    }

    public String getChaCategory() {
        return chaCategory;
    }

    public void setChaCategory(String chaCategory) {
        this.chaCategory = chaCategory;
    }

    public String getChaStartDate() {
        return chaStartDate;
    }

    public void setChaStartDate(String chaStartDate) {
        this.chaStartDate = chaStartDate;
    }

    public String getChaPart() {
        return chaPart;
    }

    public void setChaPart(String chaPart) {
        this.chaPart = chaPart;
    }

//    public int getChaBtnBtn() {
//        return chaBtn;
//    }
//
//    public void setChaBtn(int chaBtn) {
//        this.chaBtn = chaBtn;
//    }


}
