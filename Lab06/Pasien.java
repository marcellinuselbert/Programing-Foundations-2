public class Pasien extends Warga {

  private int happiness;
  private String penyakit;
  private boolean pasienSembuh;

  // constructor
  // call super(nama) to fill nama
  Pasien(String nama, String penyakit) {
    super(nama);
    this.penyakit = penyakit;
  }

  @Override
  public void berinteraksi(Warga X) {
    // if x dokter then check if doctor can cure the disease then +20
    if (X instanceof Dokter) {
      if (
        this.penyakit.equalsIgnoreCase(((Dokter) X).getPenyakitKeahlian()) &&
        !this.pasienSembuh &&
        this.happiness >= 0 &&
        this.happiness <= 100
      ) {
        // if doctor can cure increment +20, set pasienSembuh to true
        this.happiness += 20;
        this.pasienSembuh = true;
      }
      // if get dokter ramah then add 10, else decrement by 5
      if (
        ((Dokter) X).getDokterRamah() &&
        this.happiness >= 0 &&
        this.happiness <= 100
      ) {
        this.happiness += 10;
      } else if (
        this.happiness >= 0 &&
        this.happiness <= 100 &&
        !((Dokter) X).getDokterRamah()
      ) {
        this.happiness -= 5;
      }
      // if pasien then add +5
    } else if (
      this.happiness >= 0 && this.happiness <= 100 && X instanceof Pasien
    ) {
      this.happiness += 5;
    }
    // if happiness is over 100 set to 100
    // else if happiness is less than zero, set to zero
    if (this.happiness > 100) {
      this.happiness = 100;
    } else if (this.happiness < 0) {
      this.happiness = 0;
    }
    // add to log
    addLogInteraksi(X);
  }

  // call toString from warga
  @Override
  public String toString() {
    return super.toString();
  }

  public int getHappiness() {
    return this.happiness;
  }

  public boolean getStatusSembuh() {
    return this.pasienSembuh;
  }

  public String getPenyakit() {
    return this.penyakit;
  }
}
