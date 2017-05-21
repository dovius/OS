package Memory;

/**
 * Created by dovydas on 17.3.20.
 */
public class VirtualMemory {
  private MemoryBlock[] memory;
  public static final int MAX_VIRTUAL_MEMORY_BLOCKS = 8;
  public int ptr;
  public RealMemory realMemory;
/*
   code segment blocks 0 - 2
   data segment 3 - 5
   stack segment 6 - 8
*/
  private int[] segments = {0, 3, 6, 9};
  // code, data, stack
  private int[] currentBlock = {0, 3, 6};
  // code, data, stack
  private int[] currentOffset = {0, 0, 0};

  public VirtualMemory(int prt) {
    this.ptr=prt;
    memory = new MemoryBlock[MAX_VIRTUAL_MEMORY_BLOCKS];
    for (int i = 0; i < MAX_VIRTUAL_MEMORY_BLOCKS; i++)
      memory[i] = new MemoryBlock();
  }

  public void syncMemory() {
//    MemoryBlock pageTable = realMemory.getBlock(ptr);
//    for (int i = 0; i < MAX_MEMORY_BLOCKS; i++) {
//      realMemory.setBlock(pageTable.getWord(i).getIntValue(), memory[i]);
//    }
  }

  public MemoryBlock getBlock(int index) {
    return memory[index];
  }

  public void pushCode(String statement) {
    if (currentOffset[0] > 15) {
      currentBlock[0]++;
      if (currentBlock[0] == segments[1]) {
        System.out.println("ERROR - segment full");
      }
      else {
        currentBlock[0]++;
      }
    }
    getBlock(currentBlock[0]).pushData(currentOffset[0], statement);
    currentOffset[0]++;
    syncMemory();
  }

  public void pushData(String statement) {
    if (currentOffset[1] > 15) {
      currentBlock[1]++;
      if (currentBlock[1] == segments[2]) {
        System.out.println("ERROR - segment full");
      }
      else {
        currentBlock[1]++;
      }
    }
    getBlock(currentBlock[1]).pushData(currentOffset[1], statement);
    currentOffset[1]++;
    syncMemory();
  }

  public void pushData(String data, int pointer) {
    getBlock(segments[1] + pointer / 16).pushData(pointer % 16, data);
    syncMemory();
  }

  public void push(String data, int pointer) {
    getBlock(segments[2] + pointer / 16).pushData(pointer % 16, data);
    syncMemory();
  }

  public String getCode(int pointer) {
    return getBlock(pointer / 16).get(pointer % 16);
  }

  public String getData(int pointer) {
    return getBlock(segments[1] + pointer / 16).get(pointer % 16);
  }

  public String pop(int pointer) {
    pointer--;
    String data = getBlock(segments[2] + pointer / 16).get(pointer % 16);
    getBlock(segments[2] + pointer / 16).pushData(pointer % 16, "0000");
    syncMemory();
    return data;
  }
}
