package Backend;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class DiskonOngkir extends Promosi {
    private String kodePromo;
    private double persenPotongan;
    private int maksPotongan;
    private int minPembelian;
    private LocalDate tanggalAwal;
    private LocalDate tanggalAkhir;

    public DiskonOngkir(String kodePromo, LocalDate tanggalAwal, LocalDate tanggalAkhir, double persenPotongan,
            int maksPotongan, int minPembelian) {
        this.kodePromo = kodePromo;
        this.persenPotongan = persenPotongan;
        this.maksPotongan = maksPotongan;
        this.minPembelian = minPembelian;
        this.tanggalAwal = tanggalAwal;
        this.tanggalAkhir = tanggalAkhir;
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

    public double getPersenPotongan() {
        return persenPotongan;
    }

    public int getMaksPotongan() {
        return maksPotongan;
    }

    public int getMinPembelian() {
        return minPembelian;
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

    @Override
    public boolean isShippingFreeEligible(String order2) {
        return false;
    }

    public double calculateDiskOngkir(double input) {
        return input*persenPotongan/100;
    }
}