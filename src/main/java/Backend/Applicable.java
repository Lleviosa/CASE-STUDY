public interface Applicable {
    public boolean isCustomerEligible(Member member);
    public boolean isMinimumPriceEligible(String order1);

    public boolean isShippingFreeEligible(String order2);

    public int totalPotongan(int potongan) throws DiskonExceededException;

    public int totalCashback(int cashback) throws CashbackExceededException;

    public int diskonOngkir(int ongkir) throws DiskonOngkirExceededException;
}