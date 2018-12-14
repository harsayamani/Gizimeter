package com.example.harsayamani.gizimeter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

public class gizimeter_detail_bahan extends AppCompatActivity {
    ImageView Im;
    TextView tv_nama, tv_bahan, tv_cara, id;
    Gallery gallery;
    ImageSwitcher imageSwitcher;
    Integer[] imageIDs = new Integer[3];
    int msg_im;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bahan);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Im = findViewById(R.id.iv_detail);
        tv_nama = findViewById(R.id.tvNama);
        tv_bahan =  findViewById(R.id.tvBahan);
        tv_cara =  findViewById(R.id.tvCara);
        collapsingToolbarLayout = findViewById(R.id.collapsing);

        try{
            detailBahan();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void detailBahan() {
        Intent iIdentifikasi = getIntent();
        msg_im = iIdentifikasi.getIntExtra("dataIM", 0);
        String msg_nama = iIdentifikasi.getStringExtra("dataNama");
        String msg_bahan = iIdentifikasi.getStringExtra("dataBahan");
        String msg_cara = iIdentifikasi.getStringExtra("dataCara");

        collapsingToolbarLayout.setTitle(msg_nama);

        Im.setImageResource(msg_im);
        tv_nama.setText(msg_nama);
        tv_bahan.setText(msg_bahan);
        tv_cara.setText(msg_cara);
    }
}
