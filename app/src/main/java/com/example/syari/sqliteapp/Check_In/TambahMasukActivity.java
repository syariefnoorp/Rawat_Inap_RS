package com.example.syari.sqliteapp.Check_In;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.syari.sqliteapp.Check_Out.TambahKeluarActivity;
import com.example.syari.sqliteapp.R;
import com.example.syari.sqliteapp.db_helper.MasukHelper;

import java.util.ArrayList;

public class TambahMasukActivity extends AppCompatActivity {

    private ArrayList<MasukModel> masuk;
    private MasukHelper masukHelper;
    private EditText nama;
    private EditText nik;
    private EditText umur;
    private EditText penyakit;
    private Button back;
    private Button submit;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_masuk);

        nama = (EditText)findViewById(R.id.inputNama);
        nik = (EditText)findViewById(R.id.inputNik);
        umur = (EditText)findViewById(R.id.inputUmur);
        penyakit = (EditText)findViewById(R.id.inputPenyakit);
        back = (Button)findViewById(R.id.btnBack);
        submit = (Button)findViewById(R.id.btnSubmit);
        masukHelper = new MasukHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();

                Intent intent = new Intent(TambahMasukActivity.this,MasukFragment.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertData() {
        int insertNik = Integer.parseInt(nik.getText().toString());
        int insertUmur = Integer.parseInt(umur.getText().toString());

        masukHelper.open();
        MasukModel masukObj = new MasukModel(insertNik,nama.getText().toString(),insertUmur,penyakit.getText().toString());
        masukHelper.insert(masukObj);
        masukHelper.close();
    }
}
