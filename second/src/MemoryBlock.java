public class MemoryBlock {
  private Word[] data;
  public int memoryBlockSize;

  MemoryBlock(int blockSize) {
    data = new Word[blockSize];
    for (int i = 0; i < data.length; i++)
      data[i] = new Word();
  }

  public Word getWord(int index) {
    return data[index];
  }

  public void setWord(int index, Word value) {
    data[index] = value;
  }

  public int getBlockSize() {
    return memoryBlockSize;
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
}