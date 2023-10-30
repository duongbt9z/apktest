package com.example.apktest;

import java.io.Serializable;

public class SanPham implements Serializable {
    String maSP;
    String tenSP;
    double giaSP;
    String logoSP;

    public  SanPham(){}

    public SanPham(String maSP, String tenSP, double giaSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
//        this.logoSP = logoSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setGiaSP(double giaSP) {
        this.giaSP = giaSP;
    }

    public void setLogoSP(String logoSP) {
        this.logoSP = logoSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public double getGiaSP() {
        return giaSP;
    }

    public String getLogoSP() {
        return logoSP;
    }
}