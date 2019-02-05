package com.example.syari.sqliteapp.db_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.syari.sqliteapp.Check_In.MasukModel;

import java.util.ArrayList;

import static com.example.syari.sqliteapp.db_helper.DatabaseContract.MasukColumns.NAMA;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.MasukColumns.NIK;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.MasukColumns.PENYAKIT;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.MasukColumns.UMUR;
import static com.example.syari.sqliteapp.db_helper.DatabaseContract.TABLE_MASUK;

public class MasukHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public MasukHelper(Context context) {
        this.context = context;
    }

    public MasukHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<MasukModel> getSearch(String cariNama){
        Cursor cursor = database.query(TABLE_MASUK, null, null, null, null, null, null, null);
        cursor.moveToFirst();
        ArrayList<MasukModel> arrayList = new ArrayList<>();
        MasukModel masukModel;
        if (cursor.getCount() > 0) {
            do {
                masukModel = new MasukModel();
                if (cariNama.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)))){
                    masukModel.setNik(cursor.getInt(cursor.getColumnIndexOrThrow(NIK)));
                    masukModel.setNama(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                    masukModel.setUmur(cursor.getInt(cursor.getColumnIndexOrThrow(UMUR)));
                    masukModel.setPenyakit(cursor.getString(cursor.getColumnIndexOrThrow(PENYAKIT)));
                    arrayList.add(masukModel);

                }
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<MasukModel> getAllData() {
        Cursor cursor = database.query(TABLE_MASUK, null, null, null, null, null, null, null);
        cursor.moveToFirst();
        ArrayList<MasukModel> arrayList = new ArrayList<>();
        MasukModel masukModel;
        if (cursor.getCount() > 0) {
            do {
                masukModel = new MasukModel();
                masukModel.setNik(cursor.getInt(cursor.getColumnIndexOrThrow(NIK)));
                masukModel.setNama(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                masukModel.setUmur(cursor.getInt(cursor.getColumnIndexOrThrow(UMUR)));
                masukModel.setPenyakit(cursor.getString(cursor.getColumnIndexOrThrow(PENYAKIT)));
                arrayList.add(masukModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(MasukModel masukModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NIK, masukModel.getNik());
        initialValues.put(NAMA, masukModel.getNama());
        initialValues.put(UMUR, masukModel.getUmur());
        initialValues.put(PENYAKIT, masukModel.getPenyakit());
        return database.insert(TABLE_MASUK, null, initialValues);
    }


    public int update(MasukModel masukModel) {
        ContentValues args = new ContentValues();
        args.put(NIK, masukModel.getNik());
        args.put(NAMA, masukModel.getNama());
        args.put(UMUR, masukModel.getUmur());
        args.put(PENYAKIT, masukModel.getPenyakit());
        return database.update(TABLE_MASUK, args, NIK + "= '" + masukModel.getNik() + "'", null);
    }


    public int delete(int nik) {
        return database.delete(TABLE_MASUK, NIK + " = '" + nik + "'", null);
    }
}
