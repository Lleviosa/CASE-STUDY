import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class Pelanggan {
    Scanner in = new Scanner(System.in);
    Pesanan keranjang = new Pesanan(0);
    private String namaAwal;
    private String namaAkhir;
    private String nomorID;

    private int saldo = 0;

    public void orderHistory() {
        System.out.println("Kode pelanggan : " + nomorID);
        System.out.println("Nama : " + namaAwal);
        System.out.println("Saldo : " + saldo);
        System.out.printf("%3s | %-20s | %3s | %8s \n", "No", "Menu", "Qty", "Subtotal");
        System.out.println("==================================================");
        // for (int i = 0; i < keranjang.keranjang.size(); i++) {
        // System.out.println(
        // i + " | " + keranjang.keranjang.get(i).getNama() +
        // keranjang.keranjang.get(i).getJumlahCetak());
        // }
    }

    public void addIncrement(String key, int input) {
        keranjang.addIncrement(key, input);
    }

    public Pelanggan(String namaAwal, int saldo) {
        this.namaAwal = namaAwal;
        this.saldo = saldo;
    }

    public String getNamaAwal() {
        return namaAwal;
    }

    public String getNamaAkhir() {
        if (namaAkhir.equals(" ") || namaAkhir.equals("")) {
            return "";
        } else {
            return namaAkhir;
        }
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void topUp(int saldo) {
        setSaldo(getSaldo() + saldo);
    }

    public void getFullName() {
        System.out.println("Nama Pelanggan : " + getNamaAwal() + " " + getNamaAkhir());
    }

    // public void addToCart(Cetak menu, String qty) {
    // menu.setJumlahCetak(Integer.parseInt(qty));
    // keranjang.addKeranjang(menu);

    // }

    public void addNew(Cetak menu, String qty) {
        menu.setJumlahCetak(Integer.parseInt(qty));
        keranjang.addKerNew(menu);
    }

    void getStruk(Cetak c, Promosi prom) {
        c.setHargaFinal(c.hargaFinal);

    }

    public void confirmPay(Pesanan order) throws DiskonExceededException, DiskonOngkirExceededException {
        setSaldo(saldo - (keranjang.getSubtotal()));
    }

    @Deprecated // DEPRECATED CODE
    public void makeOrder() {
        // int counterBuku = 0;
        // int counterDocs = 0;
        // int counterFotkop = 0;
        // Scanner in = new Scanner(System.in);
        // List<Jilid> arrayJilid = new ArrayList<Jilid>();
        // List<Dokumen> arrayDokumen = new ArrayList<Dokumen>();
        // List<Fotokopi> arrayFotkop = new ArrayList<>();
        // String judul, penulis;
        // int jumlahLembar, jumlahCetak = 0;
        //
        // System.out.println("Pilihan pesanan");
        // System.out.println("1. Jilid");
        // System.out.println("2. Dokumen");
        // System.out.println("3. Fotokopi");
        // int pilihan2_1 = in.nextInt();
        // switch (pilihan2_1) {
        // case 1:
        // System.out.println("Berapa buku berbeda yang ingin anda cetak?");
        // int jumlahTipe1 = in.nextInt();
        // for (int i = 0; i < jumlahTipe1; i++) {
        // counterBuku++;
        // System.out.println("Buku ke " + (i + 1) + ", Masukkan jumlah lembar");
        // jumlahLembar = in.nextInt();
        // System.out.println("Buku ke " + (i + 1) + ", Masukkan jumlah cetak");
        // jumlahCetak = in.nextInt();
        // in.nextLine();
        // Jilid book = new Jilid(jumlahLembar, jumlahCetak);
        // arrayJilid.add(book);
        // System.out.println("Buku ke " + (i + 1) + ", Masukkan judul Buku");
        // judul = in.nextLine();
        // System.out.println("Buku ke " + (i + 1) + ", Masukkan Penulis Buku");
        // penulis = in.nextLine();
        // book.setAtribut(judul, penulis);
        // System.out.println("Berwarna atau tidak?");
        // System.out.println("1. Ya");
        // System.out.println("2. Tidak");
        // int pilihan3_1 = in.nextInt();
        // switch (pilihan3_1) {
        // case 1:
        // book.setWarna(true);
        // book.hargaCetak();
        // System.out.println("Pilihan jenis cover?");
        // System.out.println("1. Hard Cover");
        // System.out.println("2. Soft Cover");
        // int pilihan3_2 = in.nextInt();
        // switch (pilihan3_2) {
        // case 1:
        // book.setCover();
        // book.sampul();
        // break;
        // case 2:
        // book.sampul();
        // break;
        // }
        // break;
        // case 2:
        // book.hargaCetak();
        // System.out.println("Pilihan jenis cover?");
        // System.out.println("1. Hard Cover");
        // System.out.println("2. Soft Cover");
        // pilihan3_2 = in.nextInt();
        // in.nextLine();
        // switch (pilihan3_2) {
        // case 1:
        // book.setCover();
        // book.sampul();
        // break;
        // case 2:
        // book.sampul();
        // break;
        // }
        // break;
        // }
        // Pesanan.printDetails(counterBuku, arrayJilid.get(i).getJumlahLembar(),
        // arrayJilid.get(i).getJumlahCetak(), arrayJilid.get(i).hargaCetak(), 1);
        // Pesanan pesanan = new Pesanan(arrayJilid.get(i).hargaFinal);
        // nomorID = pesanan.getNomorPesanan();
        // }
        // break;
        // case 2:
        // System.out.println("Berapa buah dokumen berbeda yang ingin anda cetak?");
        // System.out.print("Masukkan jumlah = ");
        // int jumlahTipe2 = in.nextInt();
        // for (int i = 0; i < jumlahTipe2; i++) {
        // counterDocs++;
        // System.out.println("Dokumen ke " + (i + 1) + ", Masukkan jumlah lembar");
        // jumlahLembar = in.nextInt();
        // System.out.println("Dokumen ke " + (i + 1) + ", Masukkan jumlah cetak");
        // jumlahCetak = in.nextInt();
        // in.nextLine();
        // //Dokumen docs = new Dokumen(jumlahLembar, jumlahCetak);
        // arrayDokumen.add(docs);
        // System.out.println("Berwarna atau tidak?");
        // System.out.println("1. Ya");
        // System.out.println("2. Tidak");
        // int pilihan3_2 = in.nextInt();
        // in.nextLine();
        // switch (pilihan3_2) {
        // case 1:
        // docs.setWarna(true);
        // docs.hargaCetak();
        // break;
        // case 2:
        // docs.hargaCetak();
        // break;
        // }
        // Pesanan.printDetails(counterDocs, arrayJilid.get(i).getJumlahLembar(),
        // arrayJilid.get(i).getJumlahCetak(), arrayJilid.get(i).hargaCetak(), 2);
        // Pesanan pesanan = new Pesanan(arrayDokumen.get(i).hargaFinal);
        // nomorID = pesanan.getNomorPesanan();
        // }
        // break;
        // case 3:
        // counterFotkop++;
        // System.out.println("Masukkan jumlah lembar");
        // jumlahLembar = in.nextInt();
        // System.out.println(" Masukkan jumlah cetak");
        // jumlahCetak = in.nextInt();
        // in.nextLine();
        // Fotokopi fotkop = new Fotokopi(jumlahLembar, jumlahCetak);
        // arrayFotkop.add(fotkop);
        // System.out.println("Berwarna atau tidak?");
        // System.out.println("1. Ya");
        // System.out.println("2. Tidak");
        // int pilihan3_3 = in.nextInt();
        // in.nextLine();
        // switch (pilihan3_3) {
        // case 1:
        // fotkop.setWarna(true);
        // System.out.println("Bolak-balik atau tidak?");
        // System.out.println("1. Ya");
        // System.out.println("2. Tidak");
        // int pilihan4_1 = in.nextInt();
        // switch (pilihan4_1) {
        // case 1:
        // fotkop.isDoubleSided();
        // fotkop.doubleSided();
        // fotkop.hargaCetak();
        // break;
        // case 2:
        // fotkop.hargaCetak();
        // break;
        // }
        // break;
        // case 2:
        // fotkop.hargaCetak();
        // break;
        // }
        // Pesanan.printDetails(counterFotkop,
        // arrayFotkop.get(counterFotkop).getJumlahLembar(),
        // arrayFotkop.get(counterFotkop).getJumlahCetak(),
        // arrayFotkop.get(counterFotkop).hargaCetak(),
        // 3);
        // Pesanan pesanan = new Pesanan(arrayFotkop.get(counterFotkop).hargaFinal);
        // nomorID = pesanan.getNomorPesanan();
        // break;
        // }
        // in.close();
    }

}
