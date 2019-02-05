package com.example.syari.sqliteapp.Check_Out;

public class KeluarModel {
    private int id;
    private int nik_pasien;
    private String nama;
    private int umur;
    private String penyakit;
    private int hari;

    public KeluarModel() {
    }

    public KeluarModel(int id, int nik_pasien, int hari) {
        this.id = id;
        this.nik_pasien = nik_pasien;
        this.hari = hari;
    }

    public KeluarModel(int id, int nik_pasien, String nama, int umur, String penyakit, int hari) {
        this.id = id;
        this.nik_pasien = nik_pasien;
        this.nama = nama;
        this.umur = umur;
        this.penyakit = penyakit;
        this.hari = hari;
    }

    public KeluarModel(int nik_pasien, int hari) {
        this.nik_pasien = nik_pasien;
        this.hari = hari;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNik_pasien() {
        return nik_pasien;
    }

    public void setNik_pasien(int nik_pasien) {
        this.nik_pasien = nik_pasien;
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

    public int getHari() {
        return hari;
    }

    public void setHari(int hari) {
        this.hari = hari;
    }
}
