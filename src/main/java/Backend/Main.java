package Backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.*;
import javax.xml.catalog.CatalogException;

public class Main {
    public static Map<String, Pelanggan> userMap = new HashMap<>();
    public static Map<String, Promosi> promosiMap = new HashMap<>();
    public static Map<String, Cetak> cetakMap = new HashMap<>();
    public static Map<String, Pesanan> orderMap = new HashMap<>();
    public static Map<String, Integer> pesanan =  new HashMap<>();
    public static ArrayList<String> inputHistory = new ArrayList<>();
    public static DecimalFormat dec = new DecimalFormat("#,###");

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        Scanner in = new Scanner(System.in);
        BufferedReader inb = new BufferedReader(new InputStreamReader(System.in));
        String namaDepan;
        String namaBelakang;
        String ID;
        String idPesan;
        String idPromo;
        String adminPassword = "admin";
        boolean order1 = true;
        boolean order2 = true;
        boolean promo=false;

        System.out.println("Apakah anda member?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        System.out.println("3. Admin");
        System.out.println("4. Exit");
        int n = in.nextInt();
        in.nextLine();

        switch (n) {
            case 1:
                System.out.println("Masukkan nama depan anda");
                namaDepan = in.nextLine();
                System.out.println("Masukkan nama belakang anda");
                namaBelakang = in.nextLine();
                System.out.println("Masukkan nomor member anda");
                ID = in.nextLine();
                Member member = new Member(ID, namaDepan, now.toString(), 0);
                System.out.println("==========================================");
                member.getFullName();
                member.showMemberAge();
                System.out.println("==========================================");

                do {
                    System.out.println("Selamat datang di percetakan FILKOM!");
                    System.out.println("Apa yang ingin anda lakukan pada sesi ini?");
                    System.out.println("Menu: ");
                    System.out.println("1. Membuat Pesanan");
                    System.out.println("2. Lihat Keranjang");
                    System.out.println("3. Top-up Saldo");
                    System.out.println("4. Bayar Pesanan");
                    int pilihan1 = in.nextInt();
                    switch (pilihan1) {
                        case 1:
                            member.makeOrder();
                            continue;
                        case 2:
                            // Pesanan.printDetails();
                            break;
                        case 3:
                            // member.topUp();
                            break;
                        case 4:
                            // member.confirmPay();
                            order1 = false;
                            break;
                    }
                } while (order1);

                break;
            case 2:
                System.out.println("Masukkan nama depan anda");
                namaDepan = in.nextLine();
                Guest guest = new Guest(namaDepan, 0);
                System.out.println("==========================================");
                guest.getFullName();
                System.out.println("==========================================");

                do {
                    System.out.println("Selamat datang di percetakan FILKOM!");
                    System.out.println("Apa yang ingin anda lakukan pada sesi ini?");
                    System.out.println("Menu: ");
                    System.out.println("1. Membuat Pesanan");
                    System.out.println("2. Bayar Pesanan");
                    System.out.println("3. Batalkan Pesanan");
                    int pilihan1 = in.nextInt();
                    in.nextLine();
                    switch (pilihan1) {
                        case 1:
                            guest.makeOrder();
                            continue;
                        case 2:
                            // guest.confirmPay();
                            order2 = false;
                            break;
                    }
                } while (order2);
                break;

            case 3:
                while (true) {
                    System.out.println("Masukkan password admin");
                    System.out.println("Ketik 0 untuk keluar");
                    String x = in.nextLine();
                    if (x.equals(adminPassword)) {
                        System.out.println("Access Granted!");
                        while (true) {
                            try {
                                String input = inb.readLine();
                                inputHistory.add(input);
                                String[] arr = input.split(" ", 4);
                                String perintah1 = arr[0];

                                switch (perintah1) {
                                    case "EXIT":
                                        System.exit(-1);
                                    case "CREATE":
                                        String perintah2 = arr[1];
                                        switch (perintah2) {
                                            case "MEMBER":
                                                String[] splitMember = arr[2].split("\\|");
                                                ID = splitMember[0];
                                                if (userMap.containsKey(ID)) {
                                                    System.out.println("CREATE MEMBER FAILED: " + ID + " IS EXIST");
                                                } else {
                                                    userMap.put(ID, new Member(splitMember[0], splitMember[1],
                                                            splitMember[2], Integer.parseInt(splitMember[3])));
                                                    System.out.println(
                                                            "CREATE MEMBER SUCCESS: " + ID + " " + splitMember[1]);
                                                }
                                                break;
                                            case "GUEST":
                                                String[] splitGuest = arr[2].split("\\|");
                                                ID = splitGuest[0];
                                                if (userMap.containsKey(ID)) {
                                                    System.out.println("CREATE GUEST FAILED: " + ID + " IS EXIST");
                                                } else {
                                                    userMap.put(ID,
                                                            new Guest(splitGuest[0], Integer.parseInt(splitGuest[1])));
                                                    System.out.println("CREATE GUEST SUCCESS: " + ID);
                                                }
                                                break;
                                            case "MENU":
                                                String perintah3 = arr[2];
                                                switch (perintah3) {
                                                    case "FOTOKOPI":
                                                        String perintah4 = arr[3];
                                                        String[] splitMenu = perintah4.split("\\|");
                                                        idPesan = splitMenu[0];
                                                        if (cetakMap.containsKey(idPesan)) {
                                                            System.out.println(
                                                                    "CREATE MENU FAILED: " + idPesan + " IS EXIST");
                                                        } else {
                                                            cetakMap.put(idPesan, new Fotokopi(splitMenu[0],
                                                                    splitMenu[1], Integer.parseInt(splitMenu[2])));
                                                            if (perintah4.contains("DS")) {
                                                                ((Fotokopi) cetakMap.get(idPesan)).setDoubleSided(true);
                                                            }

                                                            System.out.println("CREATE MENU SUCCESS: " + idPesan + " "
                                                                    + splitMenu[1]);
                                                        }
                                                        break;
                                                    case "CETAK":
                                                        perintah4 = arr[3];
                                                        splitMenu = perintah4.split("\\|");
                                                        idPesan = splitMenu[0];
                                                        if (cetakMap.containsKey(idPesan)) {
                                                            System.out.println(
                                                                    "CREATE MENU FAILED: " + idPesan + " IS EXIST");
                                                        } else {
                                                            cetakMap.put(idPesan, new Dokumen(splitMenu[0],
                                                                    splitMenu[1], Integer.parseInt(splitMenu[2])));
                                                            System.out.println("CREATE MENU SUCCESS: " + idPesan + " "
                                                                    + splitMenu[1]);
                                                        }
                                                        break;
                                                }
                                                break;
                                            case "PROMO":
                                                perintah3 = arr[2];
                                                switch (perintah3) {
                                                    case "DELIVERY":
                                                        String perintah4 = arr[3];
                                                        String[] splitPromo = perintah4.split("\\|");
                                                        idPromo = splitPromo[0];
                                                        if (promosiMap.containsKey(idPromo)) {
                                                            System.out.println("CREATE PROMO DELIVERY FAILED: "
                                                                    + idPromo + " IS EXIST");
                                                        } else {
                                                            promosiMap.put(idPromo, new DiskonOngkir(idPromo,
                                                                    LocalDate.parse(splitPromo[1],
                                                                            DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                                                                    LocalDate.parse(splitPromo[2],
                                                                            DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                                                                    Double.parseDouble(splitPromo[3].replace("%", "")),
                                                                    Integer.parseInt(splitPromo[4]),
                                                                    Integer.parseInt(splitPromo[5])));
                                                            System.out.println("CREATE PROMO SUCCESS: " + idPromo);
                                                        }
                                                        break;
                                                    case "CASHBACK":
                                                        perintah4 = arr[3];
                                                        splitPromo = perintah4.split("\\|");
                                                        idPromo = splitPromo[0];
                                                        if (promosiMap.containsKey(idPromo)) {
                                                            System.out.println("CREATE PROMO CASHBACK FAILED: "
                                                                    + idPromo + " IS EXIST");
                                                        } else {
                                                            promosiMap.put(idPromo, new Cashback(idPromo,
                                                                    LocalDate.parse(splitPromo[1],
                                                                            DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                                                                    LocalDate.parse(splitPromo[2],
                                                                            DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                                                                    Double.parseDouble(splitPromo[3].replace("%", "")),
                                                                    Integer.parseInt(splitPromo[4]),
                                                                    Integer.parseInt(splitPromo[5])));
                                                            System.out.println("CREATE PROMO SUCCESS: " + idPromo);
                                                        }
                                                        break;
                                                    case "DISCOUNT":
                                                        perintah4 = arr[3];
                                                        splitPromo = perintah4.split("\\|");
                                                        String ubah = splitPromo[3].replace("%", "");
                                                        Double ubahDo = Double.parseDouble(ubah);
                                                        System.out.println(ubahDo);
                                                        idPromo = splitPromo[0];
                                                        if (promosiMap.containsKey(idPromo)) {
                                                            System.out.println("CREATE PROMO DISCOUNT FAILED: "
                                                                    + idPromo + " IS EXIST");
                                                        } else {
                                                            promosiMap.put(idPromo, new Diskon(idPromo,
                                                                    LocalDate.parse(splitPromo[1],
                                                                            DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                                                                    LocalDate.parse(splitPromo[2],
                                                                            DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                                                                    (ubahDo),
                                                                    Integer.parseInt(splitPromo[4]),
                                                                    Integer.parseInt(splitPromo[5])));
                                                            System.out.println("CREATE PROMO SUCCESS: " + idPromo);
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                    case "ADD_TO_CART":
                                        perintah2 = arr[1];
                                        String perintah3 = arr[2];
                                        int jumlah = Integer.parseInt(arr[3]);
                                        try {
                                            if (userMap.get(perintah2).keranjang
                                                    .checkKerNew(cetakMap.get(perintah3))) {

                                                for (Map.Entry<String, Integer> entry : pesanan.entrySet()) {
                                                    jumlah = entry.getValue()+jumlah;
                                                }

                                                System.out.println("ADD_TO_CART SUCCESS: " + jumlah + " "
                                                        + cetakMap.get(perintah3).getNama()
                                                        + " QUANTITY IS INCREMENTED");
                                                userMap.get(perintah2).addIncrement(perintah3, jumlah);
                                            } else if (cetakMap.containsKey(perintah3)) {

                                                pesanan.put(perintah3, jumlah);
                                                for (Map.Entry<String, Integer> entry : pesanan.entrySet()) {
                                                    jumlah = entry.getValue();
                                                }


                                                System.out.println("ADD_TO_CART SUCCESS: " + jumlah + " " + cetakMap.get(perintah3).getNama()
                                                        + " IS ADDED");
                                                userMap.get(perintah2).addNew(cetakMap.get(perintah3), String.valueOf(jumlah));
                                            } else {
                                                System.out.println("ADD TO CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                                            }
                                        }
                                        catch (Exception e) {
                                            System.out.println("ADD TO CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                                        }
                                        break;
                                    case "REMOVE_FROM_CART":
                                        perintah2 = arr[1];
                                        perintah3 = arr[2];
                                        jumlah = Integer.parseInt(arr[3]);
                                        try {
                                            userMap.get(perintah2).addNew(cetakMap.get(perintah3), String.valueOf(jumlah));


                                            for (Map.Entry<String, Integer> entry : pesanan.entrySet()) {
                                                jumlah = entry.getValue()-jumlah;
                                                if(jumlah<=0){
                                                    System.out.println("REMOVE_FROM_CART SUCCESS: " + perintah3 + "IS REMOVED");
                                                }
                                            }

                                            System.out.println("REMOVE_FROM_CART SUCCESS: " + jumlah + " "
                                                    + cetakMap.get(perintah3).getNama()
                                                    + " IS DECREMENTED");
                                        } catch (Exception e) {
                                            System.out
                                                    .println("REMOVE_FROM_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                                        }

                                    case "PRINT":
                                        try {
                                            printStruk(arr[1], promo);
                                        } catch (Exception e) {
                                            System.out.println("PRINT FAILED: NON EXISTENT CUSTOMER");
                                        }
                                        break;
                                    case "APPLY_PROMO": ;
                                        perintah2 = arr[1];
                                        perintah3 = arr[2];

                                        if (userMap.get(perintah2).keranjang.isNull()) {
                                            userMap.get(perintah2).keranjang.setPromosi(promosiMap.get(perintah3));
                                            System.out.println("APPLY_PROMO SUCCESS: " + (perintah3));
                                            promo=true;
                                        } else {
                                            System.out.println(
                                                    "APPLY_PROMO FAILED: " + (perintah3));
                                        }
                                        break;
                                    case "TOPUP":
                                        try {
                                            perintah2 = arr[1];
                                            perintah3 = arr[2];
                                            userMap.get(perintah2).topUp(Integer.parseInt(perintah3));
                                            System.out.println(
                                                    "TOPUP SUCCESS: " + dec.format(userMap.get(perintah2).getNamaAwal())
                                                            + " "
                                                            + userMap.get(perintah2).getSaldo()
                                                            + " => " + dec.format(userMap.get(perintah2).getSaldo()));
                                        } catch (Exception e) {
                                            System.out.println("TOPUP FAILED: NON EXISTENT CUSTOMER");
                                        }

                                        break;
                                    case "CHECK_OUT":
                                        // Kode pelanggan (perintah 2-perintah 3 member)
                                        perintah2 = arr[1]; // ini mapnya masih salah
                                        try {
                                            if (userMap.get(perintah2).getSaldo() >= userMap.get(perintah2).keranjang
                                                    .getSubtotal()) {
                                                userMap.get(perintah2).confirmPay(userMap.get(perintah2).keranjang);
                                                System.out.println(
                                                        "CHECK_OUT SUCCESS: " + perintah2 + " "
                                                                + userMap.get(perintah2).getNamaAwal());
                                            } else {
                                                System.out.println("CHECK_OUT FAILED: " + perintah2 + " "
                                                        + userMap.get(perintah2).getNamaAwal()
                                                        + " INSUFFICIENT BALANCE");
                                            }
                                        } catch (Exception e) {
                                            System.out.println("CHECK_OUT FAILED: NON EXISTENT CUSTOMER");
                                        }

                                        break;
                                    case "PRINT_HISTORY":
                                        try {
                                            orderHistory(arr[1]);
                                        } catch (Exception E) {
                                            System.out.println("NON EXISTENT CUSTOMER");
                                        }
                                        break;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Index out of bounds");
                            } catch (IOException e) {
                                System.out.println("IO Error");
                                // } catch (Exception e) {
                                // System.out.println("Unknown Exception Error");
                                if (x.equals("0")) {
                                    return;
                                } else {
                                    System.out.println("No command found");
                                }
                            }
                        }
                    }
                    break;
                }

            case 4:
                System.exit(-1);
        }
        in.close();
    }

    static void orderHistory(String token) {
        int i = 0;
        System.out.println("Kode pelanggan: " + token);
        System.out.println("Nama: " + userMap.get(token).getNamaAwal());
        System.out.println("Saldo: " + userMap.get(token).getSaldo());
        System.out.printf("%3s | %-20s | %3s | %8s \n", "No", "Menu", "Qty", "Subtotal");
        System.out.println("==================================================");
        Iterator<Map.Entry<String, Cetak>> iterator = userMap.get(token).keranjang.kerNew.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Cetak> entry = iterator.next();
            i++;
            String menu = entry.getValue().getNama().length() >= 20 ? entry.getValue().getNama().substring(0, 20) : entry.getValue().getNama();
            System.out.printf("%3d | %-20s | %3d | %8s \n", i, menu, entry.getValue().getJumlahCetak(), entry.getValue().getHargaFinal());
        }

    }

    static void printStruk(String token, boolean promo) throws DiskonExceededException, DiskonOngkirExceededException {
        int i = 0;
        System.out.println("Kode pelanggan: " + token);
        System.out.println("Nama: " + userMap.get(token).getNamaAwal());
        System.out.println("Saldo: " + userMap.get(token).getSaldo());
        System.out.printf("%3s | %-20s | %3s | %8s \n", "No", "Menu", "Qty", "Subtotal");
        System.out.println("==================================================");
        Iterator<Map.Entry<String, Cetak>> iterator = userMap.get(token).keranjang.kerNew.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Cetak> entry = iterator.next();
            i++;
            String menu = entry.getValue().getNama().length() >= 20 ? entry.getValue().getNama().substring(0, 20) : entry.getValue().getNama();
            int subtotal = entry.getValue().getJumlahCetak()*entry.getValue().getHargaFinal();
            System.out.printf("%3d | %-20s | %3d | %8s \n", i, menu, entry.getValue().getJumlahCetak(), subtotal);
        }
        System.out.println("==================================================");
        System.out.println("Total : " + dec.format(userMap.get(token).keranjang.getSubtotal()));
        System.out.println("Ongkos kirim : " + dec.format(userMap.get(token).keranjang.getOngkir()));

        System.out.println("==================================================");
        System.out.println("Total : " + dec.format(userMap.get(token).keranjang.getTotal() + userMap.get(token).keranjang.getOngkir()));
                // + dec.format(userMap.get(token).keranjang.getSubtotal() + userMap.get(token).keranjang.getOngkir()));
        if(promo){System.out.println("PROMO : " + userMap.get(token).keranjang.getPromosi());}
        System.out.println("Sisa saldo : " + dec.format(userMap.get(token).getSaldo()));

    }

    // static void orderHistoryGuest(String token) {
    // System.out.println("Kode pelanggan: " + guestMap.get(token));
    // System.out.println("Nama: " + guestMap.get(token).getNamaAwal());
    // System.out.println("Saldo: " + guestMap.get(token).getSaldo());
    // System.out.printf("%3s | %-20s | %3s | %8s \n", "No", "Menu", "Qty",
    // "Subtotal");
    // System.out.println("==================================================");
    // for (int i = 0; i < guestMap.get(token).keranjang.size(); i++) {
    // System.out.println(i + " | " + guestMap.get(token).keranjang.get(i) + " | "
    // + guestMap.get(token).keranjang.get(i).getJumlahCetak() + " | "
    // + guestMap.get(token).keranjang.get(i).getHargaFinal() + " | " + "placeholder
    // diskon");

    // }

    // }
}
