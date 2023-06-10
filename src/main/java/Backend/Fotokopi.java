public class Fotokopi extends Cetak {
    private boolean doubleSided = false;
    private String IDMenu;

    public Fotokopi(String IDMenu, String namaMenu, int hargaFinal) {
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

    public void setDoubleSided(boolean doubleSided) {
        this.doubleSided = doubleSided;
    }

    public boolean isDoubleSided() {
        return doubleSided = true;
    }

    public int doubleSided() {
        if (isDoubleSided()) {
            hargaFinal = hargaCetak() / 2;
        }
        return hargaFinal;
    }
}
