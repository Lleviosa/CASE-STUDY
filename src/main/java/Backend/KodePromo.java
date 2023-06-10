package Backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class KodePromo {
    private static Map<String, Promosi> promoMap = new HashMap<>();

    public static String createPromo(String input) {
        String[] tokens = input.split(" ");

        if (tokens.length != 4) {
            return "CREATE PROMO FAILED: Invalid input format";
        }

        String jenisPromo = tokens[2].toUpperCase();
        String[] promoDetails = tokens[3].split("\\|");

        if (promoDetails.length != 6) {
            return "CREATE PROMO FAILED: Invalid promo details";
        }

        String kodePromo = promoDetails[0];
        String tanggalAwal = promoDetails[1];
        String tanggalAkhir = promoDetails[2];
        double persenPotongan = Double.parseDouble(promoDetails[3].replace("%", ""));
        int maksPotongan = Integer.parseInt(promoDetails[4]);
        int minPembelian = Integer.parseInt(promoDetails[5]);

        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(tanggalAwal, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            endDate = LocalDate.parse(tanggalAkhir, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (Exception e) {
            return "CREATE PROMO FAILED: Invalid date format";
        }

        LocalDate currentDate = LocalDate.now();

        if (currentDate.isAfter(endDate)) {
            return "CREATE PROMO FAILED: Promo has expired";
        }

        if (startDate.isAfter(endDate)) {
            return "CREATE PROMO FAILED: Start date is after end date";
        }

        Promosi promo;

        switch (jenisPromo) {
            case "DISCOUNT" -> {
                promo = new Diskon(kodePromo, startDate, endDate, persenPotongan, maksPotongan, minPembelian);
            }
            case "CASHBACK" -> {
                promo = new Cashback(kodePromo, startDate, endDate, persenPotongan, maksPotongan, minPembelian);
            }
            case "DELIVERY" -> {
                promo = new DiskonOngkir(kodePromo, startDate, endDate, persenPotongan, maksPotongan, minPembelian);
            }
            default -> {
                return "CREATE PROMO " + jenisPromo + " FAILED: Invalid promo type";
            }
        }

        promoMap.put(kodePromo, promo);
        return "CREATE PROMO SUCCESS:" + kodePromo;
    }
    public static String applyPromo(String ID, String idPesanan, double harga) {
        if (idPesanan.equals("DISCOUNT")) {
            double diskon = promoMap.get(idPesanan).persenPotongan*harga;
            harga-=diskon;
            return "APPLY_PROMO SUCCESS: " + idPesanan;
        } else if (idPesanan.equals("CASHBACK")) {
            double cashback = promoMap.get(idPesanan).persenPotongan*harga;
            harga-=cashback;
            return "APPLY_PROMO SUCCESS: " + idPesanan;
        } else if (idPesanan.equals("DELIVERY")) {
            double ongkir = promoMap.get(idPesanan).persenPotongan*harga;
            harga-=ongkir;
            return "APPLY_PROMO SUCCESS: " + idPesanan;
        } else {
            return "CREATE PROMO FAILED: Invalid promo type";
        }
    }
}
