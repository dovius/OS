public class VirtualMachine {

  private char[][] memory = new char[1][16];
 // private Memory memory;

  VirtualMachine() {
  }
  //  Jeigu rezultatas netelpa, OF = 1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
  public void ADD() {
    if (memory[0][PhysicalMachine.sp] + memory[0][PhysicalMachine.sp-2] > Integer.MAX_VALUE) {
      PhysicalMachine.setOF();
      return;
    } else {
      memory[0][PhysicalMachine.sp] += memory[0][PhysicalMachine.sp-2];
    }
    if (((memory[0][PhysicalMachine.sp] >> 6) & 1) == 1) {
      PhysicalMachine.setSF();
    }
    ++PhysicalMachine.pc;
  }

  public void SUB() {
    if (memory[0][PhysicalMachine.sp] - memory[0][PhysicalMachine.sp-2] < Integer.MIN_VALUE) {
      PhysicalMachine.setOF();
      return;
    } else {
      memory[0][PhysicalMachine.sp] -= memory[0][PhysicalMachine.sp-2];
    }
    if (((memory[0][PhysicalMachine.sp] >> 6) & 1) == 1) {
      PhysicalMachine.setSF();
    }
    ++PhysicalMachine.pc;
  }
  public void MUL() {
    if (memory[0][PhysicalMachine.sp] * memory[0][PhysicalMachine.sp-2] > Integer.MAX_VALUE) {
      PhysicalMachine.setOF();
      return;
    } else {
      memory[0][PhysicalMachine.sp] *= memory[0][PhysicalMachine.sp-2];
    }
    if (((memory[0][PhysicalMachine.sp] >> 6) & 1) == 1) {
      PhysicalMachine.setSF();
    }
    ++PhysicalMachine.pc;
  }

  // Padalina R1 iš R2, įrašoma į R1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
  public void DIV() {
    memory[0][PhysicalMachine.sp] /= memory[0][PhysicalMachine.sp-2];
    if (((memory[0][PhysicalMachine.sp] >> 6) & 1) == 1) {
      PhysicalMachine.setSF();
    }
    ++PhysicalMachine.pc;
  }

  //Ši komanda palygina registre R1 ir R2 ęsančias reikšmes. Jeigu reikšmės lygios, ZF = 1, priešingu atveju ZF = 0.
  public void CMP() {
    if (memory[0][PhysicalMachine.sp] == memory[0][PhysicalMachine.sp-2]) {
      PhysicalMachine.setZF();
    } else {
      PhysicalMachine.clearZF();
    }
    ++PhysicalMachine.pc;
  }
  //JMx1x2 - besąlyginio valdymo perdavimo komanda. Ji reiškia, kad valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16 * x1 + x2
  public void JM(String address) {
    ++PhysicalMachine.pc;
  }

  //JEx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 1
  public void JE(String address) {
    if (PhysicalMachine.getZF() == 1) {
      //processCommands() tik paduot parametra, nuo kurios vietos vykdyt koda
    }
    ++PhysicalMachine.pc;
  }

  //JGx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 0 IR SF = OF
  public void JG(String address) {
    if (PhysicalMachine.getZF() == 0 && PhysicalMachine.getSF() == PhysicalMachine.getOF()) {

    }
    ++PhysicalMachine.pc;
  }

  //JLx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu SF != OF
  public void JL(String address) {
    if (PhysicalMachine.getSF() != PhysicalMachine.getOF()) {
      Integer.parseInt(address, 16);
    }
    ++PhysicalMachine.pc;
  }

  //JAx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu CF = OF
  public void JA(String address) {
    if (PhysicalMachine.getCF() == 0) {
      Integer.parseInt(address, 16);
    }
    ++PhysicalMachine.pc;
  }
}