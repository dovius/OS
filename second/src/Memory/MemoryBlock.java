package Memory;

public class MemoryBlock {
  public static int BLOCK_SIZE = 16;
  private Word[] data;
  public boolean free;

  public MemoryBlock() {
    data = new Word[BLOCK_SIZE];
    for (int i = 0; i < data.length; i++)
      data[i] = new Word();
    setFree(true);
  }

  public Word getWord(int index) {
    return data[index];
  }

  public void setWord(int index, Word value) {
    data[index] = value;
  }

  public void push(String data, int sp) {
    getWord(sp).setValue(data);
  }

  public String pop(int sp) {
    getWord(sp+1).setValue("0000");
    return getWord(sp).getValue();
  }

  public void put(String data, int offset) {
    getWord(offset).setValue(data);
  }

  public String get(int offset) {
    return getWord(offset).getValue();
  }

  public void pushData(int offset, String data) {
    this.data[offset].setValue(data);
  }

  public void setFree(boolean free) {
    this.free = free;
  }

  public boolean isEmpty() {
    return free;
  }
}