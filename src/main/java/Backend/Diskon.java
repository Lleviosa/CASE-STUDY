package Backend;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class Diskon extends Promosi {
    private String kodePromo;
    private int maksPotongan;
    private int minPembelian;
    private static double persenPotongan;
    private LocalDate tanggalAwal;
    private LocalDate tanggalAkhir;

    public Diskon(String kodePromo, LocalDate tanggalAwal, LocalDate tanggalAkhir, double persenPotongan,
            int maksPotongan, int minPembelian) {
        this.kodePromo = kodePromo;
        this.persenPotongan = persenPotongan;
        this.maksPotongan = maksPotongan;
        this.minPembelian = minPembelian;

    }
    
    public String getTanggalAwal(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return tanggalAwal.format(formatter);
    }
    
    public String getTanggalAkhir(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return tanggalAkhir.format(formatter);
    }

    public String getKodePromo() {
        return kodePromo;
    }

    public int getMaksPotongan() {
        return maksPotongan;
    }

    public int getMinPembelian() {
        return minPembelian;
    }

    public static double getPersenPotongan() {
        return persenPotongan;
    }

    public static double totalPotongan(double totalHarga) throws DiskonExceededException {
        double diskon = totalHarga * ((100 - persenPotongan) / 100);
        if (diskon > totalHarga) {
            System.out.println("Jumlah diskon melebihi batas maksimum.");
            return totalHarga;
        } else
            return diskon;
    }

    public boolean isCustomerEligible(Member member) throws InputMismatchException {
        try {
            if (member.getMemberAge() > MIN_ACCOUNT_AGE) {
                return true;
            } else
                System.out.println("Member kurang dari 30 hari.");
            return false;
        } catch (InputMismatchException e) {
            System.out.println("Bukan member!");
            return false;
        } catch (ParseException e) {
            System.out.println("Parse error.");
            return false;
        }
    }
}