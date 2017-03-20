/**
 * Created by dovydas on 17.3.20.
 */
public class VirtualMemory {
  private MemoryBlock[] memory;
  private final int MAX_MEMORY_BLOCKS;

  VirtualMemory(int blocks) {
    MAX_MEMORY_BLOCKS = blocks;
    memory = new MemoryBlock[blocks];
    for (int i = 0; i < blocks; i++)
      memory[i] = new MemoryBlock(16);
  }

  public MemoryBlock getBlock(int index) {
    return memory[index];
  }

  public int getMaxMemoryBlocks() {
    return MAX_MEMORY_BLOCKS;
  }
}
