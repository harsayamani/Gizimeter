package com.example.harsayamani.gizimeter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gizimeter_detail_monitor_gizi extends AppCompatActivity {

    TextView tv_nama, tv_jenis_kelamin, tv_tanggal_cek, tv_umur_bulan, tv_umur_tahun, tv_berat_badan, tv_tinggi_badan, tv_imt, tv_status_gizi, tv_saran_gizi;
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_monitor_gizi);
        tv_saran_gizi= findViewById(R.id.saranGizi);
        tv_nama = findViewById(R.id.tvNamaLengkap);
        tv_jenis_kelamin = findViewById(R.id.tvJenisKelamin);
        tv_tanggal_cek = findViewById(R.id.tvTanggalCek);
        tv_umur_bulan = findViewById(R.id.tvUmurBulan);
        tv_umur_tahun = findViewById(R.id.tvUmurTahun);
        tv_berat_badan = findViewById(R.id.tvBeratBadan);
        tv_tinggi_badan = findViewById(R.id.tvTinggiBadan);
        tv_imt = findViewById(R.id.tvIMT);
        tv_status_gizi = findViewById(R.id.tvStatusGizi);
        btn_update = findViewById(R.id.btn_update);

        try{
            detailMonitorGizi();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void detailMonitorGizi() {
        final Intent identifikasi = getIntent();

        final String msgId = identifikasi.getStringExtra("dataID");

        final String msgNama = identifikasi.getStringExtra("dataNama");
        String msgJenisKelamin = identifikasi.getStringExtra("dataJenisKelamin");
        String msgTanggalCek = identifikasi.getStringExtra("dataTanggal");
        String msgUmurTahun = identifikasi.getStringExtra("dataUmurTahun");
        String msgUmurBulan = identifikasi.getStringExtra("dataUmurBulan");
        String msgBeratBadan = identifikasi.getStringExtra("dataBeratBadan");
        String msgTinggiBadan = identifikasi.getStringExtra("dataTinggiBadan");
        Double msgIMT = identifikasi.getDoubleExtra("dataIMT",0);
        String msgStatusGizi = identifikasi.getStringExtra("dataStatusGizi");
        String msgSaranGizi = identifikasi.getStringExtra("dataSaranGizi");

        tv_nama.setText(msgNama);
        tv_jenis_kelamin.setText(msgJenisKelamin);
        tv_tanggal_cek.setText(msgTanggalCek);
        tv_umur_tahun.setText(msgUmurTahun);
        tv_umur_bulan.setText(msgUmurBulan);
        tv_berat_badan.setText(msgBeratBadan + " kg");
        tv_tinggi_badan.setText(msgTinggiBadan + " cm");
        tv_imt.setText("IMT = "+msgIMT.toString());
        tv_status_gizi.setText(msgStatusGizi);
        tv_saran_gizi.setText(msgSaranGizi);

        updateMonitorGizi(msgId, msgNama);
    }

    private void updateMonitorGizi(final String msgId, final String msgNama) {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gizimeter_detail_monitor_gizi.this, gizimeter_update_monitor_gizi.class);
                intent.putExtra("id", msgId);
                intent.putExtra("nama", msgNama);
                startActivity(intent);
            }
        });
    }
}
