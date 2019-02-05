package com.example.syari.sqliteapp.Check_In;

public class MasukModel {
    private int nik;
    private String nama;
    private int umur;
    private String penyakit;

    public MasukModel(){

    }

    public MasukModel(int nik, String nama, int umur, String penyakit) {
        this.nik = nik;
        this.nama = nama;
        this.umur = umur;
        this.penyakit = penyakit;
    }

    public int getNik() {
        return nik;
    }

    public void setNik(int nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getPenyakit() {
        return penyakit;
    }

    public void setPenyakit(String penyakit) {
        this.penyakit = penyakit;
    }
}
