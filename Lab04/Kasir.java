import java.io.*;
import java.util.*;

public class Kasir {

  private static InputReader in = new InputReader(System.in);
  private static PrintWriter out = new PrintWriter(System.out);
  // Gunakan out sebagai pengganti System.out
  // out ini akan menahan output sampai dia di-(close/flush)
  // Contoh jika ingin print("merah"), maka tulis out.print("merah")

  static Barang[] barang;
  static Pelanggan[] pelanggan;
  static int N, M;
  int pointer = 0;

  static Pelanggan cariPelanggan(String nama) {
    for (Pelanggan p : pelanggan) {
      if (nama.equals(p.getNama())) {
        return p;
      }
    }
    return null;
  }

  static Barang cariBarang(String namaBarang) {
    for (Barang b : barang) {
      if (namaBarang.equals(b.getNama())) {
        return b;
      }
    }
    return null;
  }

  static void kasir(Pelanggan K) {
    // If keranjang first index is null means no item added
    int counterNull = 0;
    for (int i = 0; i < K.getKeranjang().length; i++) {
      if (K.getKeranjang()[i] == null) {
        counterNull++;
      }
    }
    // if null counter is equal to keranjang items amount then its empty keranjang
    if (counterNull == K.getKeranjang().length) {
      out.println("Maaf tidak ada barang di keranjang " + K.getNama());
    } else {
      // if not empy then check this next statement
      // if uang less than harga then dont have enough money to pay
      if (K.getUang() < K.totalHargaBarang()) {
        out.println("Maaf " + K.getNama() + " tidak memiliki cukup uang");
      } // else user can pay
      else {
        out.println("Pembelian " + K.getNama() + " berhasil:");
        // for every item in keranjang then print barang name amount and amount multiplied with barang cost
        for (int i = 0; i < K.getKeranjang().length; i++) {
          if (K.getKeranjang()[i] != null) {
            out.println(
              "* " +
              K.getKeranjang()[i].getBarang().getNama() +
              " " +
              K.getKeranjang()[i].getBanyakBarang() +
              " = " +
              (
                K.getKeranjang()[i].getBanyakBarang() *
                K.getKeranjang()[i].getBarang().getHarga()
              )
            );
          }
        }
        // print total harga
        out.println("* Total Belanjaan = " + K.totalHargaBarang());
        // set uang = uang - cost
        K.setUang(K.getUang() - K.totalHargaBarang());
        out.println("* Sisa Uang = " + K.getUang());
        // reset order
        K.resetKeranjang();
      }
    }
  }

  public static void main(String[] args) {
    N = in.nextInt();
    barang = new Barang[N];
    for (int i = 0; i < N; i++) {
      String namaBarang = in.next();
      int hargaBarang = in.nextInt();
      int beratBarang = in.nextInt();
      int stock = in.nextInt();
      // created list of barang-barang
      // fill the list of barang-barang with the input
      barang[i] = new Barang(namaBarang, hargaBarang, beratBarang, stock);
    }

    M = in.nextInt();
    pelanggan = new Pelanggan[M];
    for (int j = 0; j < M; j++) {
      String namaPelanggan = in.next();
      int uang = in.nextInt();
      // created list of members
      // fill the list of members with the input
      pelanggan[j] = new Pelanggan(namaPelanggan, uang, N);
    }

    int P = in.nextInt();
    for (int k = 0; k < P; k++) {
      String command = in.next();

      if (command.equals("ADD")) {
        String namaPelanggan = in.next();
        String namaBarang = in.next();
        int banyakBarangDiambil = in.nextInt();

        Pelanggan plg = cariPelanggan(namaPelanggan);
        out.println(plg.addBarang(cariBarang(namaBarang), banyakBarangDiambil));
      }

      if (command.equals("TOTAL_HARGA")) {
        String namaPelanggan = in.next();
        Pelanggan plg = cariPelanggan(namaPelanggan);
        out.printf(
          "Total harga belanjaan %s adalah %d%n",
          plg.getNama(),
          plg.totalHargaBarang()
        );
      }

      if (command.equals("KASIR")) {
        String namaPelanggan = in.next();
        Pelanggan plg = cariPelanggan(namaPelanggan);
        kasir(plg);
      }

      if (command.equals("CEK_UANG")) {
        String namaPelanggan = in.next();
        Pelanggan plg = cariPelanggan(namaPelanggan);
        out.println("Uang " + plg.getNama() + " sekarang " + plg.cekUang());
      }
    }

    // don't forget to close/flush the output
    out.close();
  }

  // taken from https://codeforces.com/submissions/Petr
  // together with PrintWriter, these input-output (IO) is much faster than the
  // usual Scanner(System.in) and System.out
  static class InputReader {

    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream), 32768);
      tokenizer = null;
    }

    public String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }
  }
}
