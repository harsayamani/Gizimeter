package com.example.harsayamani.gizimeter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseKamusGiziHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "db_resep";

    public DatabaseKamusGiziHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS resep(_id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, bahan TEXT, cara TEXT, img BLOB)";
        db.execSQL(sql);
        ContentValues values = new ContentValues();
        values.put("_id", "1");
        values.put("nama", "Abon Sapi");
        values.put("bahan", "Banyaknya Abon Sapi yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Abon Sapi yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Abon Sapi = 212 kkal\n" +
                "Jumlah Kandungan Protein Abon Sapi = 18 gr\n" +
                "Jumlah Kandungan Lemak Abon Sapi = 10,6 gr\n" +
                "Jumlah Kandungan Karbohidrat Abon Sapi = 59,3 gr\n" +
                "Jumlah Kandungan Kalsium Abon Sapi = 150 mg\n" +
                "Jumlah Kandungan Fosfor Abon Sapi = 209 mg\n" +
                "Jumlah Kandungan Zat Besi Abon Sapi = 12,3 mg\n" +
                "Jumlah Kandungan Vitamin A Abon Sapi = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Abon Sapi = 0,17 mg\n" +
                "Jumlah Kandungan Vitamin C Abon Sapi = 0 mg");
        values.put("cara", "Dikonsumsi sebagai lauk pauk");
        values.put("img", R.drawable.abonsapi); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "2");
        values.put("nama", "Acar Timun");
        values.put("bahan", "Banyaknya Acar Timun yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Acar Timun yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Acar Timun = 10 kkal\n" +
                "Jumlah Kandungan Protein Acar Timun = 0,5 gr\n" +
                "Jumlah Kandungan Lemak Acar Timun = 0,2 gr\n" +
                "Jumlah Kandungan Karbohidrat Acar Timun = 2 gr\n" +
                "Jumlah Kandungan Kalsium Acar Timun = 20 mg\n" +
                "Jumlah Kandungan Fosfor Acar Timun = 98 mg\n" +
                "Jumlah Kandungan Zat Besi Acar Timun = 1 mg\n" +
                "Jumlah Kandungan Vitamin A Acar Timun = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Acar Timun = 3 mg\n" +
                "Jumlah Kandungan Vitamin C Acar Timun = 0 mg");
        values.put("cara", "Dikonsumsi sebagai lauk pauk");
        values.put("img", R.drawable.acartimun); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "3");
        values.put("nama", "Aci Aren");
        values.put("bahan", "Banyaknya Aci Aren yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Aci Aren yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Aci Aren = 355 kkal\n" +
                "Jumlah Kandungan Protein Aci Aren = 0,6 gr\n" +
                "Jumlah Kandungan Lemak Aci Aren = 1,1 gr\n" +
                "Jumlah Kandungan Karbohidrat Aci Aren = 85,6 gr\n" +
                "Jumlah Kandungan Kalsium Aci Aren = 91 mg\n" +
                "Jumlah Kandungan Fosfor Aci Aren = 167 mg\n" +
                "Jumlah Kandungan Zat Besi Aci Aren = 2,2 mg\n" +
                "Jumlah Kandungan Vitamin A Aci Aren = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Aci Aren = 0,04 mg\n" +
                "Jumlah Kandungan Vitamin C Aci Aren = 0 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.aciaren); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "4");
        values.put("nama", "Agar-Agar");
        values.put("bahan", "Banyaknya Agar-Agar yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Agar-Agar yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Agar-Agar = 0 kkal\n" +
                "Jumlah Kandungan Protein Agar-Agar = 0 gr\n" +
                "Jumlah Kandungan Lemak Agar-Agar = 0,2 gr\n" +
                "Jumlah Kandungan Karbohidrat Agar-Agar = 0 gr\n" +
                "Jumlah Kandungan Kalsium Agar-Agar = 400 mg\n" +
                "Jumlah Kandungan Fosfor Agar-Agar = 125 mg\n" +
                "Jumlah Kandungan Zat Besi Agar-Agar = 5 mg\n" +
                "Jumlah Kandungan Vitamin A Agar-Agar = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Agar-Agar = 0 mg\n" +
                "Jumlah Kandungan Vitamin C Agar-Agar = 0 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.agaragar); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "5");
        values.put("nama", "Agar-Agar Laut");
        values.put("bahan", "Banyaknya Agar-Agar Laut yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Agar-Agar Laut yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Agar-Agar Laut = 21 kkal\n" +
                "Jumlah Kandungan Protein Agar-Agar Laut = 0,1 gr\n" +
                "Jumlah Kandungan Lemak Agar-Agar Laut = 0,1 gr\n" +
                "Jumlah Kandungan Karbohidrat Agar-Agar Laut = 4,8 gr\n" +
                "Jumlah Kandungan Kalsium Agar-Agar Laut = 133 mg\n" +
                "Jumlah Kandungan Fosfor Agar-Agar Laut = 6 mg\n" +
                "Jumlah Kandungan Zat Besi Agar-Agar Laut = 5,4 mg\n" +
                "Jumlah Kandungan Vitamin A Agar-Agar Laut = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Agar-Agar Laut = 0,03 mg\n" +
                "Jumlah Kandungan Vitamin C Agar-Agar Laut = 0 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.agaragarlaut); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "6");
        values.put("nama", "Air Kelapa Muda");
        values.put("bahan", "Banyaknya Air Kelapa Muda yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Air Kelapa Muda yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Air Kelapa Muda = 17 kkal\n" +
                "Jumlah Kandungan Protein Air Kelapa Muda = 0,2 gr\n" +
                "Jumlah Kandungan Lemak Air Kelapa Muda = 0,1 gr\n" +
                "Jumlah Kandungan Karbohidrat Air Kelapa Muda = 3,8 gr\n" +
                "Jumlah Kandungan Kalsium Air Kelapa Muda = 15 mg\n" +
                "Jumlah Kandungan Fosfor Air Kelapa Muda = 8 mg\n" +
                "Jumlah Kandungan Zat Besi Air Kelapa Muda = 0 mg\n" +
                "Jumlah Kandungan Vitamin A Air Kelapa Muda = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Air Kelapa Muda = 0 mg\n" +
                "Jumlah Kandungan Vitamin C Air Kelapa Muda = 1 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.airkelapa); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "8");
        values.put("nama", "Air Sari Jeruk Manis");
        values.put("bahan", "Banyaknya Air Sari Jeruk Manis yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Air Sari Jeruk Manis yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Air Sari Jeruk Manis = 44 kkal\n" +
                "Jumlah Kandungan Protein Air Sari Jeruk Manis = 0,8 gr\n" +
                "Jumlah Kandungan Lemak Air Sari Jeruk Manis = 0,2 gr\n" +
                "Jumlah Kandungan Karbohidrat Air Sari Jeruk Manis = 11 gr\n" +
                "Jumlah Kandungan Kalsium Air Sari Jeruk Manis = 19 mg\n" +
                "Jumlah Kandungan Fosfor Air Sari Jeruk Manis = 16 mg\n" +
                "Jumlah Kandungan Zat Besi Air Sari Jeruk Manis = 0 mg\n" +
                "Jumlah Kandungan Vitamin A Air Sari Jeruk Manis = 190 IU\n" +
                "Jumlah Kandungan Vitamin B1 Air Sari Jeruk Manis = 0,08 mg\n" +
                "Jumlah Kandungan Vitamin C Air Sari Jeruk Manis = 49 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.airperasanjeruk); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "9");
        values.put("nama", "Air Sari Tomat");
        values.put("bahan", "Banyaknya Air Sari Tomat yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Air Sari Tomat yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Air Sari Tomat = 15 kkal\n" +
                "Jumlah Kandungan Protein Air Sari Tomat = 1 gr\n" +
                "Jumlah Kandungan Lemak Air Sari Tomat = 0,2 gr\n" +
                "Jumlah Kandungan Karbohidrat Air Sari Tomat = 3,5 gr\n" +
                "Jumlah Kandungan Kalsium Air Sari Tomat = 7 mg\n" +
                "Jumlah Kandungan Fosfor Air Sari Tomat = 15 mg\n" +
                "Jumlah Kandungan Zat Besi Air Sari Tomat = 0 mg\n" +
                "Jumlah Kandungan Vitamin A Air Sari Tomat = 600 IU\n" +
                "Jumlah Kandungan Vitamin B1 Air Sari Tomat = 0,05 mg\n" +
                "Jumlah Kandungan Vitamin C Air Sari Tomat = 10 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.airtomat); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "10");
        values.put("nama", "Ampas Kacang");
        values.put("bahan", "Banyaknya Ampas Kacang yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Ampas Kacang yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Ampas Kacang = 96 kkal\n" +
                "Jumlah Kandungan Protein Ampas Kacang = 3,7 gr\n" +
                "Jumlah Kandungan Lemak Ampas Kacang = 0,6 gr\n" +
                "Jumlah Kandungan Karbohidrat Ampas Kacang = 19,1 gr\n" +
                "Jumlah Kandungan Kalsium Ampas Kacang = 133 mg\n" +
                "Jumlah Kandungan Fosfor Ampas Kacang = 150 mg\n" +
                "Jumlah Kandungan Zat Besi Ampas Kacang = 1,3 mg\n" +
                "Jumlah Kandungan Vitamin A Ampas Kacang = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Ampas Kacang = 0,05 mg\n" +
                "Jumlah Kandungan Vitamin C Ampas Kacang = 0 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.ampaskacang); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "11");
        values.put("nama", "Ampas Tahu");
        values.put("bahan", "Banyaknya Ampas Tahu yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Ampas Tahu yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Ampas Tahu = 414 kkal\n" +
                "Jumlah Kandungan Protein Ampas Tahu = 26,6 gr\n" +
                "Jumlah Kandungan Lemak Ampas Tahu = 18,3 gr\n" +
                "Jumlah Kandungan Karbohidrat Ampas Tahu = 41,3 gr\n" +
                "Jumlah Kandungan Kalsium Ampas Tahu = 19 mg\n" +
                "Jumlah Kandungan Fosfor Ampas Tahu = 29 mg\n" +
                "Jumlah Kandungan Zat Besi Ampas Tahu = 4 mg\n" +
                "Jumlah Kandungan Vitamin A Ampas Tahu = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Ampas Tahu = 0,2 mg\n" +
                "Jumlah Kandungan Vitamin C Ampas Tahu = 0 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.ampastahu); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "12");
        values.put("nama", "Ampela Ayam");
        values.put("bahan", "Banyaknya Ampela Ayam yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Ampela Ayam yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Ampela Ayam = 108 kkal\n" +
                "Jumlah Kandungan Protein Ampela Ayam = 17,2 gr\n" +
                "Jumlah Kandungan Lemak Ampela Ayam = 4,1 gr\n" +
                "Jumlah Kandungan Karbohidrat Ampela Ayam = 0,4 gr\n" +
                "Jumlah Kandungan Kalsium Ampela Ayam = 0 mg\n" +
                "Jumlah Kandungan Fosfor Ampela Ayam = 0 mg\n" +
                "Jumlah Kandungan Zat Besi Ampela Ayam = 0 mg\n" +
                "Jumlah Kandungan Vitamin A Ampela Ayam = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Ampela Ayam = 0 mg\n" +
                "Jumlah Kandungan Vitamin C Ampela Ayam = 0 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.ampelaayam); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "13");
        values.put("nama", "Ampela Ayam Goreng");
        values.put("bahan", "Banyaknya Ampela Ayam Goreng yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Ampela Ayam Goreng yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Ampela Ayam Goreng = 270 kkal\n" +
                "Jumlah Kandungan Protein Ampela Ayam Goreng = 32,3 gr\n" +
                "Jumlah Kandungan Lemak Ampela Ayam Goreng = 11,2 gr\n" +
                "Jumlah Kandungan Karbohidrat Ampela Ayam Goreng = 9,9 gr\n" +
                "Jumlah Kandungan Kalsium Ampela Ayam Goreng = 0 mg\n" +
                "Jumlah Kandungan Fosfor Ampela Ayam Goreng = 0 mg\n" +
                "Jumlah Kandungan Zat Besi Ampela Ayam Goreng = 0 mg\n" +
                "Jumlah Kandungan Vitamin A Ampela Ayam Goreng = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Ampela Ayam Goreng = 0 mg\n" +
                "Jumlah Kandungan Vitamin C Ampela Ayam Goreng = 0 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.ampelaayam); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "14");
        values.put("nama", "Anak Mas Rasa Ayam");
        values.put("bahan", "Banyaknya Anak Mas Rasa Ayam yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Anak Mas Rasa Ayam yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Anak Mas Rasa Ayam = 400 kkal\n" +
                "Jumlah Kandungan Protein Anak Mas Rasa Ayam = 7 gr\n" +
                "Jumlah Kandungan Lemak Anak Mas Rasa Ayam = 22 gr\n" +
                "Jumlah Kandungan Karbohidrat Anak Mas Rasa Ayam = 65 gr\n" +
                "Jumlah Kandungan Kalsium Anak Mas Rasa Ayam = 200 mg\n" +
                "Jumlah Kandungan Fosfor Anak Mas Rasa Ayam = 0 mg\n" +
                "Jumlah Kandungan Zat Besi Anak Mas Rasa Ayam = 5 mg\n" +
                "Jumlah Kandungan Vitamin A Anak Mas Rasa Ayam = 1400 IU\n" +
                "Jumlah Kandungan Vitamin B1 Anak Mas Rasa Ayam = 0,75 mg\n" +
                "Jumlah Kandungan Vitamin C Anak Mas Rasa Ayam = 30 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.anakmasrasaayam); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "15");
        values.put("nama", "Anak Mas Rasa Keju");
        values.put("bahan", "Banyaknya Anak Mas Rasa Keju yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Anak Mas Rasa Keju yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Anak Mas Rasa Keju = 440 kkal\n" +
                "Jumlah Kandungan Protein Anak Mas Rasa Keju = 7 gr\n" +
                "Jumlah Kandungan Lemak Anak Mas Rasa Keju = 22 gr\n" +
                "Jumlah Kandungan Karbohidrat Anak Mas Rasa Keju = 65 gr\n" +
                "Jumlah Kandungan Kalsium Anak Mas Rasa Keju = 200 mg\n" +
                "Jumlah Kandungan Fosfor Anak Mas Rasa Keju = 0 mg\n" +
                "Jumlah Kandungan Zat Besi Anak Mas Rasa Keju = 5 mg\n" +
                "Jumlah Kandungan Vitamin A Anak Mas Rasa Keju = 1400 IU\n" +
                "Jumlah Kandungan Vitamin B1 Anak Mas Rasa Keju = 0,75 mg\n" +
                "Jumlah Kandungan Vitamin C Anak Mas Rasa Keju = 30 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.anakmasrasakeju); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "16");
        values.put("nama", "Andaliman");
        values.put("bahan", "Banyaknya Andaliman yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Andaliman yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Andaliman = 99 kkal\n" +
                "Jumlah Kandungan Protein Andaliman = 4,6 gr\n" +
                "Jumlah Kandungan Lemak Andaliman = 1 gr\n" +
                "Jumlah Kandungan Karbohidrat Andaliman = 18 gr\n" +
                "Jumlah Kandungan Kalsium Andaliman = 383 mg\n" +
                "Jumlah Kandungan Fosfor Andaliman = 107 mg\n" +
                "Jumlah Kandungan Zat Besi Andaliman = 2,9 mg\n" +
                "Jumlah Kandungan Vitamin A Andaliman = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Andaliman = 3 mg\n" +
                "Jumlah Kandungan Vitamin C Andaliman = 14,7 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.andaliman); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "17");
        values.put("nama", "Andewi");
        values.put("bahan", "Banyaknya Andewi yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Andewi yang dapat dikonsumsi (Bdd / Food Edible) = 80 %\n" +
                "Jumlah Kandungan Energi Andewi = 25 kkal\n" +
                "Jumlah Kandungan Protein Andewi = 1,6 gr\n" +
                "Jumlah Kandungan Lemak Andewi = 0,2 gr\n" +
                "Jumlah Kandungan Karbohidrat Andewi = 5,3 gr\n" +
                "Jumlah Kandungan Kalsium Andewi = 33 mg\n" +
                "Jumlah Kandungan Fosfor Andewi = 66 mg\n" +
                "Jumlah Kandungan Zat Besi Andewi = 1 mg\n" +
                "Jumlah Kandungan Vitamin A Andewi = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Andewi = 0,14 mg\n" +
                "Jumlah Kandungan Vitamin C Andewi = 10 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.andewi1); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "18");
        values.put("nama", "Arrowroot");
        values.put("bahan", "Banyaknya Arrowroot yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Arrowroot yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Arrowroot = 102 kkal\n" +
                "Jumlah Kandungan Protein Arrowroot = 1 gr\n" +
                "Jumlah Kandungan Lemak Arrowroot = 0,2 gr\n" +
                "Jumlah Kandungan Karbohidrat Arrowroot = 24,1 gr\n" +
                "Jumlah Kandungan Kalsium Arrowroot = 28 mg\n" +
                "Jumlah Kandungan Fosfor Arrowroot = 35 mg\n" +
                "Jumlah Kandungan Zat Besi Arrowroot = 1,7 mg\n" +
                "Jumlah Kandungan Vitamin A Arrowroot = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Arrowroot = 0,06 mg\n" +
                "Jumlah Kandungan Vitamin C Arrowroot = 2 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.arrowroot); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "19");
        values.put("nama", "Asam Cuka");
        values.put("bahan", "Banyaknya Asam Cuka yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Asam Cuka yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Asam Cuka = 12 kkal\n" +
                "Jumlah Kandungan Protein Asam Cuka = 0,1 gr\n" +
                "Jumlah Kandungan Lemak Asam Cuka = 0,1 gr\n" +
                "Jumlah Kandungan Karbohidrat Asam Cuka = 5 gr\n" +
                "Jumlah Kandungan Kalsium Asam Cuka = 7 mg\n" +
                "Jumlah Kandungan Fosfor Asam Cuka = 10 mg\n" +
                "Jumlah Kandungan Zat Besi Asam Cuka = 0 mg\n" +
                "Jumlah Kandungan Vitamin A Asam Cuka = 0 IU\n" +
                "Jumlah Kandungan Vitamin B1 Asam Cuka = 0 mg\n" +
                "Jumlah Kandungan Vitamin C Asam Cuka = 0 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.asamcuka); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

        values.put("_id", "20");
        values.put("nama", "Ayam Goreng");
        values.put("bahan", "Banyaknya Ayam Goreng yang diteliti (Food Weight) = 100 gr \n" +
                "Bagian Ayam Goreng yang dapat dikonsumsi (Bdd / Food Edible) = 100 %\n" +
                "Jumlah Kandungan Energi Ayam Goreng = 595 kkal\n" +
                "Jumlah Kandungan Protein Ayam Goreng = 30,5 gr\n" +
                "Jumlah Kandungan Lemak Ayam Goreng = 0 gr\n" +
                "Jumlah Kandungan Karbohidrat Ayam Goreng = 0 gr\n" +
                "Jumlah Kandungan Kalsium Ayam Goreng = 14 mg\n" +
                "Jumlah Kandungan Fosfor Ayam Goreng = 0 mg\n" +
                "Jumlah Kandungan Zat Besi Ayam Goreng = 1,5 mg\n" +
                "Jumlah Kandungan Vitamin A Ayam Goreng = 810 IU\n" +
                "Jumlah Kandungan Vitamin B1 Ayam Goreng = 0,16 mg\n" +
                "Jumlah Kandungan Vitamin C Ayam Goreng = 0 mg");
        values.put("cara", "Dikonsumsi");
        values.put("img", R.drawable.ayamgoreng); //memasukkan gambar daridrawable
        db.insert("resep", "_id", values);

    }
    public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS resep");
        onCreate(db);
    }
}