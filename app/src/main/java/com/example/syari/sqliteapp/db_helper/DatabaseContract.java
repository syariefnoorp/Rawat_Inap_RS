package com.example.syari.sqliteapp.db_helper;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_MASUK = "table_masuk";
    static String TABLE_KELUAR = "table_keluar";

    static final class MasukColumns implements BaseColumns {

        // Check-in Model nama
        static String NAMA = "nama";
        // Check-in Model nik
        static String NIK = "nik";
        // Check-in Model umur
        static String UMUR = "umur";
        // Check-in Model penyakit
        static String PENYAKIT = "penyakit";
    }

    static final class KeluarColumns implements BaseColumns {

        static String ID = "id";
        static String NIK_PASIEN = "nik_pasien";
        static String NAMA = "nama";
        static String UMUR = "umur";
        static String PENYAKIT = "penyakit";
        static String HARI = "hari";

    }
}
