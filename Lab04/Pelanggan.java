public class Pelanggan {

  // TODO: Tambahkan modifier
  private String nama;
  private int uang;
  private Order[] keranjang;
  private int kapasitasKeranjang = 5000;
  int kapasitasAwal;
  int pointer = 0;

  public Pelanggan(String nama, int uang, int kapasitas) {
    this.nama = nama;
    this.uang = uang;
    this.kapasitasAwal = kapasitas;
    this.keranjang = new Order[kapasitas];
  }

  String addBarang(Barang barang, int banyakBarang) {
    int banyakAwal = banyakBarang;
    boolean isTooMuch = false;
    // get the weight, multiply single barang weight with amount barang
    int weight = barang.getBeratBarang() * banyakBarang;

    // call method is stock ready from barang to get the info if barang ready?
    if (barang.isStockReady(banyakBarang)) {
      // call method decrement capacity
      this.decrementKapasitas(weight);
      // this while algorithm is to get the sufficient amount of barang
      while (this.kapasitasKeranjang < 0) {
        // while kapasitas keranjang still less than 0 then try to decrement the banyak barang
        banyakBarang--;
        // then add kapasitas with single berat barang
        this.kapasitasKeranjang += barang.getBeratBarang();
        // if this while is running means kapasitas is less than zero then set is too much to true
        isTooMuch = true;
      }
      if (keranjang[pointer] == null) {
        // pointer starts with zero
        // if pointed keranjang item null then add  new barang
        // then go to next pointer (pointer+1)
        // if barang greater than zero than add barang else dont care
        if (banyakBarang > 0) {
          keranjang[pointer] = new Order(barang, banyakBarang);
          pointer++;
        }
      } else {
        // if not null then its exist
        // if exist then set banyak barang with previous banyak barang + banyak barang added
        keranjang[pointer].setBanyakBarang(
            keranjang[pointer].getBanyakBarang() + banyakBarang
          );
      }
      // reduce barang
      barang.setStock(barang.getStock() - banyakBarang);
      // if too much then return "maaf ....", else return berhasil ditambahkan
      return isTooMuch
        ? "Maaf " +
        banyakAwal +
        " " +
        barang.getNama() +
        " terlalu berat, tetapi " +
        banyakBarang +
        " " +
        barang.getNama() +
        " berhasil ditambahkan"
        : this.nama +
        " berhasil menambahkan " +
        banyakBarang +
        " " +
        barang.getNama();
    } else {
      // if requested stock > stock available then return kurang
      return "Stock " + barang.getNama() + " kurang";
    }
  }

  int totalHargaBarang() {
    int res = 0;

    // iterate every keranjang item multiply amount * harga
    for (int i = 0; i < keranjang.length; i++) {
      // if item null then its empty so increment with zero
      if (keranjang[i] == null) {
        res += 0;
      } else {
        res +=
          keranjang[i].getBanyakBarang() * keranjang[i].getBarang().getHarga();
      }
    }
    return res;
  }

  String cekUang() {
    return Integer.toString(this.uang);
  }

  // Setter and Getter dan lengkapi modifier
  String getNama() {
    return this.nama;
  }

  void setNama(String nama) {
    this.nama = nama;
  }

  int getUang() {
    return this.uang;
  }

  void setUang(int uang) {
    this.uang = uang;
  }

  Order[] getKeranjang() {
    return keranjang;
  }

  void resetKeranjang() {
    // reset keranjang to initial capacity
    this.keranjang = new Order[kapasitasAwal];
  }

  void decrementKapasitas(int bobot) {
    this.kapasitasKeranjang -= bobot;
  }
}
