package com.example.harsayamani.gizimeter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabasePenggunaHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "monitorgizi";

    DatabasePenggunaHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table hasil_monitor(id integer primary key autoincrement, nama_lengkap text , " +
                "tanggal_cek text," +
                "jenis_kelamin text ," +
                "umur_tahun text ," +
                "umur_bulan text ," +
                "berat_badan text ," +
                "tinggi_badan text ," +
                "imt Double ," +
                "status_gizi text," +
                "saran_kesehatan text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS hasil_monitor" );
        onCreate(db);
    }

    public boolean insertData(String nama_lengkap, String tanggal_cek, String jenis_kelamin, String umur_tahun, String umur_bulan, String berat_badan, String tinggi_badan, double imt, String status_gizi, String saran_kesehatan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nama_lengkap", nama_lengkap);
        contentValues.put("jenis_kelamin", jenis_kelamin);
        contentValues.put("tanggal_cek", tanggal_cek);
        contentValues.put("umur_tahun",umur_tahun);
        contentValues.put("umur_bulan", umur_bulan);
        contentValues.put("berat_badan", berat_badan);
        contentValues.put("tinggi_badan", tinggi_badan);
        contentValues.put("imt", imt);
        contentValues.put("status_gizi", status_gizi);
        contentValues.put("saran_kesehatan", saran_kesehatan);

        db.insert("hasil_monitor", "id", contentValues);

        return true;
    }

    public boolean updateData( String id, String nama_lengkap, String tanggal_cek, String jenis_kelamin, String umur_tahun, String umur_bulan, String berat_badan, String tinggi_badan, double imt, String status_gizi, String saran_kesehatan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("nama_lengkap", nama_lengkap);
        contentValues.put("jenis_kelamin", jenis_kelamin);
        contentValues.put("tanggal_cek", tanggal_cek);
        contentValues.put("umur_tahun",umur_tahun);
        contentValues.put("umur_bulan", umur_bulan);
        contentValues.put("berat_badan", berat_badan);
        contentValues.put("tinggi_badan", tinggi_badan);
        contentValues.put("imt", imt);
        contentValues.put("status_gizi", status_gizi);
        contentValues.put("saran_kesehatan", saran_kesehatan);

        db.update("hasil_monitor", contentValues, "id = ?", new String[]{id});
        return true;
    }

    public void deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("hasil_monitor", "ID = ?", new String[]{id});
    }



}
