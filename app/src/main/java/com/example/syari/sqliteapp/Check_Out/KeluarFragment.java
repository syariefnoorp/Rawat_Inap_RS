package com.example.syari.sqliteapp.Check_Out;

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

import com.example.syari.sqliteapp.R;
import com.example.syari.sqliteapp.db_helper.KeluarHelper;

import java.util.ArrayList;

public class KeluarFragment extends Fragment {
    private static final String TAG = "TabKeluar";
    private KeluarHelper keluarHelper;
    private KeluarAdapter keluarAdapter;
    private RecyclerView recyclerView;
    private Button tambah;
    private Button cari;
    private Button reset;
    private EditText inputCari;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkout_fragment,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listKeluar);
        keluarHelper = new KeluarHelper(view.getContext());
        keluarAdapter = new KeluarAdapter(view.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        tambah = (Button)view.findViewById(R.id.btnTambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),TambahKeluarActivity.class);
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

        getAllData();

        return view;
    }
    private void getAllData(){
        keluarHelper.open();
        // Ambil semua data mahasiswa di database
        ArrayList<KeluarModel> keluarModels = keluarHelper.getAllData();
        keluarHelper.close();
        keluarAdapter.addItem(keluarModels);
        recyclerView.setAdapter(keluarAdapter);
    }

    private void getSearchData(String nama){
        keluarHelper.open();
        // Ambil semua data mahasiswa di database
        ArrayList<KeluarModel> keluarModels = keluarHelper.getSearch(nama);
        keluarHelper.close();
        keluarAdapter.addItem(keluarModels);
        recyclerView.setAdapter(keluarAdapter);
    }
}
