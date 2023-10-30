package com.example.assignmentandroid.model;

import java.io.Serializable;

public class DAONhanVien implements Serializable {
    private String maNV,tenNV,phongBan;

    public DAONhanVien(String maNV, String tenNV, String phongBan) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.phongBan = phongBan;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }
}
