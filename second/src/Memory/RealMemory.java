package Memory;

public class RealMemory {
  public MemoryBlock[] memory;
  public static int MAX_MEMORY_BLOCKS = 16;

  public RealMemory() {
    memory = new MemoryBlock[MAX_MEMORY_BLOCKS];
    for (int i = 0; i < MAX_MEMORY_BLOCKS; i++) {
      memory[i] = new MemoryBlock();
    }
  }

  public MemoryBlock getBlock(int index) {
    return memory[index];
  }

  public int getMaxMemoryBlocks() {
    return MAX_MEMORY_BLOCKS;
  }

  public void setBlock(int index, MemoryBlock memoryBlock) {
    memory[index] = memoryBlock;
  }
}