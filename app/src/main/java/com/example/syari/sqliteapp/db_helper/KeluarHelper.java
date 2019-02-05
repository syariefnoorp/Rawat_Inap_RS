package com.example.syari.sqliteapp.db_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.syari.sqliteapp.Check_Out.KeluarModel;

import java.util.ArrayList;

import static com.example.syari.sqliteapp.db_helper.DatabaseContract.KeluarColumns.HARI;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.KeluarColumns.ID;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.KeluarColumns.NAMA;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.KeluarColumns.NIK_PASIEN;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.KeluarColumns.PENYAKIT;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.KeluarColumns.UMUR;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.MasukColumns.NIK;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.TABLE_KELUAR;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.TABLE_MASUK;

public class KeluarHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public KeluarHelper(Context context) {
        this.context = context;
    }

    public KeluarHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<KeluarModel> getSearch(String cariNama){

        String sql = "SELECT "+ID+","+NIK_PASIEN+","+NAMA+","+UMUR+","+PENYAKIT+","+HARI+" FROM "
                +TABLE_KELUAR+" JOIN "+TABLE_MASUK+" ON "+TABLE_KELUAR+"."+NIK_PASIEN+" = "+TABLE_MASUK+"."+NIK;
        Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        ArrayList<KeluarModel> arrayList = new ArrayList<>();
        KeluarModel keluarModel;
        if (cursor.getCount() > 0) {
            do {
                keluarModel = new KeluarModel();
                if (cariNama.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)))){
                    keluarModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                    keluarModel.setNik_pasien(cursor.getInt(cursor.getColumnIndexOrThrow(NIK_PASIEN)));
                    keluarModel.setNama(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                    keluarModel.setUmur(cursor.getInt(cursor.getColumnIndexOrThrow(UMUR)));
                    keluarModel.setPenyakit(cursor.getString(cursor.getColumnIndexOrThrow(PENYAKIT)));
                    keluarModel.setHari(cursor.getInt(cursor.getColumnIndexOrThrow(HARI)));

                    arrayList.add(keluarModel);
                }
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KeluarModel> getAllData() {
//        String sql = "SELECT id,nik_pasien,nama,umur,penyakit,hari FROM table_keluar JOIN table_masuk ON table_keluar.nik_pasisen = table_masuk.nik";
//        Cursor cursor = database.rawQuery(sql,null);
        //String sql = "SELECT * FROM "+TABLE_MASUK;
        String sql = "SELECT "+ID+","+NIK_PASIEN+","+NAMA+","+UMUR+","+PENYAKIT+","+HARI+" FROM "
                +TABLE_KELUAR+" JOIN "+TABLE_MASUK+" ON "+TABLE_KELUAR+"."+NIK_PASIEN+" = "+TABLE_MASUK+"."+NIK;
        Cursor cursor = database.rawQuery(sql,null);
        //Cursor cursor = database.query(TABLE_KELUAR, null, null, null, null, null, null, null);
        cursor.moveToFirst();
        ArrayList<KeluarModel> arrayList = new ArrayList<>();
        KeluarModel keluarModel;
        if (cursor.getCount() > 0) {
            do {
                keluarModel = new KeluarModel();
                keluarModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                keluarModel.setNik_pasien(cursor.getInt(cursor.getColumnIndexOrThrow(NIK_PASIEN)));
                keluarModel.setNama(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                keluarModel.setUmur(cursor.getInt(cursor.getColumnIndexOrThrow(UMUR)));
                keluarModel.setPenyakit(cursor.getString(cursor.getColumnIndexOrThrow(PENYAKIT)));
                keluarModel.setHari(cursor.getInt(cursor.getColumnIndexOrThrow(HARI)));
                arrayList.add(keluarModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(KeluarModel keluarModel) {
        ContentValues initialValues = new ContentValues();
        //initialValues.put(ID, keluarModel.getId());
        initialValues.put(NIK_PASIEN, keluarModel.getNik_pasien());
        initialValues.put(HARI, keluarModel.getHari());
        return database.insert(TABLE_KELUAR, null, initialValues);
    }


    public int update(KeluarModel keluarModel) {
        ContentValues args = new ContentValues();
        args.put(ID, keluarModel.getId());
        args.put(NIK_PASIEN, keluarModel.getNik_pasien());
//        args.put(NAMA, keluarModel.getNama());
//        args.put(UMUR, keluarModel.getUmur());
//        args.put(PENYAKIT, keluarModel.getPenyakit());
        args.put(HARI, keluarModel.getHari());
        return database.update(TABLE_KELUAR, args, ID + "= '" + keluarModel.getId() + "'", null);
    }


    public int delete(int id) {
        return database.delete(TABLE_KELUAR, ID + " = '" + id + "'", null);
    }
}
