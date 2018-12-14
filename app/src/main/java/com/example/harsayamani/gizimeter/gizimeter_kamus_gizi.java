package com.example.harsayamani.gizimeter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class gizimeter_kamus_gizi extends Activity {

    protected ListView lv;
    protected ListAdapter adapter;
    SQLiteDatabase db;
    Cursor cursor;
    EditText et_db;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus_gizi);
        db = (new DatabaseKamusGiziHelper(this)).getWritableDatabase();
        lv = findViewById(R.id.lv);
        et_db = findViewById(R.id.et);

        try {
            listData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listData() {
        cursor = db.rawQuery("SELECT * FROM resep ORDER BY nama ASC", null);
        adapter = new SimpleCursorAdapter(this, R.layout.design_isi_listview, cursor,
                new String[] { "nama", "bahan", "img" }, new int[] {
                R.id.tv_nama, R.id.tvBahan, R.id.imV });
        lv.setAdapter(adapter);
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                detail(position);
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void search_db(View v) {
        String edit_db = et_db.getText().toString();
        if (!edit_db.equals("")) {
            try {
                cursor = db.rawQuery("SELECT * FROM resep WHERE nama LIKE ?", new String[] { "%" + edit_db + "%" });
                adapter = new SimpleCursorAdapter(this, R.layout.design_isi_listview, cursor, new String[] { "nama", "bahan", "img" }, new int[] { R.id.tv_nama, R.id.tvBahan, R.id.imV });
                if (adapter.getCount() == 0) {
                    Toast.makeText(this, "Tidak ada " + edit_db + "", Toast.LENGTH_SHORT).show();
                    lv.setAdapter(adapter);
                } else {
                    lv.setAdapter(adapter);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                cursor = db.rawQuery("SELECT * FROM resep ORDER BY nama ASC", null);
                adapter = new SimpleCursorAdapter(this, R.layout.design_isi_listview, cursor, new String[] { "nama", "bahan", "img" }, new int[] { R.id.tv_nama, R.id.tvBahan, R.id.imV });
                lv.setAdapter(adapter);
                lv.setTextFilterEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void detail(int position) {
        int im = 0;
        String nama = "";
        String bahan = "";
        String cara = "";
        if (cursor.moveToFirst()) {
            cursor.moveToPosition(position);
            im = cursor.getInt(cursor.getColumnIndex("img"));
            nama = cursor.getString(cursor.getColumnIndex("nama"));
            bahan = cursor.getString(cursor.getColumnIndex("bahan"));
            cara = cursor.getString(cursor.getColumnIndex("cara"));
        }
        Intent iIntent = new Intent(gizimeter_kamus_gizi.this, gizimeter_detail_bahan.class);
        iIntent.putExtra("dataIM", im);
        iIntent.putExtra("dataNama", nama);
        iIntent.putExtra("dataBahan", bahan);
        iIntent.putExtra("dataCara", cara);
        setResult(RESULT_OK, iIntent);
        startActivityForResult(iIntent,99);
    }
}