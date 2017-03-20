import java.util.ArrayList;

public class VirtualMachine {

  private static VirtualMemory memory;
   // private Memory memory;

  VirtualMachine() {
    memory = new VirtualMemory(2);
  }

  public void fillMemory() {
    String program = ExternalMemory.read(PhysicalMachine.programs.get(0), 0);
    String[] statements = program.split(";");
    int memoryPointer = 0;
    memory.getBlock(0).setWord(++memoryPointer, );
  }

  //  Jeigu rezultatas netelpa, OF = 1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
  public static void ADD() {
//    if (memory[0][PhysicalMachine.sp] + memory[0][PhysicalMachine.sp-2] > Integer.MAX_VALUE) {
//      PhysicalMachine.setOF();
//      return;
//    } else {
//      memory[0][PhysicalMachine.sp] += memory[0][PhysicalMachine.sp-2];
//    }
//    if (((memory[0][PhysicalMachine.sp] >> 6) & 1) == 1) {
//      PhysicalMachine.setSF();
//    }
//    ++PhysicalMachine.pc;
  }

  public static void SUB() {
//    if (memory[0][PhysicalMachine.sp] - memory[0][PhysicalMachine.sp-2] < Integer.MIN_VALUE) {
//      PhysicalMachine.setOF();
//      return;
//    } else {
//      memory[0][PhysicalMachine.sp] -= memory[0][PhysicalMachine.sp-2];
//    }
//    if (((memory[0][PhysicalMachine.sp] >> 6) & 1) == 1) {
//      PhysicalMachine.setSF();
//    }
//    ++PhysicalMachine.pc;
  }
  public static void MUL() {
//    if (memory[0][PhysicalMachine.sp] * memory[0][PhysicalMachine.sp-2] > Integer.MAX_VALUE) {
//      PhysicalMachine.setOF();
//      return;
//    } else {
//      memory[0][PhysicalMachine.sp] *= memory[0][PhysicalMachine.sp-2];
//    }
//    if (((memory[0][PhysicalMachine.sp] >> 6) & 1) == 1) {
//      PhysicalMachine.setSF();
//    }
//    ++PhysicalMachine.pc;
  }

  // Padalina R1 iš R2, įrašoma į R1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
  public static void DIV() {
//    memory[0][PhysicalMachine.sp] /= memory[0][PhysicalMachine.sp-2];
//    if (((memory[0][PhysicalMachine.sp] >> 6) & 1) == 1) {
//      PhysicalMachine.setSF();
//    }
//    ++PhysicalMachine.pc;
  }

  //Ši komanda palygina registre R1 ir R2 ęsančias reikšmes. Jeigu reikšmės lygios, ZF = 1, priešingu atveju ZF = 0.
  public static void CMP() {
//    if (memory[0][PhysicalMachine.sp] == memory[0][PhysicalMachine.sp-2]) {
//      PhysicalMachine.setZF();
//    } else {
//      PhysicalMachine.clearZF();
//    }
//    ++PhysicalMachine.pc;
  }
  //TODO VISI JUMPAI NUŠOKA DUOTU ADRESU
  //JMx1x2 - besąlyginio valdymo perdavimo komanda. Ji reiškia, kad valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16 * x1 + x2
  public static void JM(String address) {
    ++PhysicalMachine.pc;
  }

  //JEx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 1
  public static void JE(String address) {
    if (PhysicalMachine.getZF() == 1) {
      Integer.parseInt(address, 16);
    }
    ++PhysicalMachine.pc;
  }
  //JNx1x2 - valdymas turi būti perduotas kodo segmentui, nurodytam adresu 16*x1+x2, jeigu ZF = 0
  public static void JN(String address){
    if(PhysicalMachine.getZF() == 0) {
      Integer.parseInt(address, 16);
    }
    ++PhysicalMachine.pc;
  }
  //JAx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu CF = OF
  public static void JA(String address) {
    if (PhysicalMachine.getCF() == 0) {
      Integer.parseInt(address, 16);
    }
    ++PhysicalMachine.pc;
  }
  //JBx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu CF=1
  public static void JB(String address){
    if(PhysicalMachine.getCF() == 1){
      Integer.parseInt(address, 16);
    }
    ++PhysicalMachine.pc;
  }
  //JGx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 0 IR SF = OF
  public static void JG(String address) {
    if (PhysicalMachine.getZF() == 0 && PhysicalMachine.getSF() == PhysicalMachine.getOF()) {
      Integer.parseInt(address, 16);
    }
    ++PhysicalMachine.pc;
  }

  //JLx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu SF != OF
  public static void JL(String address) {
    if (PhysicalMachine.getSF() != PhysicalMachine.getOF()) {
      Integer.parseInt(address, 16);
    }
    ++PhysicalMachine.pc;
  }

  public static void PUSH(){
    PhysicalMachine.sp++;
    //TODO PUSH TO STACK PhysicalMachine.r
      ++PhysicalMachine.pc;
  }
  public static void POP(){
    //TODO POP FROM STACK TO PhysicalMachine.r
    PhysicalMachine.sp--;
    ++PhysicalMachine.pc;
  }
  public static void PRNL(){
    System.out.print('\n');
    ++PhysicalMachine.pc;
  }
  public static void GD(String x, String y){
      Integer.parseInt(x, 16);
      Integer.parseInt(y, 16);
      ++PhysicalMachine.pc;
  }
  public static void PD(String x, String y){
      Integer.parseInt(x, 16);
      Integer.parseInt(y, 16);
      ++PhysicalMachine.pc;
  }

}