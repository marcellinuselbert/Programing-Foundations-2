public class Barang {

  private String nama;
  private int harga;
  private int beratBarang;
  private int stock;

  public Barang(String nama, int harga, int beratBarang, int stock) {
    this.nama = nama;
    this.harga = harga;
    this.beratBarang = beratBarang;
    this.stock = stock;
  }

  // if barang stock more than parameter or same with params then return true else return false
  boolean isStockReady(int stock) {
    return this.stock >= stock;
  }

  String getNama() {
    return nama;
  }

  int getStock() {
    return stock;
  }

  void setStock(int kuantitas) {
    this.stock = kuantitas;
  }

  int getBeratBarang() {
    return beratBarang;
  }

  int getHarga() {
    return harga;
  }
}
