public class Obligasi extends Aset {

  private double bunga;
  private int maturitas;
  private boolean jatuhTempo = false;

  Obligasi(String nama, int jumlah, double harga, double bunga, int maturitas) {
    // call aset constructor to add nama, jumlah,harga
    super(nama, jumlah, harga);
    this.bunga = bunga;
    this.maturitas = maturitas;
    setJatuhTempo(isJatuhTempo());
  }

  @Override
  public void nextYear() {
    // override aset next year method
    // set jatuh tempo with is Jatuh tempo
    setJatuhTempo(isJatuhTempo());
    // call next year method from aset
    super.nextYear();
    // if not jatuh tempo then add earning with harga*bunga*jumlah
    if (!isJatuhTempo()) {
      Pacilnomo.addToEarnings(this.getHarga() * this.bunga * this.getJumlah());
    }
  }

  @Override
  // override toString from object
  public String toString() {
    return String.format(
      "%s\nTipe: Obligasi\nHarga: %.2f\nJumlah: %d\nBunga: %.2f\nJatuh Tempo: %s",
      getNama(),
      getHarga(),
      getJumlah(),
      this.bunga,
      isJatuhTempo()
    );
  }

  // getter
  public double getBunga() {
    return this.bunga;
  }

  public int getMaturitas() {
    return this.maturitas;
  }

  public boolean getJatuhTempo() {
    return this.jatuhTempo;
  }

  // setter
  public void setJatuhTempo(boolean condition) {
    this.jatuhTempo = condition;
  }

  // isJatuhTempo checker
  public boolean isJatuhTempo() {
    return this.getTahun() > this.maturitas ? true : false;
  }
}
