package com.example.harsayamani.gizimeter;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class gizimeter_detail_monitor_gizi2 extends AppCompatActivity {

    TextView tvRiwayatMonitor, tvSaranGizi, tvstatusGizi, tvImt;
    GraphView graphView;
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[0]);
    DatabaseMonitorHelper mvHelper;
    Button resetData, tambahData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_monitor_gizi2);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Hasil Monitor Gizi");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            tvSaranGizi = findViewById(R.id.tv_saran_gizi);
            tvRiwayatMonitor = findViewById(R.id.et_riwayat_monitor);
            graphView = findViewById(R.id.detail_grafik);
            mvHelper = new DatabaseMonitorHelper(this);
            tvstatusGizi = findViewById(R.id.tv_status_gizi);
            tvImt = findViewById(R.id.tv_imt);
            resetData = findViewById(R.id.btn_reset_data);
            tambahData = findViewById(R.id.btn_tambah_data);

         try{
            grafikMonitorGizi();
            detailMonitorGizi2();
            buttonAction();
        }catch(Exception ex){
            startActivity(new Intent(gizimeter_detail_monitor_gizi2.this, gizimeter_monitor_gizi2.class));
            Toast.makeText(getApplicationContext(), "Data Belum Ada", Toast.LENGTH_LONG).show();
        }
    }

    private void grafikMonitorGizi() {
        series.resetData(getDataPoint());
        graphView.addSeries(series);
    }

    private void detailMonitorGizi2() {
        tvstatusGizi.setText(statusGizi());
        tvImt.setText("IMT = "+hasilIMT());
        tvRiwayatMonitor.setText(riwayatMonitor());
        tvSaranGizi.setText(saranGizi());
    }

    private void buttonAction() {
        reset_data();
        tambah_data();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_share_hasil_monitoring_gizi, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share_monitor:
                LayoutInflater layoutInflater = getLayoutInflater();
                @SuppressLint("InflateParams") final View formsView = layoutInflater.inflate(R.layout.design_nama_lengkap, null, false);
                final EditText nama_lengkap = formsView.findViewById(R.id.share_nama);

                AlertDialog.Builder dialog = new AlertDialog.Builder(gizimeter_detail_monitor_gizi2.this);
                dialog.setView(formsView);
                dialog.setTitle("Bagikan Sebagai..");
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msgNama = nama_lengkap.getText().toString();

                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Hasil Monitor Gizi Aplikasi Gizimeter");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "Pengguna : "+msgNama+"\n\n"
                                +"Indeks Masa Tubuh Terakhir\b : "+hasilIMT()+"\n\n"
                                +"Status Gizi Terakhir\b : "+statusGizi()+"\n\n"
                                +"Saran Gizi\b : \n\n"+saranGizi()+"\n\n"
                                +"Riwayat Monitor Gizi\b : \n\n"+riwayatMonitor());
                        startActivity(Intent.createChooser(shareIntent, "Bagikan dengan"));
                    }
                });
                dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String saranGizi() {
        SQLiteDatabase read_data = mvHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = read_data.rawQuery("select * from grafik", null);
        cursor.moveToLast();
        return cursor.getString(4);
    }

    private void tambah_data() {
        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gizimeter_detail_monitor_gizi2.this, gizimeter_monitor_gizi2.class));
            }
        });
    }

    private void reset_data() {
        resetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gizimeter_detail_monitor_gizi2.this, gizimeter_monitor_gizi2.class));
                Toast.makeText(getApplicationContext(), "Data Sudah Direset", Toast.LENGTH_LONG).show();
                mvHelper.delete_data();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private String hasilIMT() {
        SQLiteDatabase read_data = mvHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = read_data.rawQuery("select * from grafik", null);
        cursor.moveToLast();
        return cursor.getString(1);
    }

    private String statusGizi() {
        SQLiteDatabase read_data = mvHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = read_data.rawQuery("select * from grafik", null);
        cursor.moveToLast();
        return cursor.getString(3);
    }

    private DataPoint[] getDataPoint() {
        SQLiteDatabase readData = mvHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = readData.rawQuery("select*from grafik", null);
        DataPoint[] dp = new DataPoint[cursor.getCount()];

        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            dp[i] = new DataPoint(cursor.getInt(0), cursor.getDouble(1));
        }
        return dp;
    }

    private StringBuffer riwayatMonitor() {

        Cursor res = mvHelper.getAllData();

        if (res.getCount() == 0) {
            gizimeter_main_activity ma = new gizimeter_main_activity();
            ma.showMessage("Error", "Nothing Found");
        }

        StringBuffer buffer = new StringBuffer();

            while (res.moveToNext()) {
                buffer.append("Tanggal Cek: ").append(res.getString(2)).append("\n");
                buffer.append("Indeks Masa Tubuh (IMT) : ").append(res.getString(1)).append("\n");
                buffer.append("Status Gizi : ").append(res.getString(3)).append("\n\n");
            }
            return buffer;
    }
}

