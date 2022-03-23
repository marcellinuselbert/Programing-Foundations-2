import java.lang.StackWalker.Option;
import java.util.Arrays;
import java.util.Scanner;

public class Lab03 {

  static int pointer = 0;
  static String[][] playlist = new String[1][4];
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    // declare option
    int option;
    System.out.println("Selamat Datang di Pacilfy!");
    while (true) {
      // call add song method
      addSong();
      System.out.println("Lanjut Menambahkan lagu?\n[1] Lanjut\n[0] Berhenti");
      System.out.print("Perintah: ");
      // ask user input option
      option = Integer.parseInt(in.nextLine());
      // if option == 0 then just break
      // else if == 1 then enlarge the list
      // else 0 and 1 then invalid input!
      if (option == 0) {
        break;
      } else if (option == 1) {
        enlargeList();
      } else {
        System.out.println("Invalid input!");
        continue;
      }
    }

    System.out.println("Pacilfy siap dimulai");
    System.out.println("\nSELAMAT DATANG DI\n");
    System.out.println(" /$$$$$$$                     /$$ /$$  /$$$$$$");
    System.out.println("| $$__  $$                   |__/| $$ /$$__  $$");
    System.out.println(
      "| $$  \\ $$ /$$$$$$   /$$$$$$$ /$$| $$| $$  \\__//$$   /$$"
    );
    System.out.println(
      "| $$$$$$$/|____  $$ /$$_____/| $$| $$| $$$$   | $$  | $$"
    );
    System.out.println(
      "| $$____/  /$$$$$$$| $$      | $$| $$| $$_/   | $$  | $$"
    );
    System.out.println(
      "| $$      /$$__  $$| $$      | $$| $$| $$     | $$  | $$"
    );
    System.out.println(
      "| $$     |  $$$$$$$|  $$$$$$$| $$| $$| $$     |  $$$$$$$"
    );
    System.out.println(
      "|__/      \\_______/ \\_______/|__/|__/|__/      \\____  $$"
    );
    System.out.println(
      "                                               /$$  | $$"
    );
    System.out.println(
      "                                              |  $$$$$$/"
    );
    System.out.println(
      "                                               \\______/"
    );

    int command = 1;
    while (true) {
      // while True call display method
      display();
      System.out.print("Command (0 untuk exit) : ");
      command = Integer.parseInt(in.nextLine());
      // if command = 1 then prev music, if command = 2 add music, if 3 then check details, if 4 then delete current music, if 5 then next, if 0 stop the loop.
      // else 1,2,3,4,5,0 then print command salah
      if (command == 1) {
        prevMusic();
      } else if (command == 2) {
        addMusic();
      } else if (command == 3) {
        detailsMusic();
      } else if (command == 4) {
        deleteMusic();
      } else if (command == 5) {
        nextMusic();
      } else if (command == 0) {
        break;
      } else {
        System.out.println("Maaf, command yang anda masukan salah");
      }
    }

    System.out.println("Terima kasih sudah menggunakan Pacilfy!");
  }

  private static void nextMusic() {
    // check if pointer is on the last index? if yes set pointer to zero
    // else just increment the pointer
    if (pointer == (playlist.length - 1)) {
      pointer = 0;
    } else pointer++;
  }

  private static void deleteMusic() {
    // Source: https://www.softwaretestinghelp.com/remove-element-from-array-java/#:~:text=To%20remove%20an%20element%20from,ArrayList%20back%20to%20the%20array.
    // check if playlist length is not one? if not one then execute the following statements
    if (playlist.length != 1) {
      // create new array with decreased sized array
      String[][] tempPlaylist = new String[playlist.length - 1][];

      // copy elements from playlist array from beginning untill pointer into tempPlaylist
      System.arraycopy(playlist, 0, tempPlaylist, 0, pointer);

      // copy elements from playlist array from pointer+1 untill end into tempPlaylist
      System.arraycopy(
        playlist,
        pointer + 1,
        tempPlaylist,
        pointer,
        playlist.length - pointer - 1
      );
      // if pointer is in the last index then set pointer to zero
      // this pointer setting is to prevent out of range indexing
      // ex: array.length = 2, and pointer is on 1
      // new array length is 1 and pointer is still one
      // must be zero to prevenet index out of range
      if (pointer == (playlist.length - 1)) {
        pointer = 0;
      }
      // change playlist reference tempPlaylist reference
      playlist = tempPlaylist;
    } else {
      // if array.length is one then print next statement
      System.out.println("Minimal ada satu musik dalam sistem");
    }
  }

  private static void detailsMusic() {
    System.out.print("Judul yang ingin dicari: ");

    boolean isFound = false;
    String find_judul = in.nextLine();
    // check for every element in playlist
    for (int i = 0; i < playlist.length; i++) {
      // if first index of element is equal to user input and ignore case
      // then print the details
      String selected_song = playlist[i][0];
      // Source: https://www.w3schools.com/java/ref_string_equalsignorecase.asp#:~:text=The%20equalsIgnoreCase()%20method%20compares,strings%20lexicographically%2C%20ignoring%20case%20differences.
      if (selected_song.equalsIgnoreCase(find_judul)) {
        System.out.println("Data lagu: ");
        System.out.println("Judul : " + playlist[i][0]);
        System.out.println("Artist : " + playlist[i][1]);
        System.out.println("Album : " + playlist[i][2]);
        System.out.println("Tahun : " + playlist[i][3]);
        // if found then set true and break the loop
        isFound = true;
        break;
      }
    }
    // if not found then print not found
    if (!isFound) {
      System.out.println("Lagu tidak ditemukan");
    }
  }

  private static void prevMusic() {
    // if pointer is in the first index then set the pointer to the last index
    // else just decrement the pointer
    if (pointer == 0) {
      pointer = playlist.length - 1;
    } else pointer--;
  }

  private static void addMusic() {
    // to add music we need to enlarge the list first
    // then add the song
    enlargeList();
    addSong();
  }

  public static void addSong() {
    // ask input
    System.out.print("Judul : ");
    String judul = in.nextLine();
    System.out.print("Artist : ");
    String artist = in.nextLine();
    System.out.print("Album : ");
    String album = in.nextLine();
    System.out.print("Tahun : ");
    String year = in.nextLine();
    // set first index to judul
    // set second index to artist
    // set third index to album
    // set fourth index to year

    // playlist.length-1 because to add the new song to the last index of the array
    playlist[playlist.length - 1][0] = judul;
    playlist[playlist.length - 1][1] = artist;
    playlist[playlist.length - 1][2] = album;
    playlist[playlist.length - 1][3] = year;
  }

  public static void enlargeList() {
    // Source: https://stackoverflow.com/questions/8438879/expanding-an-array
    // set new temp playlist with enlarged size
    String[][] temp_playlist = new String[playlist.length + 1][4];
    // copy the entire playlist to temp_playlist
    System.arraycopy(playlist, 0, temp_playlist, 0, playlist.length);
    // set new reference for playlist
    playlist = temp_playlist;
  }

  private static void display() {
    System.out.println();
    System.out.println("Currently Playing");

    String displayedMusic =
      " " + playlist[pointer][1] + " - " + playlist[pointer][0] + " ";
    String command =
      "|[1] prev |[2] add music |[3] details |[4] delete music |[5] next|";

    if (displayedMusic.length() < command.length()) {
      int width = 62;
      String s = displayedMusic;

      int padSize = width - s.length();
      int padStart = s.length() + padSize / 2;

      s = String.format("%" + padStart + "s", s);
      s = String.format("%-" + width + "s", s);

      System.out.println(new String(new char[66]).replace("\0", "="));
      System.out.println("= " + s + " =");
      System.out.println(new String(new char[66]).replace("\0", "="));
      System.out.println(command);

      return;
    }

    System.out.println(
      "=" +
      new String(new char[displayedMusic.length()]).replace("\0", "=") +
      "="
    );
    System.out.println("=" + displayedMusic + "=");
    System.out.println(
      "=" +
      new String(new char[displayedMusic.length()]).replace("\0", "=") +
      "="
    );
    System.out.println(command);
  }
}
