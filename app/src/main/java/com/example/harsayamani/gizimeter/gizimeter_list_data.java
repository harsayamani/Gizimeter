package com.example.harsayamani.gizimeter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;


public class gizimeter_list_data extends AppCompatActivity {

    private ListView listView;
    private DatabasePenggunaHelper mydb;
    private ArrayList<String> ListData;
    String [] items ={"Hapus Data", "Edit Data"};
    LinearLayout llKosong;
    ImageView gambarGIF;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        getSupportActionBar().setTitle("Daftar Pengguna");
        listView = findViewById(R.id.list);
        llKosong = findViewById(R.id.llkosong);
        gambarGIF = findViewById(R.id.gambarGIF);
        add = findViewById(R.id.btn_add);
        ListData = new ArrayList<>();
        mydb = new DatabasePenggunaHelper(getBaseContext());

        try{
            dataEmpty();
            getData();
            listData();
            addData();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void dataEmpty() {
        Glide.with(gizimeter_list_data.this)
                .load(R.drawable.scroll_down_1)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(gambarGIF);
    }

    private void listData() {
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.design_isi_daftar_pengguna, ListData));

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(gizimeter_list_data.this);
                builder.setTitle("Opsi");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(items[which].equals("Hapus Data")){
                            SQLiteDatabase ReadData = mydb.getReadableDatabase();
                            @SuppressLint("Recycle") Cursor cursor = ReadData.rawQuery("select * from hasil_monitor", null);
                            String id = " ";
                            if(cursor.moveToFirst()){
                                cursor.moveToPosition(position);
                                id = String.valueOf(cursor.getInt(0));
                            }
                            mydb.deleteData(id);
                            Intent intent = new Intent(gizimeter_list_data.this, gizimeter_list_data.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                        }else if(items[which].equals("Edit Data")){
                            detail(position);
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                detail(position);
            }
        });
    }

    private void addData() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gizimeter_list_data.this, gizimeter_monitor_gizi.class));
            }
        });
    }

    private void getData() {
        SQLiteDatabase ReadData = mydb.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = ReadData.rawQuery("select * from hasil_monitor", null);
        cursor.moveToFirst();

        for(int count=0; count<cursor.getCount(); count++){
            cursor.moveToPosition(count);
            ListData.add(cursor.getString(1));
            llKosong.setVisibility(View.INVISIBLE);
        }
    }

    public void detail(int position) {
        SQLiteDatabase ReadData = mydb.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = ReadData.rawQuery("select * from hasil_monitor", null);

        String nama = "";
        String jenisKelamin = "";
        String tanggalCek = "";
        String umurTahun = "";
        String umurBulan = "";
        String beratBadan = "";
        String tinggiBadan = "";
        String statusGizi = "";
        String saranGizi = "";
        String id = " ";
        double imt = 0;

        if (cursor.moveToFirst()) {
            cursor.moveToPosition(position);
            id = cursor.getString(0);
            nama = cursor.getString(1);
            tanggalCek = cursor.getString(2);
            jenisKelamin = cursor.getString(3);
            umurTahun = cursor.getString(4);
            umurBulan = cursor.getString(5);
            beratBadan = cursor.getString(6);
            tinggiBadan = cursor.getString(7);
            imt = cursor.getDouble(8);
            statusGizi = cursor.getString(9);
            saranGizi = cursor.getString(10);
        }

        Intent iIntent = new Intent(gizimeter_list_data.this, gizimeter_detail_monitor_gizi.class);
        iIntent.putExtra("dataID", id);
        iIntent.putExtra("dataNama", nama);
        iIntent.putExtra("dataTanggal", tanggalCek);
        iIntent.putExtra("dataJenisKelamin", jenisKelamin);
        iIntent.putExtra("dataUmurTahun", umurTahun);
        iIntent.putExtra("dataUmurBulan", umurBulan);
        iIntent.putExtra("dataBeratBadan", beratBadan);
        iIntent.putExtra("dataTinggiBadan", tinggiBadan);
        iIntent.putExtra("dataIMT", imt);
        iIntent.putExtra("dataStatusGizi", statusGizi);
        iIntent.putExtra("dataSaranGizi", saranGizi);
        setResult(RESULT_OK, iIntent);
        startActivityForResult(iIntent,99);
    }

}