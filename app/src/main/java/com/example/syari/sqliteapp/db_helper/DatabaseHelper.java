package com.example.syari.sqliteapp.db_helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import static android.provider.BaseColumns._ID;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.KeluarColumns.HARI;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.KeluarColumns.ID;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.KeluarColumns.NIK_PASIEN;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.MasukColumns.NAMA;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.MasukColumns.NIK;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.MasukColumns.PENYAKIT;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.MasukColumns.UMUR;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.TABLE_KELUAR;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.TABLE_MASUK;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static String CREATE_TABLE_MASUK = "create table " + TABLE_MASUK +
            " (" + NIK + " integer primary key, " +
            NAMA + " text not null, " +
            UMUR + " integer not null, " +
            PENYAKIT + " text not null);";
    public static String CREATE_TABLE_KELUAR = "create table " + TABLE_KELUAR +
            " (" + ID + " integer primary key autoincrement, " +
            NIK_PASIEN + " integer not null, " +
            HARI + " integer not null, " +
            " FOREIGN KEY ("+NIK_PASIEN+") REFERENCES "+TABLE_MASUK+"("+NIK+"));";
    private static String DATABASE_NAME = "db_masuk";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MASUK);
        db.execSQL(CREATE_TABLE_KELUAR);
    }

    //untuk migrasi database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MASUK);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_KELUAR);
        onCreate(db);
    }
}
