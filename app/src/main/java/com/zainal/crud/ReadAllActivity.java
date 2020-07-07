package com.zainal.crud;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ReadAllActivity extends AppCompatActivity {
    //inisialisasi variabel
    private static final String TAG = "ReadAllActivity";
    private List<DataMahasiswa> dataMahasiswa;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all);

//       findId recyclerView yg ada pada activity_read_all.xml
        recyclerView = findViewById(R.id.recyclerReadAllData);

        recyclerView.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //menset layout manager sebagai LinearLayout(scroll kebawah)
        dataMahasiswa = new ArrayList<>(); //arraylist untuk menyimpan data mahasiswa
        AndroidNetworking.initialize(getApplicationContext()); //inisialisasi FAN
        getData(); // pemanggilan fungsi get data
    }

    public void getData(){
        //koneksi ke file read_all.php, jika menggunakan localhost gunakan ip PC anda
        AndroidNetworking.get("http://192.168.42.189/fan/read_all.php")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
                        {
                            //mengambil data dari JSON array pada read_all.php
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);
//                                  inisialisasi pengaturan yang di database
                                    dataMahasiswa.add(new DataMahasiswa(
                                            data.getInt("id_mahasiswa"),
                                            data.getString("nama_mahasiswa"),
                                            data.getString("nim_mahasiswa"),
                                            data.getString("kelas_mahasiswa")
                                    ));
                                }
//                               inisialisasi RecyclerView
                                ListMahasiswaAdapter adapter = new ListMahasiswaAdapter(ReadAllActivity.this, dataMahasiswa);
                                recyclerView.setAdapter(adapter); //menset adapter yang akan digunakan pada recyclerView
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } @Override
                    public void onError(ANError error) {
                        Log.d(TAG, "onError: " + error); //untuk log pada onerror
                        // handle error
                    }
                });
    }

}