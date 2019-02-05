package com.example.syari.sqliteapp.Check_In;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syari.sqliteapp.R;
import com.example.syari.sqliteapp.db_helper.MasukHelper;

import java.util.ArrayList;

public class MasukAdapter extends RecyclerView.Adapter<MasukAdapter.CustomViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<MasukModel> masuk;
    private Context context;
    private MasukHelper masukHelper;

    public MasukAdapter (Context context){
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        masukHelper = new MasukHelper(context);

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.list_masuk, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder,final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String nama = masuk.get(position).getNama();
        final String penyakit = masuk.get(position).getPenyakit();
        final String umur = String.valueOf(masuk.get(position).getUmur());
        final String nik = String.valueOf(masuk.get(position).getNik());
        holder.viewNama.setText(nama);
        holder.viewPenyakit.setText(penyakit);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MasukActivity.class);
                intent.putExtra("detail_nik", nik);
                intent.putExtra("detail_nama", nama);
                intent.putExtra("detail_umur", umur);
                intent.putExtra("detail_penyakit", penyakit);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return masuk.size();
    }

    public void addItem(ArrayList<MasukModel> mData) {
        this.masuk = mData;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView viewNama, viewPenyakit;
        private LinearLayout parentLayout;

        public CustomViewHolder(View itemView) {
            super(itemView);

            viewNama = (TextView) itemView.findViewById(R.id.viewNama);
            viewPenyakit = (TextView) itemView.findViewById(R.id.viewPenyakit);
            parentLayout = (LinearLayout) itemView.findViewById(R.id.parent_layout);
        }

    }
}
