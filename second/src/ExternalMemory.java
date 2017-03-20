import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by dovydas on 17.3.8.
 */
public class ExternalMemory {
  private static final int DISK_SIZE_BYTES = 1024;
  private static final String EMPTY_CELL_CHARACTER = " ";
  private static RandomAccessFile file;

  public ExternalMemory() {
    try {
      file = new RandomAccessFile("HDD", "rw");
    } catch (FileNotFoundException e) {
      System.out.println("Error creating HDD");
      e.printStackTrace();
    }
    try {
      for (int i = 0; i < DISK_SIZE_BYTES / 2; ++i) {
        file.seek(i * 2);
        file.writeChars(EMPTY_CELL_CHARACTER);
      }
    } catch (IOException e) {
      System.out.println("Error initializing HDD");
      e.printStackTrace();
    }
  }

  public static void write(char[] data, int offset) {
    if (offset < 0 || offset > DISK_SIZE_BYTES) {
      throw new IllegalArgumentException("Incorrect sector");
    }
    try {
      file.seek(offset);
      file.writeChars(new String(data));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static String read(int size, int offset) {
    String dataRead = new String();
    if (offset < 0 || offset > DISK_SIZE_BYTES) {
      throw new IllegalArgumentException("Incorrect sector");
    }
    try {
      file.seek(offset);
      for (int i = 0; i < size; i++) {
        dataRead += file.readChar();
      }
      file.read();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return dataRead;
  }

}
