package com.example.assignmentandroid.model;

public class DAOPhongBan {
    private int img;
    private String txtName;

    public DAOPhongBan(int img, String txtName) {
        this.img = img;
        this.txtName = txtName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }
}
