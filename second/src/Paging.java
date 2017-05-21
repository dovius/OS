import Memory.MemoryBlock;
import Memory.VirtualMemory;
import Memory.Word;

/**
 * Created by dovydas on 17.5.20.
 */
public class Paging {
  public MemoryBlock getPageTable(MemoryBlock[] realMemory) {
    MemoryBlock pageTable = new MemoryBlock();
    pageTable.free = false;
    int blocksUsed = 0;
    for (int i = 0; i < PhysicalMachine.MAX_REAL_MEMORY_BLOCKS && blocksUsed < VirtualMemory.MAX_VIRTUAL_MEMORY_BLOCKS; i++) {
      if (realMemory[i].isEmpty()) {
        pageTable.setWord(blocksUsed++, new Word(i));
        realMemory[i].free = false;
      }
    }
    if (blocksUsed != VirtualMemory.MAX_VIRTUAL_MEMORY_BLOCKS) {
      return null;
    }
    return pageTable;
  }

  public int getFreeBlock(MemoryBlock[] realMemory) {
    for (int i = 0; i < PhysicalMachine.MAX_REAL_MEMORY_BLOCKS; i++) {
      if (realMemory[i].isEmpty()) {
        realMemory[i].free = false;
        return i;
      }
    }
    return -1;
  }

}
