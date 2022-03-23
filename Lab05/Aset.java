public class Aset {

  private String nama;
  private int jumlah;
  private double harga;
  private int tahun = 0;

  Aset(String nama, int jumlah, double harga) {
    this.nama = nama;
    this.jumlah = jumlah;
    this.harga = harga;
  }

  // Increment tahun
  public void nextYear() {
    this.tahun++;
  }

  // getter
  public String getNama() {
    return this.nama;
  }

  public int getJumlah() {
    return this.jumlah;
  }

  public double getHarga() {
    return this.harga;
  }

  public int getTahun() {
    return this.tahun;
  }

  // setter for new harga
  public void setHarga(double newHarga) {
    this.harga = newHarga;
  }
}
