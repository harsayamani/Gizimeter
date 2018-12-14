package com.example.harsayamani.gizimeter;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class gizimeter_monitor_gizi2 extends AppCompatActivity {

    DatabaseMonitorHelper mvHelper;
    SQLiteDatabase sqLiteDatabase;
    Button pilihTanggal;
    private EditText tanggalCek;
    private SimpleDateFormat dateFormat;
    EditText umurTahun;
    EditText umurBulan;
    EditText beratBadan;
    EditText tinggiBadan;
    Button insertData;
    RadioGroup RGJenisKelamin;
    String jenis_kelamin ;
    Double imt;
    String hasil = null;
    String saran_gizi = null;
    RadioButton radioButtonPerempuan;
    RadioButton radioButtonLakiLaki;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_gizi2);

        mvHelper = new DatabaseMonitorHelper(this);
        sqLiteDatabase = mvHelper.getWritableDatabase();

        tanggalCek = findViewById(R.id.etTanggal);
        umurTahun = findViewById(R.id.etUmurTahun);
        umurBulan = findViewById(R.id.etUmurBulan);
        beratBadan = findViewById(R.id.et_berat_badan);
        tinggiBadan = findViewById(R.id.et_tinggi_badan);
        insertData = findViewById(R.id.btnHasilMonitor);
        RGJenisKelamin = findViewById(R.id.rgJenisKelamin);
        pilihTanggal = findViewById(R.id.btnPilihTanggal);
        radioButtonLakiLaki = findViewById(R.id.Rbperempuan);
        radioButtonPerempuan = findViewById(R.id.Rblakilaki);

        try{
            DateDialog();
            addData();
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Form Harus Diisi dengan Benar", Toast.LENGTH_LONG).show();
        }
    }

    private void DateDialog() {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        pilihTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }

    private  void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                tanggalCek.setText(dateFormat.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void addData() {
        insertData.setOnClickListener(new View.OnClickListener() {

        @Override
            public void onClick(View v) {
              try{
                  Double umur_tahun = Double.parseDouble(umurTahun.getText().toString());
                  Double umur_bulan = Double.parseDouble(umurBulan.getText().toString());
                  if(umur_tahun > 19 || umur_bulan > 11){
                      Toast.makeText(getApplicationContext(), "Anda Salah Memasukkan Inputan", Toast.LENGTH_LONG).show();
                  }else{
                      startActivity(new Intent(gizimeter_monitor_gizi2.this, gizimeter_detail_monitor_gizi2.class));
                      hitungGizi();
                      notifikasiShow();
                      Toast.makeText(getApplicationContext(), "Data Dimasukkan", Toast.LENGTH_LONG).show();
                  }
              }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), "Form Harus Diisi dengan Benar", Toast.LENGTH_LONG).show();
              }
            }

        });
    }

    private void notifikasiShow() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle("Monitor Gizi Dijalankan");
        mBuilder.setContentText("Update monitor gizi...");
        mBuilder.setAutoCancel(false);
        mBuilder.setPriority(1);

        Intent notificationIntent = new Intent(this, gizimeter_monitor_gizi2.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.notify(0, mBuilder.build());
    }

    public void hitungGizi(){
        Double umur_tahun = Double.parseDouble(umurTahun.getText().toString());
        Double umur_bulan = Double.parseDouble(umurBulan.getText().toString());
        Double berat_badan = Double.parseDouble(beratBadan.getText().toString());
        Double tinggi_badan = Double.parseDouble(tinggiBadan.getText().toString());

        int id = RGJenisKelamin.getCheckedRadioButtonId();

        tinggi_badan = tinggi_badan / 100;

        imt = berat_badan / (tinggi_badan * tinggi_badan);
        switch (id){
            case R.id.Rbperempuan:
                jenis_kelamin = "Perempuan";
                if (umur_tahun == 5) {
                    if (umur_bulan <= 11) {
                        if (imt < 11.7 || imt < 11.8) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 11.7 && imt < 12.7) {
                            hasil = "Kurus";
                        } else if (imt >= 12.7 && imt <= 17.0) {
                            hasil = "Normal";
                        } else if (imt > 17.0 && imt <= 19.2) {
                            hasil = "Gemuk";
                        } else if (imt > 19.2) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 6) {
                    if (umur_bulan <= 11) {
                        if (imt < 11.7) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 11.7 && imt < 12.7) {
                            hasil = "Kurus";
                        } else if (imt >= 12.7 && imt <= 17.3) {
                            hasil = "Normal";
                        } else if (imt > 17.3 && imt <= 19.7) {
                            hasil = "Gemuk";
                        } else if (imt > 19.7) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 7) {
                    if (umur_bulan <= 11) {
                        if (imt < 11.9) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 11.9 && imt < 12.9) {
                            hasil = "Kurus";
                        } else if (imt >= 12.9 && imt <= 17.7) {
                            hasil = "Normal";
                        } else if (imt > 17.7 && imt <= 20.5) {
                            hasil = "Gemuk";
                        } else if (imt > 20.5) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 8) {
                    if (umur_bulan <= 11) {
                        if (imt < 12.1) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 12.1 && imt < 13.1) {
                            hasil = "Kurus";
                        } else if (imt >= 13.1 && imt <= 18.3) {
                            hasil = "Normal";
                        } else if (imt > 18.3 && imt <= 21.4) {
                            hasil = "Gemuk";
                        } else if (imt > 21.4) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 9) {
                    if (umur_bulan <= 11) {
                        if (imt < 12.4) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 12.4 && imt < 13.4) {
                            hasil = "Kurus";
                        } else if (imt >= 13.4 && imt <= 19.0) {
                            hasil = "Normal";
                        } else if (imt > 19.0 && imt <= 22.5) {
                            hasil = "Gemuk";
                        } else if (imt > 22.5) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 10) {
                    if (umur_bulan <= 11) {
                        if (imt < 12.7) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 12.7 && imt < 13.8) {
                            hasil = "Kurus";
                        } else if (imt >= 13.8 && imt <= 19.8) {
                            hasil = "Normal";
                        } else if (imt > 19.8 && imt <= 23.6) {
                            hasil = "Gemuk";
                        } else if (imt > 23.6) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 11) {
                    if (umur_bulan <= 11) {
                        if (imt < 13.1) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 13.1 && imt < 14.3) {
                            hasil = "Kurus";
                        } else if (imt >= 14.3 && imt <= 20.7) {
                            hasil = "Normal";
                        } else if (imt > 20.7 && imt <= 24.9) {
                            hasil = "Gemuk";
                        } else if (imt > 24.9) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 12) {
                    if (umur_bulan <= 11) {
                        if (imt < 13.6) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 13.6 && imt < 14.9) {
                            hasil = "Kurus";
                        } else if (imt >= 14.9 && imt <= 21.7) {
                            hasil = "Normal";
                        } else if (imt > 21.7 && imt <= 26.1) {
                            hasil = "Gemuk";
                        } else if (imt > 26.1) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 13) {
                    if (umur_bulan <= 11) {
                        if (imt < 14.0) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 14.0 && imt < 15.4) {
                            hasil = "Kurus";
                        } else if (imt >= 15.4 && imt <= 22.7) {
                            hasil = "Normal";
                        } else if (imt > 22.7 && imt <= 27.2) {
                            hasil = "Gemuk";
                        } else if (imt > 27.2) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 14) {
                    if (umur_bulan <= 11) {
                        if (imt < 14.3) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 14.3 && imt < 15.8) {
                            hasil = "Kurus";
                        } else if (imt >= 15.8 && imt <= 23.5) {
                            hasil = "Normal";
                        } else if (imt > 23.5 && imt <= 28.2) {
                            hasil = "Gemuk";
                        } else if (imt > 28.2) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 15) {
                    if (umur_bulan <= 11) {
                        if (imt < 14.6) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 14.6 && imt < 16.2) {
                            hasil = "Kurus";
                        } else if (imt >= 16.2 && imt <= 24.1) {
                            hasil = "Normal";
                        } else if (imt > 24.1 && imt <= 28.8) {
                            hasil = "Gemuk";
                        } else if (imt > 28.8) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 16) {
                    if (umur_bulan <= 11) {
                        if (imt < 14.7) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 14.7 && imt < 16.3) {
                            hasil = "Kurus";
                        } else if (imt >= 16.3 && imt <= 24.5) {
                            hasil = "Normal";
                        } else if (imt > 24.5 && imt <= 29.3) {
                            hasil = "Gemuk";
                        } else if (imt > 29.3) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 17) {
                    if (umur_bulan <= 11) {
                        if (imt < 14.7) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 14.7 && imt < 16.4) {
                            hasil = "Kurus";
                        } else if (imt >= 16.4 && imt <= 24.8) {
                            hasil = "Normal";
                        } else if (imt > 24.8 && imt <= 29.5) {
                            hasil = "Gemuk";
                        } else if (imt > 29.5) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 18) {
                    if (umur_bulan <= 11) {
                        if (imt < 14.7) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 14.7 && imt < 16.5) {
                            hasil = "Kurus";
                        } else if (imt >= 16.5 && imt <= 25.0) {
                            hasil = "Normal";
                        } else if (imt > 25.0 && imt <= 29.7) {
                            hasil = "Gemuk";
                        } else if (imt > 29.7) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 19) {
                    if (umur_bulan == 0) {
                        if (imt < 14.7) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 14.7 && imt < 16.5) {
                            hasil = "Kurus";
                        } else if (imt >= 16.5 && imt <= 25.0) {
                            hasil = "Normal";
                        } else if (imt > 25.0 && imt <= 29.7) {
                            hasil = "Gemuk";
                        } else if (imt > 29.7) {
                            hasil = "Obesitas";
                        }
                    }
                }
                break;
            case R.id.Rblakilaki:
                jenis_kelamin = "Laki-Laki";
                if (umur_tahun == 5) {
                    if (umur_bulan <= 11) {
                        if (imt < 12.1) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 12.1 && imt < 13.0) {
                            hasil = "Kurus";
                        } else if (imt >= 13.0 && imt <= 16.7) {
                            hasil = "Normal";
                        } else if (imt > 16.7 && imt <= 18.5) {
                            hasil = "Gemuk";
                        } else if (imt > 18.5) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 6) {
                    if (umur_bulan <= 11) {
                        if (imt < 12.2) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 12.2 && imt < 13.1) {
                            hasil = "Kurus";
                        } else if (imt >= 13.1 && imt <= 17.0) {
                            hasil = "Normal";
                        } else if (imt > 17.0 && imt <= 19.0) {
                            hasil = "Gemuk";
                        } else if (imt > 19.0) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 7) {
                    if (umur_bulan <= 11) {
                        if (imt < 12.4) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 12.4 && imt < 13.3) {
                            hasil = "Kurus";
                        } else if (imt >= 13.3 && imt <= 17.4) {
                            hasil = "Normal";
                        } else if (imt > 17.4 && imt <= 19.6) {
                            hasil = "Gemuk";
                        } else if (imt > 19.6) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 8) {
                    if (umur_bulan <= 11) {
                        if (imt < 12.5) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 12.5 && imt < 13.5) {
                            hasil = "Kurus";
                        } else if (imt >= 13.5 && imt <= 17.9) {
                            hasil = "Normal";
                        } else if (imt > 17.9 && imt <= 20.4) {
                            hasil = "Gemuk";
                        } else if (imt > 20.4) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 9) {
                    if (umur_bulan <= 11) {
                        if (imt < 12.8) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 12.8 && imt < 13.7) {
                            hasil = "Kurus";
                        } else if (imt >= 13.7 && imt <= 18.4) {
                            hasil = "Normal";
                        } else if (imt > 18.4 && imt <= 21.3) {
                            hasil = "Gemuk";
                        } else if (imt > 21.3) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 10) {
                    if (umur_bulan <= 11) {
                        if (imt < 13.0) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 13.0 && imt < 14.0) {
                            hasil = "Kurus";
                        } else if (imt >= 14.0 && imt <= 19.1) {
                            hasil = "Normal";
                        } else if (imt > 19.1 && imt <= 22.4) {
                            hasil = "Gemuk";
                        } else if (imt > 22.4) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 11) {
                    if (umur_bulan <= 11) {
                        if (imt < 13.4) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 13.4 && imt < 14.4) {
                            hasil = "Kurus";
                        } else if (imt >= 14.4 && imt <= 19.9) {
                            hasil = "Normal";
                        } else if (imt > 19.9 && imt <= 23.5) {
                            hasil = "Gemuk";
                        } else if (imt > 23.5) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 12) {
                    if (umur_bulan <= 11) {
                        if (imt < 13.8) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 13.8 && imt < 14.9) {
                            hasil = "Kurus";
                        } else if (imt >= 14.9 && imt <= 20.8) {
                            hasil = "Normal";
                        } else if (imt > 20.8 && imt <= 24.7) {
                            hasil = "Gemuk";
                        } else if (imt > 24.7) {
                            hasil = "Obesitas";
                        }
                    }
                } else if (umur_tahun == 13) {
                    if (umur_bulan <= 11) {
                        if (imt < 14.2) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 14.2 && imt < 15.4) {
                            hasil = "Kurus";
                        } else if (imt >= 15.4 && imt <= 21.7) {
                            hasil = "Normal";
                        } else if (imt > 21.7 && imt <= 25.8) {
                            hasil = "Gemuk";
                        } else if (imt > 25.8) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 14) {
                    if (umur_bulan <= 11) {
                        if (imt < 14.7) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 14.7 && imt < 16.0) {
                            hasil = "Kurus";
                        } else if (imt >= 16.0 && imt <= 22.6) {
                            hasil = "Normal";
                        } else if (imt > 22.6 && imt <= 26.9) {
                            hasil = "Gemuk";
                        } else if (imt > 26.9) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 15) {
                    if (umur_bulan <= 11) {
                        if (imt < 15.1) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 15.1 && imt < 16.5) {
                            hasil = "Kurus";
                        } else if (imt >= 16.5 && imt <= 23.5) {
                            hasil = "Normal";
                        } else if (imt > 23.5 && imt <= 27.8) {
                            hasil = "Gemuk";
                        } else if (imt > 27.8) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 16) {
                    if (umur_bulan <= 11) {
                        if (imt < 15.4) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 15.4 && imt < 16.9) {
                            hasil = "Kurus";
                        } else if (imt >= 16.9 && imt <= 24.2) {
                            hasil = "Normal";
                        } else if (imt > 24.2 && imt <= 28.6) {
                            hasil = "Gemuk";
                        } else if (imt > 28.6) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 17) {
                    if (umur_bulan <= 11) {
                        if (imt < 15.7) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 15.7 && imt < 17.3) {
                            hasil = "Kurus";
                        } else if (imt >= 17.3 && imt <= 24.9) {
                            hasil = "Normal";
                        } else if (imt > 24.9 && imt <= 29.2) {
                            hasil = "Gemuk";
                        } else if (imt > 29.2) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 18) {
                    if (umur_bulan <= 11) {
                        if (imt < 15.8) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 15.8 && imt < 17.5) {
                            hasil = "Kurus";
                        } else if (imt >= 17.5 && imt <= 25.4) {
                            hasil = "Normal";
                        } else if (imt > 25.4 && imt <= 29.7) {
                            hasil = "Gemuk";
                        } else if (imt > 29.7) {
                            hasil = "Obesitas";
                        }
                    }
                }else if (umur_tahun == 19) {
                    if (umur_bulan == 0) {
                        if (imt < 15.9) {
                            hasil = "Sangat Kurus";
                        } else if (imt >= 15.9 && imt < 19.6) {
                            hasil = "Kurus";
                        } else if (imt >= 19.6 && imt <= 25.4) {
                            hasil = "Normal";
                        } else if (imt > 25.4 && imt <= 29.7) {
                            hasil = "Gemuk";
                        } else if (imt > 29.7) {
                            hasil = "Obesitas";
                        }
                    }
                }
                break;
        }

        switch (hasil) {
            case "Sangat Kurus":
                saran_gizi = " (1) Tambahkan Kalori yang Sehat \n\n (2) Pilih Makanan Padat Gizi \n\n (3) Ngemil Camilan bergizi \n\n (4) Rajin Berolahraga";
                break;
            case "Kurus":
                saran_gizi = " (1) Tambahkan Kalori yang Sehat \n\n (2) Nutrisi Makanan Harus Cukup \n\n (3) Rajin Berolahraga";
                break;
            case "Gemuk":
                saran_gizi = " (1) Kurangi Konsumsi Karbohidrat Murni \n\n (2) Hindari Makanan yang Mengandung Kolesterol \n\n (3) Perbanyak Makan Sayur-Sayuran \n\n (4) Berolahraga Secara Teratur";
                break;
            case "Obesitas":
                saran_gizi = " (1) Kurangi Porsi Makan Seperempat Porsi dari Kebiasaan Makan \n\n (2) Pilih Makanan yang Mengandung Serat \n\n (3) Hindari Makanan yang Mengandung Gula \n\n (4) Berolahraga dengan Intensitas yang Sering";
                break;
            case "Normal":
                saran_gizi = " (1) Pertahankan Tubuh Anda yang Sehat \n\n (2) Konsumsi Makanan yang Sehat \n\n (3) Rajin Berolahraga Agar Daya Tahan Tubuh Kuat";
                break;
        }


        boolean isInserted = mvHelper.insert_data(tanggalCek.getText().toString(), imt, hasil, saran_gizi);

        if (isInserted){
            Toast.makeText(getApplicationContext(),"Data Ditambahkan",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Data Tidak Ditambahkan",Toast.LENGTH_LONG).show();
        }
    }
}
