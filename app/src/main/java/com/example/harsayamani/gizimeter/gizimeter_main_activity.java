package com.example.harsayamani.gizimeter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class gizimeter_main_activity extends AppCompatActivity {

    CardView btnHitungGizi;
    CardView btnKamusGizi;
    CardView btnMonitorGizi;
    CardView btnRiwayatHasil;
    LinearLayout linearLayout;
    TextView artikelGizi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        setTitle("Gizimeter");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_round);
        linearLayout = findViewById(R.id.dotsLayout);
        btnMonitorGizi = findViewById(R.id.btn_monitor_gizi);
        artikelGizi = findViewById(R.id.artikel);
        btnRiwayatHasil = findViewById(R.id.btn_riwayat_hasil);
        btnKamusGizi = findViewById(R.id.btn_kamus_gizi);
        btnHitungGizi = findViewById(R.id.btn_hitung_gizi);

        try{
            viewPager();
            cekGizi();
            kamusGizi();
            monitorGizi();
            hasilMonitorGizi();
            artikelGizi();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void artikelGizi() {
        artikelGizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gizimeter_main_activity.this, gizimeter_artikel_gizi.class));
            }
        });
    }

    private void viewPager() {
        ViewPager viewPager = findViewById(R.id.viewpager);
        SliderAdapter myadapter = new SliderAdapter(this);
        viewPager.setAdapter(myadapter);
        viewPager.addOnPageChangeListener(viewListener);
        dotsIndicator(0);
    }

    private void hasilMonitorGizi() {
        btnRiwayatHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gizimeter_main_activity.this, gizimeter_detail_monitor_gizi2.class));
            }
        });
    }

    private void monitorGizi() {
        btnMonitorGizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gizimeter_main_activity.this, gizimeter_monitor_gizi2.class));
            }
        });
    }

    private void kamusGizi() {
        btnKamusGizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gizimeter_main_activity.this, gizimeter_kamus_gizi.class));
            }
        });
    }

    private void cekGizi() {
        btnHitungGizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gizimeter_main_activity.this, gizimeter_list_data.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Panduan:
                startActivity(new Intent(gizimeter_main_activity.this, gizimeter_panduan_penggunaan.class));
                break;
            case R.id.Tentang:
                showMessage("Tentang", "Aplikasi Gizimeter ini dibuat oleh : \n- Harsa Yamani \n- Inez Aryani Suryana \n- Rahayu ");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dotsIndicator(int position){
        TextView[] ndots = new TextView[3];
        linearLayout.removeAllViews();
        for(int i = 0; i<ndots.length; i++){
            ndots[i] = new TextView(this);
            ndots[i].setText(Html.fromHtml("&#8226;"));
            ndots[i].setTextSize(35);
            ndots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            linearLayout.addView(ndots[i]);
        }

        ndots[position].setTextColor(getResources().getColor(R.color.colorWhite));
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            dotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}
