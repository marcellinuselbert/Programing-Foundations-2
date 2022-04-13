public class Dokter extends Warga {

  private int jumlahPasienDitemui;
  private String penyakitKeahlian;
  private boolean dokterRamah;

  // constructor
  // call super constructor to fill the nama
  Dokter(String nama, String penyakitKeahlian, boolean dokterRamah) {
    super(nama);
    this.penyakitKeahlian = penyakitKeahlian;
    this.dokterRamah = dokterRamah;
  }

  // if x pasien type then increment jumlahPasien
  @Override
  public void berinteraksi(Warga X) {
    if (X instanceof Pasien) {
      this.jumlahPasienDitemui++;
    }
    // add to log
    addLogInteraksi(X);
  }

  // call toString warga
  @Override
  public String toString() {
    return super.toString();
  }

  public int getJumlahPasienDitemui() {
    return this.jumlahPasienDitemui;
  }

  public String getPenyakitKeahlian() {
    return this.penyakitKeahlian;
  }

  public boolean getDokterRamah() {
    return this.dokterRamah;
  }
}
