import java.util.Scanner;

public class KalkulatorBMI {

  public static void main(String[] args) {
    // initialize scanner for input
    Scanner studentInput = new Scanner(System.in);
    Scanner calculationStandardInput = new Scanner(System.in);
    Scanner massInput = new Scanner(System.in);
    Scanner heightInput = new Scanner(System.in);
    // initialize variable
    double mass;
    double height;
    double resBMI = 0;
    int belowNormal = 0;
    int normal = 0;
    int aboveNormal = 0;
    int obesity = 0;
    System.out.println("Selamat datang di program kalkulator BMI!");
    System.out.println(
      "--------------------------------------------------------"
    );
    System.out.print("Masukkan jumlah mahasiswa yang akan dihitung datanya: ");
    // User input as integer
    int student = studentInput.nextInt();
    // loop as many as students amount
    for (int count = 1; count <= student; count++) {
      // string formatting, %d stands for integer
      System.out.printf(
        "--------------------DATA MAHASISWA %d--------------------\n",
        count
      );
      System.out.print("Standar pengukuran apakah yang digunakan? ");
      // String input
      String calculationStandard = calculationStandardInput.nextLine();
      // switch case, if METRIK then execute next METRIK statements, else if IMPERIAL then execute next IMPERIAL statements
      switch (calculationStandard) {
        case "METRIK":
          System.out.print("Masukkan massa tubuh mahasiswa (kilogram): ");
          // Double type input
          mass = massInput.nextDouble();
          System.out.print("Masukkan tinggi tubuh mahasiswa (sentimeter): ");
          height = heightInput.nextDouble();
          // Calculate BMI
          // Height is input in centimeter. We need to calculate in meter so mass * 10**4
          resBMI = mass * 10000 / (height * height);
          break;
        case "IMPERIAL":
          System.out.print("Masukkan massa tubuh mahasiswa (pon): ");
          mass = massInput.nextDouble();
          System.out.print("Masukkan tinggi tubuh mahasiswa (inci): ");

          height = heightInput.nextDouble();
          // Calculate BMI in IMPERIAL
          resBMI = mass * 703 / (height * height);
          break;
      }
      // if under 18.5 then add below normal amount
      // if between 18.5 and 24.9 then add normal amount
      // if between 25 and 29.9 then add above normal amount
      // if above 30 then add obesity amount
      if (resBMI < 18.5) belowNormal++; else if (
        resBMI >= 18.5 && resBMI < 25
      ) normal++; else if (
        resBMI >= 25 && resBMI < 30
      ) aboveNormal++; else obesity++;
    }
    System.out.printf(
      "Berikut merupakan ringkasan hasil pengukuran BMI dari %d mahasiswa \n",
      student
    );
    System.out.printf(
      "Jumlah mahasiswa dengan berat badan di bawah normal: %d \n",
      belowNormal
    );
    System.out.printf(
      "Jumlah mahasiswa dengan berat badan normal: %d \n",
      normal
    );
    System.out.printf(
      "Jumlah mahasiswa dengan berat badan di atas normal: %d \n",
      aboveNormal
    );
    System.out.printf("Jumlah mahasiswa obesitas: %d \n", obesity);
    System.out.println(
      "--------------------------------------------------------"
    );
    System.out.println(
      "Terima kasih telah menggunakan program kalkulator BMI!"
    );
    // Close input to prevent resource leak
    studentInput.close();
    calculationStandardInput.close();
    massInput.close();
    heightInput.close();
  }
}
