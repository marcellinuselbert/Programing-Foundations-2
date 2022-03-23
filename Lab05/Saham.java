public class Saham extends Aset {

  private double dividen;
  private double pertumbuhan;

  Saham(
    String nama,
    int jumlah,
    double harga,
    double pertumbuhan,
    double dividen
  ) {
    // call aset constructor to add nama, jumlah,harga

    super(nama, jumlah, harga);
    this.dividen = dividen;
    this.pertumbuhan = pertumbuhan;
  }

  @Override
  // override from aset
  public void nextYear() {
    // call aset next year method to increment year
    super.nextYear();
    // calculate the growth
    grow();
    // add old harga with harga*growth
    this.setHarga(this.getHarga() + this.getHarga() * this.pertumbuhan);
    // add earning with dividen*harga*jumlah
    Pacilnomo.addToEarnings(this.dividen * this.getHarga() * this.getJumlah());
  }

  // Linear congruential generator for subsequent growth
  void grow() {
    int a = 0x4b;
    int c = 0x4a;
    int m = 2;
    pertumbuhan = ((a * pertumbuhan + c) % m) - 1;
    pertumbuhan = pertumbuhan < 0 ? pertumbuhan % -m : pertumbuhan;
  }

  @Override
  // override to string from Object
  public String toString() {
    return String.format(
      "%s\nTipe: Saham\nHarga: %.2f\nJumlah: %d\nDividen: %.2f\nPertumbuhan: %.2f",
      getNama(),
      getHarga(),
      getJumlah(),
      this.dividen,
      this.pertumbuhan
    );
  }

  // get dividen
  public double getDividen() {
    return this.dividen;
  }
}
