package com.example.harsayamani.gizimeter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseMonitorHelper extends SQLiteOpenHelper {
    DatabaseMonitorHelper(Context context) {
        super(context, "MonitorGizi", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTabel = "create table grafik (xValues integer primary key autoincrement, imt double ,tanggal_cek text not null, status_gizi text, saran_gizi text)";
        db.execSQL(createTabel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS grafik");
        onCreate(db);
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("select * from grafik order by xValues desc ", null);
    }

    public boolean insert_data(String tanggal_cek, double imt, String status_gizi, String saran_gizi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imt", imt);
        values.put("tanggal_cek", tanggal_cek);
        values.put("status_gizi", status_gizi);
        values.put("saran_gizi", saran_gizi);

        db.insert("grafik",null , values);
        return true;
    }

    void delete_data(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from grafik");
        db.close();
    }
}
