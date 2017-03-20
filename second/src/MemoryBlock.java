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

  // TODO
  public void push(Word word){

  }
}