package com.example.syari.sqliteapp.Check_Out;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syari.sqliteapp.Check_In.MasukActivity;
import com.example.syari.sqliteapp.Check_In.MasukAdapter;
import com.example.syari.sqliteapp.Check_In.MasukModel;
import com.example.syari.sqliteapp.R;
import com.example.syari.sqliteapp.db_helper.KeluarHelper;
import com.example.syari.sqliteapp.db_helper.MasukHelper;

import java.util.ArrayList;
import java.util.List;

public class KeluarAdapter extends RecyclerView.Adapter<KeluarAdapter.CustomViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<KeluarModel> keluar;
    private Context context;
    private KeluarHelper keluarHelper;

    public KeluarAdapter (Context context){
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        keluarHelper = new KeluarHelper(context);

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.list_keluar, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final String id = String.valueOf(keluar.get(position).getId());
        final String nik_pasien = String.valueOf(keluar.get(position).getNik_pasien());
        final String nama = keluar.get(position).getNama();
        final String penyakit = keluar.get(position).getPenyakit();
        final String umur = String.valueOf(keluar.get(position).getUmur());
        final String hari = String.valueOf(keluar.get(position).getHari());

        holder.viewNama.setText(nama);
        holder.viewPenyakit.setText(penyakit);
        holder.viewHari.setText(hari);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KeluarActivity.class);
                intent.putExtra("detail_id", id);
                intent.putExtra("detail_nik", nik_pasien);
                intent.putExtra("detail_nama", nama);
                intent.putExtra("detail_umur", umur);
                intent.putExtra("detail_penyakit", penyakit);
                intent.putExtra("detail_hari", hari);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return keluar.size();
    }

    public void addItem(ArrayList<KeluarModel> mData) {
        this.keluar = mData;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView viewNama, viewPenyakit, viewHari;
        private LinearLayout parentLayout;

        public CustomViewHolder(View itemView) {
            super(itemView);

            viewNama = (TextView) itemView.findViewById(R.id.viewNama);
            viewPenyakit = (TextView) itemView.findViewById(R.id.viewPenyakit);
            viewHari = (TextView)itemView.findViewById(R.id.viewHari);
            parentLayout = (LinearLayout) itemView.findViewById(R.id.parent_layout);
        }

    }
}
