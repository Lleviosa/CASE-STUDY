public class Dokumen extends Cetak {
    private String IDMenu;

    public Dokumen(String IDMenu, String namaMenu, int hargaFinal) {
        super(namaMenu, hargaFinal);
        this.IDMenu = IDMenu;
    }

    public int hargaCetak() {
        if (!isWarna()) {
            setHargaFinal(getJumlahLembar() * getHargaPerLembar() * getJumlahCetak());
            return hargaFinal;
        } else {
            setHargaPerLembar(getHargaPerLembar() + 250);
            setHargaFinal(getJumlahLembar() * getHargaPerLembar() * getJumlahCetak());
            return hargaFinal;
        }
    }
}