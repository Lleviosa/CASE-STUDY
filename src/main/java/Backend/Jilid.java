package Backend;

public class Jilid extends Cetak {
    private static String judul;
    private static String penulis;
    private boolean cover = false;

    protected void setCover() {
        this.cover = true;
    }
    public boolean isCover() {
        return cover;
    }

    public Jilid(int jumlahLembar, int jumlahCetak) {
        super(String.valueOf(jumlahCetak), jumlahLembar);
    }

    public void setAtribut(String judul, String penulis) {
        Jilid.judul = judul;
        Jilid.penulis = penulis;
    }

    public static String getJudul() {
        return judul;
    }

    public static String getPenulis() {
        return penulis;
    }

    public int hargaCetak() {
        if (!isWarna()) {
            setHargaPerLembar(150);
            setHargaFinal(getJumlahLembar() * getHargaPerLembar() * getJumlahCetak());
            return hargaFinal;
        } else {
            setHargaPerLembar(200);
            setHargaFinal(getJumlahLembar() * getHargaPerLembar() * getJumlahCetak());
            return hargaFinal;
        }
    }

    public int sampul() {
        if (isCover()) {
            hargaFinal = hargaCetak() + 25_000;
            return hargaFinal;
        } else {
            hargaFinal = hargaCetak() + 10_000;
            return hargaFinal;
        }
    }
}