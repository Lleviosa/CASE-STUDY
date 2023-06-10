import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

public class Member extends Pelanggan {
    // Diskon diskon = new Diskon();
    public String ID;

    public Member(String ID, String namaAwal, String tanggalMember, int saldo) { //TODO tanggalmember
        super(namaAwal,saldo);
        this.ID = ID;
    }

    public void showMemberAge() {
        DateFormat dateAwal = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();

        try {
            Date tglAwal = dateAwal.parse(ID.substring(6, 8) + "/" + ID.substring(4, 6) + "/" + ID.substring(0, 4));

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(tglAwal);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(today);

            String hasil = String.valueOf(selisih(cal1, cal2));

            System.out.println("Lama menjadi member: " + hasil + " hari");

        } catch (ParseException e) {
            System.out.println("Parse Exception!");
        }
    }

    public int getMemberAge() throws InputMismatchException, ParseException {
        DateFormat dateAwal = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        try {
            Date tglAwal = dateAwal.parse(ID.substring(6, 8) + "/" + ID.substring(4, 6) + "/" + ID.substring(0, 4));

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(tglAwal);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(today);

            String hasil = String.valueOf(selisih(cal1, cal2));
            return Integer.parseInt(hasil);
        } catch (InputMismatchException e) {
            System.out.println("Bukan Member!");
            return 0;
        } catch (ParseException e) {
            System.out.println("Parse Error!");
            return 0;
        }
    }

    // Selisih hari
    private static long selisih(Calendar tanggalAwal, Calendar tanggalAkhir) {
        long lama = 0;
        Calendar tanggal = (Calendar) tanggalAwal.clone();
        while (tanggal.before(tanggalAkhir)) {
            tanggal.add(Calendar.DAY_OF_MONTH, 1);
            lama++;
        }
        return lama;
    }
}
