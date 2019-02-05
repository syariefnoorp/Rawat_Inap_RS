package com.example.syari.sqliteapp.Check_In;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.syari.sqliteapp.R;
import com.example.syari.sqliteapp.db_helper.MasukHelper;

import java.util.ArrayList;

public class MasukFragment extends Fragment {
    private static final String TAG = "TabMasuk";
    private MasukHelper masukHelper;
    private MasukAdapter masukAdapter;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private Button tambah;
    private Button cari;
    private Button reset;
    private EditText inputCari;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkin_fragment,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listMasuk);
        masukHelper = new MasukHelper(view.getContext());
        masukAdapter = new MasukAdapter(view.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        tambah = (Button)view.findViewById(R.id.btnTambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),TambahMasukActivity.class);
                startActivity(intent);
            }
        });
        inputCari = (EditText)view.findViewById(R.id.inputSearch);
        cari = (Button)view.findViewById(R.id.btnCari);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSearchData(inputCari.getText().toString());
            }
        });
        reset = (Button)view.findViewById(R.id.btnReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllData();
                inputCari.setText("");
            }
        });

        linearLayout = (LinearLayout)view.findViewById(R.id.search);
        inputCari.clearFocus();
        linearLayout.requestFocus();

        getAllData();

        return view;
    }

    private void getAllData(){
        masukHelper.open();
        // Ambil semua data mahasiswa di database
        ArrayList<MasukModel> masukModels = masukHelper.getAllData();
        masukHelper.close();
        masukAdapter.addItem(masukModels);
        recyclerView.setAdapter(masukAdapter);
    }

    private void getSearchData(String nama){
        masukHelper.open();
        // Ambil semua data mahasiswa di database
        ArrayList<MasukModel> masukModels = masukHelper.getSearch(nama);
        masukHelper.close();
        masukAdapter.addItem(masukModels);
        recyclerView.setAdapter(masukAdapter);
    }
}
