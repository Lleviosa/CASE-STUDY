public abstract class Promosi implements Applicable, Comparable<Integer> {
    protected String kodePromo;
    protected double persenPotongan;
    protected static final int MIN_ACCOUNT_AGE = 30; // Umur akun minimum untuk eligibility

    public abstract boolean isCustomerEligible(Member member);

    public boolean isMinimumPriceEligible(String order1) {
        return true;
    }

    public String getKodePromo() {
        return kodePromo;
    }

    public void setKodePromo(String kodePromo) {
        this.kodePromo = kodePromo;
    }

    @Override
    public int compareTo(Integer o) {
        return 0;
    }

    public boolean isShippingFreeEligible(String order2) {
        return true;
    }

    public int totalPotongan(int potongan) throws DiskonExceededException {
        return 0;
    }

    public int totalCashback(int cashback) throws CashbackExceededException {
        return 0;
    }

    public int diskonOngkir(int ongkir) throws DiskonOngkirExceededException {
        return 0;
    }
}