package com.zainal.crud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; //untuk melihat log
    private EditText tsNim, tsNama, tsKelas; //pembuatan variabel edittext
    private Button btnTambah, btnLihat; //pembuatan variabel button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        koding ini untuk membuat tulisan di header menjadi ke tengah dengan
//        mengambil tampilan dari layout action_bar_layout.xml
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

//        ini untuk input data dari activity_main.xml
        Log.d(TAG, "onCreate: inisialisasi"); //untuk log pada oncreate
        tsNim = findViewById(R.id.txt_Nim);
        tsNama = findViewById(R.id.txt_Nama);
        tsKelas = findViewById(R.id.txt_Kelas);
        btnTambah = findViewById(R.id.btn_Tambah);
        btnLihat = findViewById(R.id.btn_Lihat);

//       inisialisasi library FAN (Fast Android Networking)
        AndroidNetworking.initialize(getApplicationContext());
        aksiButton();//memanggil fungsi aksiButton()
    }

    public void aksiButton() {
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               mengambil Value input dari activity_main.xml tadi menjadi string
                String nim = tsNim.getText().toString();
                String nama = tsNama.getText().toString();
                String kelas = tsKelas.getText().toString();
//               kodingan jika form tidak di isi apa apa, lalu di klik buttonTambah,
//               maka akan muncul notifikasi seperti di bawah ini
                if (nim.equals("") || nama.equals("") || kelas.equals("")) {
                    Toast.makeText(getApplicationContext(), "Semua data harus diisi",
                            Toast.LENGTH_SHORT).show();
                } else {
                    tambahData(nim, nama, kelas); //memanggil fungsi tambahData()
                    tsNim.setText(""); //mengosongkan form setelah data berhasil ditambahkan
                    tsNama.setText(""); //mengosongkan form setelah data berhasil ditambahkan
                    tsKelas.setText(""); //mengosongkan form setelah data berhasil ditambahkan
                }
            }
        });
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //untuk pindah keActivity lain saat buttonLihat dipencet
                Intent intent = new Intent(MainActivity.this, ReadAllActivity.class);
                startActivity(intent);
            }
        });
    }
    public void tambahData (String nim, String nama, String kelas){
        //koneksi ke file create.php, jika menggunakan localhost gunakan ip pc anda
        AndroidNetworking.post("http://192.168.42.189/fan/create.php")
                .addBodyParameter("id_mahasiswa", "") //id bersifat Auto_Increment tidak perlu diisi/(diisi NULL) cek create.php
                .addBodyParameter("nim_mahasiswa", nim) //mengirimkan data nim_mahasiswa yang akan diisi dengan varibel nim
                .addBodyParameter("nama_mahasiswa", nama) //mengirimkan data nama_mahasiswa yang akan diisi dengan varibel nama
                .addBodyParameter("kelas_mahasiswa", kelas) //mengirimkan data kelas_mahasiswa yang akan diisi dengan varibel kelas
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Handle Response
                        Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
//                       memunculkan Toast saat data berhasil ditambahkan
                        Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError error) {
                        //Handle Error
                        Log.d(TAG, "onError: Failed" + error); //untuk log pada onerror
//                       memunculkan Toast saat data gagal ditambahkan
                        Toast.makeText(getApplicationContext(), "Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}