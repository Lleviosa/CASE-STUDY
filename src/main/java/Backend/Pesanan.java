package Backend;

import java.security.Key;
import java.time.LocalDate;
import java.util.*;

public class Pesanan {
    private String nomorPesanan;
    private LocalDate tanggalPesanan;
    private int subtotal;
    private int ongkir = 15000;
    private int diskon;
    private int total;
    private int jumlahLembar;
    private StatusPesanan status;
    private Promosi promosi;

    HashMap<String, Cetak> kerNew = new HashMap<>();
    // ArrayList<Cetak> keranjang = new ArrayList<Cetak>();
    
    public boolean checkKerNew(Cetak check) {
        if (kerNew.containsValue(check)) {
            return true;
        } else
            return false;
    }

    public void addIncrement(String key, int input){
        kerNew.get(key).setJumlahCetak(input + kerNew.get(key).getJumlahCetak());
    }

    public void addKerNew(Cetak input) {
        kerNew.put(input.getNama(), input);
    }

    // public boolean checkKeranjang(Cetak check) {
    //     return keranjang.contains(check);
    // }

    // public void addKeranjang(Cetak input) {
    //     keranjang.add(input);
    //     setSubtotal(subtotal + input.getHargaFinal());
    // }

    public boolean isNull() {
        if (promosi == null) {
            return true;
        } else
            return false;
    }

    public String getPromosi() {
        return promosi.getKodePromo();
    }

    public Pesanan(int harga) {
        this.subtotal = harga;
        this.ongkir = 15000;
        this.diskon = 0;
        this.total = subtotal + ongkir - diskon;
        this.status = StatusPesanan.UNPAID;
        this.promosi = null;
    }

    public int getTotal() {
        return total;
    }

    public void setPromosi(Promosi promosi) {
        this.promosi = promosi;
    }

    public double cashback(Cashback cashback) {
        double x = cashback.applyCashback(subtotal);
        return x;
    }

    public double diskon(Diskon diskon) throws DiskonExceededException {
        double x = diskon.totalPotongan(subtotal);
        return x;

    }

    public double ongkir(DiskonOngkir discong) throws DiskonOngkirExceededException {
        double x = discong.calculateDiskOngkir(ongkir);
        return x;

    }

    public int getSubtotal() throws DiskonExceededException, DiskonOngkirExceededException {
        if (promosi instanceof Diskon) {
            setSubtotal((int) diskon((Diskon) promosi) + ongkir);
            return subtotal;
        } else if (promosi instanceof Cashback) {
            setSubtotal((int) cashback((Cashback) promosi) + ongkir);
            return subtotal;
        } else if (promosi instanceof DiskonOngkir) {
            setSubtotal((int) ongkir((DiskonOngkir) promosi) + subtotal);
            return subtotal;
        } else
            return subtotal;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public void setTanggalPesanan() {
        LocalDate today = LocalDate.now();
        this.tanggalPesanan = today;
    }

    public LocalDate getTanggalPesanan() {
        return tanggalPesanan;
    }

    public String getNomorPesanan() {
        return nomorPesanan;
    }

    public void checkOut() {
        this.status = StatusPesanan.SUCCESSFUL;
    }

    public static void printDetails(int count, int jumlahLembar, int jumlahCetak, int harga, int jenis) {

        System.out.println("=============== STRUK PEMBELIAN =============== ");
        for (int i = 1; i <= count; i++) {
            if (jenis == 1) {
                System.out.println("Judul buku " + (i) + "            : " + Jilid.getJudul());
                System.out.println("Penulis buku " + (i) + "          : " + Jilid.getPenulis());
                System.out.println("Jumlah lembar buku ke " + (i) + " : " + jumlahLembar);
                System.out.println("Jumlah cetak buku ke " + (i) + "  : " + jumlahCetak);
                System.out.println("Harga buku ke " + (i) + "         : Rp." + harga);
                System.out.print("\n");
            }
            if (jenis == 2) {
                System.out.println("Jumlah lembar dokumen ke " + (i) + " : " + jumlahLembar);
                System.out.println("Jumlah cetak dokumen ke " + (i) + "  : " + jumlahCetak);
                System.out.println("Harga dokumen ke " + (i) + "         : Rp." + harga);
                System.out.print("\n");
            }
            if (jenis == 3) {
                System.out.println("Jumlah lembar fotokopi : " + jumlahLembar);
                System.out.println("Jumlah cetak fotokopi  : " + jumlahCetak);
                System.out.println("Harga fotokopi         : Rp." + harga);
                System.out.print("\n");
            }
        }

        System.out.println("===============================================");
        System.out.println("\n");
    }

    public int getOngkir() {
        return ongkir;
    }

    public void setOngkir(int ongkir) {
        this.ongkir = ongkir;
    }

    public void pay() {

    }

}
