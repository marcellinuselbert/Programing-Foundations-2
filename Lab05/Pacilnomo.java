import java.util.Scanner;

public class Pacilnomo {
	private static Aset[] portofolio;
	private static double earnings;

	private static void printSeparator() {
		System.out.println("=".repeat(64));
	}
	
	private static void daftarAset() {
		printSeparator();
		System.out.printf("Kamu memiliki %d total aset:\n", portofolio.length);
		for(Aset a : portofolio) {
			System.out.println("- " + a);
		}
		printSeparator();
	}

	private static void infoPortofolio() {
		int jumlahSaham = 0, jumlahObligasi = 0;
		double netWorth = 0, totalSaham=0, totalObligasi=0;
		for(Aset a : portofolio){
			// check a if type of saham then add jumlahSaham amount
			// then increment total with harga*jumlah
			if (a instanceof Saham){
				
				jumlahSaham++;
				
				totalSaham+= a.getHarga()*a.getJumlah();
			}
			// else if its obligasi then add jumlahObligasi amount
			// then increment total with harga*jumlah
			else if(a instanceof Obligasi){
				
				jumlahObligasi++;
				
				totalObligasi+=a.getHarga()*a.getJumlah();
			}
			
		}
		// add networth with total saham + obligasi + earning
		netWorth+= totalSaham+totalObligasi+earnings;

		printSeparator();
		System.out.printf("""
		Info Portofolio
		Jumlah Jenis Saham: %d
		Jumlah Jenis Obligasi: %d
		Total Nilai Portofolio: %.2f
		""", jumlahSaham, jumlahObligasi, netWorth);
		printSeparator();
	}

	private static void nextYear() {
		// for every object in portofolio list, call object.nextYear() 
		for (Aset a : portofolio){
			a.nextYear();
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Silakan masukkan banyak aset yang tersedia: ");
		int banyakAset = Integer.parseInt(in.nextLine());
		
		portofolio = new Aset[banyakAset];
		
		for(int i = 0; i < banyakAset; i++) {
			System.out.printf("Aset %d: ", i + 1);
			String inp[] = in.nextLine().split("\\s+");
			String namaAset = inp[0], jenisAset = inp[1]; 
			int jumlah = Integer.valueOf(inp[2]); 
			double harga = Double.valueOf(inp[3]);
			// if saham then get the pertumbuhan in fourth index and divident in fifth index
			if(jenisAset.equals("SAHAM")) {
				double pertumbuhan = Double.valueOf(inp[4]); 
				double dividen = Double.valueOf(inp[5]);
				// create new object saham
				portofolio[i] = new Saham(namaAset, jumlah, harga, pertumbuhan, dividen);
			// if its obligasi then get the bunga in fourth index and maturitas in fifth index1
			} else if(jenisAset.equals("OBLIGASI")) {
				double bunga = Double.valueOf(inp[4]); 
				int maturitas= Integer.valueOf(inp[5]);
				// create new object obligasi 
				portofolio[i] = new Obligasi(namaAset, jumlah, harga, bunga, maturitas);
			} 
		}

		System.out.print("Selamat datang di...");
		System.out.print(""" 


							 /$$$$$$$                     /$$ /$$                                            
							| $$__  $$                   |__/| $$                                            
							| $$  \\ $$ /$$$$$$   /$$$$$$$ /$$| $$ /$$$$$$$   /$$$$$$  /$$$$$$/$$$$   /$$$$$$ 
							| $$$$$$$/|____  $$ /$$_____/| $$| $$| $$__  $$ /$$__  $$| $$_  $$_  $$ /$$__  $$
							| $$____/  /$$$$$$$| $$      | $$| $$| $$  \\ $$| $$  \\ $$| $$ \\ $$ \\ $$| $$  \\ $$
							| $$      /$$__  $$| $$      | $$| $$| $$  | $$| $$  | $$| $$ | $$ | $$| $$  | $$
							| $$     |  $$$$$$$|  $$$$$$$| $$| $$| $$  | $$|  $$$$$$/| $$ | $$ | $$|  $$$$$$/
							|__/      \\_______/ \\_______/|__/|__/|__/  |__/ \\______/ |__/ |__/ |__/ \\______/ 
																											
                                                                                 
                                                                                 """);
		
		while(true) {
			System.out.printf("""
				Silakan pilih salah satu opsi berikut:
				[1] Daftar aset
				[2] Info portofolio
				[3] Lanjut ke tahun berikutnya
				[*] Keluar\n""", earnings);
			printSeparator();
			System.out.print("Input: ");
			String pilihan = in.nextLine();
			if(pilihan.equals("1")) {
				daftarAset();
			} else if(pilihan.equals("2")) {
				infoPortofolio();
			} else if(pilihan.equals("3")) {
				nextYear();
				System.out.println("Setahun telah berlalu...");
				printSeparator();
			} else {
				System.out.println("Terima kasih telah menggunakan layanan Pacilnomo ~ !");
				break;
			}
		}
		
		in.close();
	}
	
	public static void addToEarnings(double jumlah) {
		earnings += jumlah;
	}
}
