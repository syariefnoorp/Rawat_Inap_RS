package com.example.syari.sqliteapp.Check_Out;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.syari.sqliteapp.Check_In.MasukFragment;
import com.example.syari.sqliteapp.Check_In.MasukModel;
import com.example.syari.sqliteapp.Check_In.TambahMasukActivity;
import com.example.syari.sqliteapp.R;
import com.example.syari.sqliteapp.db_helper.KeluarHelper;
import com.example.syari.sqliteapp.db_helper.MasukHelper;

import java.util.ArrayList;

public class TambahKeluarActivity extends AppCompatActivity {

    private ArrayList<KeluarModel> keluar;
    private KeluarHelper keluarHelper;
    private EditText nik;
    private EditText hari;
    private Button back;
    private Button submit;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_keluar);

        nik = (EditText)findViewById(R.id.inputNik);
        hari = (EditText)findViewById(R.id.inputHari);
        back = (Button)findViewById(R.id.btnBack);
        submit = (Button)findViewById(R.id.btnSubmit);
        keluarHelper = new KeluarHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();

                Intent intent = new Intent(TambahKeluarActivity.this,KeluarFragment.class);
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
        int insertHari = Integer.parseInt(hari.getText().toString());

        keluarHelper.open();
        KeluarModel keluarObj = new KeluarModel(insertNik,insertHari);
        keluarHelper.insert(keluarObj);
        keluarHelper.close();
    }
}
