import java.text.ParseException;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class DiskonOngkir extends Promosi {
    private String kodePromo;
    private double persenPotongan;
    private int maksPotongan;

    public DiskonOngkir(String kodePromo, LocalDate tanggalAwal, LocalDate tanggalAkhir, double persenPotongan,
            int maksPotongan, int minPembelian) {
        this.kodePromo = kodePromo;
        this.persenPotongan = persenPotongan;
        this.maksPotongan = maksPotongan;
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