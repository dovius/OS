import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by dovydas on 17.3.8.
 */
public class ExternalMemory {
  private static final int SECTORS = 1000;
  private static final int WORDS_PER_SECTOR = 32;
  private static final String EMPTY_SECTOR = "                                ";
  public static ArrayList<Integer> usedSectors = new ArrayList<>();
  private static RandomAccessFile file;

  public ExternalMemory() {
    try {
      file = new RandomAccessFile("HDD", "rw");
    } catch (FileNotFoundException e) {
      System.out.println("Error creating HDD");
      e.printStackTrace();
    }
    try {
      for (int i = 0; i < 100; ++i) {
        file.seek(i * WORDS_PER_SECTOR * 2);
        file.writeChars(EMPTY_SECTOR);
      }
    } catch (IOException e) {
      System.out.println("Error initializing HDD");
      e.printStackTrace();
    }
  }

  public static void write(char[] data, int sector) {
    if (sector < 0 || sector > SECTORS) {
      throw new IllegalArgumentException("Incorrect sector");
    }
    try {
      file.seek(sector * WORDS_PER_SECTOR * 2);
      file.writeChars(new String(data));
      usedSectors.add(sector);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
