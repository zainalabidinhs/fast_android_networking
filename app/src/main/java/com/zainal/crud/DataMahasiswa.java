package com.zainal.crud;

public class DataMahasiswa {

    //inisialisasi variabel
    int idMahasiswa; //idMahasiswa didatabase merupakan int (Auto Increment)
    String namaMahasiswa; //namaMahasiswa didatabase merupakan string
    String nimMahasiswa; //nimMahasiswa didatabase merupakan string
    String kelasMahasiswa; //kelasMahasiswa didatabase merupakan string

    //construktor datamahasiswa
    public DataMahasiswa(int idMahasiswa, String namaMahasiswa, String nimMahasiswa, String kelasMahasiswa) {
        this.idMahasiswa = idMahasiswa;
        this.namaMahasiswa = namaMahasiswa;
        this.nimMahasiswa = nimMahasiswa;
        this.kelasMahasiswa = kelasMahasiswa;
    }

    //getter dan setter

    public int getIdMahasiswa() {
        return idMahasiswa;
    }

    public void setIdMahasiswa(int idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getNimMahasiswa() {
        return nimMahasiswa;
    }

    public void setNimMahasiswa(String nimMahasiswa) {
        this.nimMahasiswa = nimMahasiswa;
    }

    public String getKelasMahasiswa() {
        return kelasMahasiswa;
    }

    public void setKelasMahasiswa(String kelasMahasiswa) { this.kelasMahasiswa = kelasMahasiswa; }
}
