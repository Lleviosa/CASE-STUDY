import java.text.ParseException;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class Diskon extends Promosi {
    private String kodePromo;
    private int maksPotongan;
    private static double persenPotongan;

    public Diskon(String kodePromo, LocalDate tanggalAwal, LocalDate tanggalAkhir, double persenPotongan,
            int maksPotongan, int minPembelian) {
        this.kodePromo = kodePromo;
        Diskon.persenPotongan = persenPotongan;
        this.maksPotongan = maksPotongan;
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