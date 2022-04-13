import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

class RumahSakit {

  private static InputReader in;
  private static PrintWriter out;
  Warga[] daftarWarga = new Warga[0];

  private static void handleLog(Warga X) {
    if (X instanceof Pasien) { // Jika pasien
      out.println(((Pasien) X).getHappiness()); // cast to pasien then get happiness and print
      out.println(((Pasien) X).getStatusSembuh()); //  cast to pasien then get status and print
    } else { // Jika dokter
      out.println(((Dokter) X).getJumlahPasienDitemui()); //  cast to dokter then get patient amount
    }

    for (int i = 0; i < X.getLogInteraksi().length; i++) {
      out.println(X.getLogInteraksi()[i]);
    }
    out.println("------------");
  }

  private static void handleInteraksi(Warga X, Warga Y) {
    X.berinteraksi(Y);
    Y.berinteraksi(X);
  }

  //method untuk menambahkan daftar warga
  private void masukkanKeDaftarWarga(Warga objWarga) {
    Warga[] newDaftarWarga = new Warga[this.daftarWarga.length + 1];

    for (int i = 0; i < this.daftarWarga.length; i++) {
      newDaftarWarga[i] = this.daftarWarga[i];
    }
    this.daftarWarga = newDaftarWarga;

    newDaftarWarga[this.daftarWarga.length - 1] = objWarga;
  }

  //method untuk mendapat objek warga berdasarkan nama
  private Warga getWarga(String nama) {
    for (Warga warga : this.daftarWarga) {
      if (warga.getNama().equalsIgnoreCase(nama)) {
        return warga;
      }
    }
    return null;
  }

  private void mainProgram() {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    int N;

    N = in.nextInt();
    for (int tmp = 0; tmp < N; tmp++) {
      String event = in.next();

      if (event.equals("ADD")) {
        String roleWarga = in.next();
        String nama = in.next();
        if (roleWarga.equals("DOKTER")) {
          String penyakitKeahlian = in.next();
          boolean dokterRamah = in.next().equals("Yes") ? true : false;
          // create new doctor and add to daftar warga
          Dokter doctor = new Dokter(nama, penyakitKeahlian, dokterRamah);
          masukkanKeDaftarWarga(doctor);
        } else {
          String penyakit = in.next();
          // create patient and add to daftar warga
          Pasien pasien = new Pasien(nama, penyakit);
          masukkanKeDaftarWarga(pasien);
        }
      } else if (event.equals("INTERAKSI")) {
        String X = in.next();
        String Y = in.next();
        handleInteraksi(getWarga(X), getWarga(Y));
      } else {
        String X = in.next();
        handleLog(getWarga(X));
      }
    }

    out.flush();
  }

  public static void main(String[] args) throws IOException {
    RumahSakit rs = new RumahSakit();
    rs.mainProgram();
  }

  // taken from https://codeforces.com/submissions/Petr
  // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
  // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
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
