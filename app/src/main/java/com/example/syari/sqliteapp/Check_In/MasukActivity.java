package com.example.syari.sqliteapp.Check_In;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.syari.sqliteapp.R;
import com.example.syari.sqliteapp.db_helper.MasukHelper;

import java.util.ArrayList;

public class MasukActivity extends AppCompatActivity {

    private EditText nik;
    private EditText nama;
    private EditText umur;
    private EditText penyakit;
    private Button update;
    private Button delete;
    private ArrayList<MasukModel> masuk;
    private MasukHelper masukHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);


        update = (Button) findViewById(R.id.btnUpdate);
        delete = (Button)findViewById(R.id.btnDelete);
        masukHelper = new MasukHelper(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();

                Intent intent = new Intent(MasukActivity.this,MasukFragment.class);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();

                Intent intent = new Intent(MasukActivity.this,MasukFragment.class);
                startActivity(intent);
            }
        });

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("detail_nik")&&getIntent().hasExtra("detail_nama")&&
                getIntent().hasExtra("detail_umur")&&getIntent().hasExtra("detail_penyakit")){
            //System.out.println("nik error : "+getIntent().getStringExtra("detail_nik"));
            String incomingNik = getIntent().getStringExtra("detail_nik");
            String incomingNama = getIntent().getStringExtra("detail_nama");
            String incomingUmur = getIntent().getStringExtra("detail_umur");
            String incomingPenyakit = getIntent().getStringExtra("detail_penyakit");

            setDetail(incomingNik,incomingNama,incomingUmur,incomingPenyakit);
        }
    }

    private void setDetail(String incomingNik,String incomingNama,String incomingUmur,String incomingPenyakit){
        nik = (EditText)findViewById(R.id.detailNik);
        nama = (EditText)findViewById(R.id.detailNama);
        umur = (EditText)findViewById(R.id.detailUmur);
        penyakit = (EditText)findViewById(R.id.detailPenyakit);

        nik.setText(incomingNik);
        nama.setText(incomingNama);
        umur.setText(incomingUmur);
        penyakit.setText(incomingPenyakit);
    }

    private void updateData(){
        int updateNik = Integer.parseInt(nik.getText().toString());
        int updateUmur = Integer.parseInt(umur.getText().toString());

        masukHelper.open();
        MasukModel masukObj = new MasukModel(updateNik,nama.getText().toString(),updateUmur,penyakit.getText().toString());
        masukHelper.update(masukObj);
        masukHelper.close();
    }

    private void deleteData(){
        int deleteNik = Integer.parseInt(nik.getText().toString());

        masukHelper.open();
        masukHelper.delete(deleteNik);
        masukHelper.close();
    }
}
