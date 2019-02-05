package com.example.syari.sqliteapp.Check_Out;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.syari.sqliteapp.Check_In.MasukModel;
import com.example.syari.sqliteapp.R;
import com.example.syari.sqliteapp.db_helper.KeluarHelper;
import com.example.syari.sqliteapp.db_helper.MasukHelper;

import java.util.ArrayList;

public class KeluarActivity extends AppCompatActivity {

    private EditText id;
    private EditText nik;
    private EditText nama;
    private EditText umur;
    private EditText penyakit;
    private EditText hari;
    private Button update;
    private Button delete;
    private ArrayList<KeluarModel> keluar;
    private ArrayList<MasukModel> masuk;
    private KeluarHelper keluarHelper;
    private MasukHelper masukHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keluar);

        update = (Button) findViewById(R.id.btnUpdate);
        delete = (Button)findViewById(R.id.btnDelete);
        keluarHelper = new KeluarHelper(this);
        masukHelper = new MasukHelper(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();

                Intent intent = new Intent(KeluarActivity.this,KeluarFragment.class);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();

                Intent intent = new Intent(KeluarActivity.this,KeluarFragment.class);
                startActivity(intent);
            }
        });

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("detail_nik")&&getIntent().hasExtra("detail_nama")&&
                getIntent().hasExtra("detail_umur")&&getIntent().hasExtra("detail_penyakit")&&
                getIntent().hasExtra("detail_id")&&getIntent().hasExtra("detail_hari")){
            //System.out.println("nik error : "+getIntent().getStringExtra("detail_nik"));
            String incomingId = getIntent().getStringExtra("detail_id");
            String incomingNik = getIntent().getStringExtra("detail_nik");
            String incomingNama = getIntent().getStringExtra("detail_nama");
            String incomingUmur = getIntent().getStringExtra("detail_umur");
            String incomingPenyakit = getIntent().getStringExtra("detail_penyakit");
            String incomingHari = getIntent().getStringExtra("detail_hari");

            setDetail(incomingId,incomingNik,incomingNama,incomingUmur,incomingPenyakit,incomingHari);
        }
    }

    private void setDetail(String incomingId,String incomingNik,String incomingNama,String incomingUmur,
                           String incomingPenyakit,String incomingHari){
        id = (EditText)findViewById(R.id.detailId);
        nik = (EditText)findViewById(R.id.detailNik);
        nama = (EditText)findViewById(R.id.detailNama);
        umur = (EditText)findViewById(R.id.detailUmur);
        penyakit = (EditText)findViewById(R.id.detailPenyakit);
        hari = (EditText)findViewById(R.id.detailHari);

        id.setText(incomingId);
        nik.setText(incomingNik);
        nama.setText(incomingNama);
        umur.setText(incomingUmur);
        penyakit.setText(incomingPenyakit);
        hari.setText(incomingHari);
    }

    private void updateData(){
        int updateId = Integer.parseInt(id.getText().toString());
        int updateNik = Integer.parseInt(nik.getText().toString());
        int updateUmur = Integer.parseInt(umur.getText().toString());
        int updateHari = Integer.parseInt(hari.getText().toString());

        masukHelper.open();

        MasukModel masukObj = new MasukModel(updateNik,nama.getText().toString(),updateUmur,penyakit.getText().toString());
        masukHelper.update(masukObj);
        masukHelper.close();

        keluarHelper.open();
        KeluarModel keluarObj = new KeluarModel(updateId,updateNik,updateHari);
        keluarHelper.update(keluarObj);

        keluarHelper.close();

    }

    private void deleteData(){
        int deleteId = Integer.parseInt(id.getText().toString());

        keluarHelper.open();
        keluarHelper.delete(deleteId);
        keluarHelper.close();
    }
}
