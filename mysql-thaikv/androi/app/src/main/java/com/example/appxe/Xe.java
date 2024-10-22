package com.example.appxe;

public class Xe {
    String tenxe;
    String hangsx;
    int namsx;
    String hinh;
    public Xe(String tenxe, String hangsx, int namsx, String hinh) {
        this.tenxe = tenxe;
        this.hangsx = hangsx;
        this.namsx = namsx;
        this.hinh = hinh;
    }
    public String getTenxe() {
        return tenxe;
    }
    public void setTenxe(String tenxe) {
        this.tenxe = tenxe;
    }
    public String getHangsx() {
        return hangsx;
    }
    public void setHangsx(String hangsx) {
        this.hangsx = hangsx;
    }
    public int getNamsx() {
        return namsx;
    }
    public void setNamsx(int namsx) {
        this.namsx = namsx;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

}
