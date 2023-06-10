package Backend;

public abstract class Cetak {
    // method abstraknya cuman hargaCetak
    // buat subclassnya yang bedain masih cuman harganya(harus ada method khusus
    // biar beda)
    private boolean warna = false;
    private int jumlahLembar;
    private int jumlahCetak;
    private int hargaPerLembar = 250;
    public int hargaFinal;
    private String nama;

    Cetak(String nama, int hargaFinal) {
        this.hargaFinal = hargaFinal;
        this.nama = nama;
    }

    public int getJumlahCetak() {
        return jumlahCetak;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setJumlahCetak(int jumlahCetak) {
        this.jumlahCetak = jumlahCetak;
    }

    public int getJumlahLembar() {
        return jumlahLembar;
    }

    public void setJumlahLembar(int jumlahLembar) {
        this.jumlahLembar = jumlahLembar;
    }

    public abstract int hargaCetak();

    public int getHargaPerLembar() {
        return hargaPerLembar;
    }

    public void setHargaPerLembar(int hargaPerLembar) {
        this.hargaPerLembar = hargaPerLembar;
    }

    public void setHargaFinal(int hargaFinal) {
        this.hargaFinal = hargaFinal;
    }

    public int getHargaFinal() {
        return hargaFinal;
    }

    public void setWarna(boolean warna) {
        this.warna = warna;
    }

    public boolean isWarna() {
        return warna;
    }

    public void printStruk() {
        System.out.println("Harga total untuk sesi percetakan ini adalah: " + hargaFinal);
    }

    public void ongkir(DiskonOngkir ongkir) {

    }
}
