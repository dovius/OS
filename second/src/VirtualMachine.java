public class VirtualMachine {

  protected char[][] memory = new char[1][16];
 // private Memory memory;

  VirtualMachine() {
  }
  // Sudeda R1 ir R2, įrašoma į R1. Jeigu rezultatas netelpa, OF = 1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
  public void ADD() {

    if (memory[0][PhysicalMachine.sp] + memory[0][PhysicalMachine.sp-2] > Integer.MAX_VALUE) {
      setOF();
      return;
    } else {
      RM.R1 += RM.R2;
    }
    if (((RM.R1 >> 6) & 1) == 1) {
      setSF();
    }
    ++IC;
  }
}