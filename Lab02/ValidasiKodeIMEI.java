import java.util.Scanner;

public class ValidasiKodeIMEI {

  /** Return true jika kode memenuhi ketentuan */
  public static boolean isValid(long kode) {
    return (
      cekPrefix(kode) &&
      (getPanjangKode(kode) == 11) &&
      ((jumlahDoublePosisiGanjil(kode) + jumlahPosisiGenap(kode)) % 10 == 0)
    );
  }

  /**
   * Dapatkan hasil dari langkah (b)
   * (harus rekursif), boleh menggunakan helper method
   */
  public static int jumlahDoublePosisiGanjil(long kode) {
    String kodeString = String.valueOf(kode);

    if (kodeString.length() == 1) return getDigit(
      Integer.parseInt(kodeString.substring(0, 1)) * 2
    ); else {
      return (
        getDigit(Integer.parseInt(kodeString.substring(0, 1)) * 2) +
        jumlahDoublePosisiGanjil(Long.parseLong(kodeString.substring(2)))
      );
    }
  }

  /**
   * Jika hasil kali dua digit tersebut menghasilkan angka yang lebih dari 9,
   * tambahkan dua digit angka tersebut untuk mendapatkan angka yang <= 9.
   */
  public static int getDigit(int number) {
    String numberString = String.valueOf(number);
    if (number > 9) return (
      Integer.parseInt(numberString.substring(0, 1)) +
      Integer.parseInt(numberString.substring(1, 2))
    ); else return number;
  }

  /**
   * Dapatkan hasil dari langkah (c)
   * (harus rekursif), boleh menggunakan helper method
   */
  public static int jumlahPosisiGenap(long kode) {
    String kodeString = String.valueOf(kode);
    kodeString = kodeString.substring(1);
    if (kodeString.length() == 2) return Integer.parseInt(
      kodeString.substring(0, 1)
    ); else {
      return (
        Integer.parseInt(kodeString.substring(0, 1)) +
        jumlahPosisiGenap(Long.parseLong(kodeString.substring(1)))
      );
    }
  }

  /** Return true jika kode merupakan prefix yang valid */
  public static boolean cekPrefix(long kode) {
    String kodeString = String.valueOf(kode);

    if (
      kodeString.subSequence(0, 1).equals("2") ||
      kodeString.substring(0, 2).equals("18")
    ) return true; else return false;
  }

  /** Return panjang kode */
  public static int getPanjangKode(long kode) {
    String kodeString = String.valueOf(kode);
    return kodeString.length();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    while (n-- > 0) {
      long kode = sc.nextLong();
      System.out.println(isValid(kode) ? "YES" : "NO");
    }
    sc.close();
  }
}
