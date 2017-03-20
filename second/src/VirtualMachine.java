import java.io.IOException;
import java.util.ArrayList;

public class VirtualMachine {

 // private Memory memory;
  private static VirtualMemory memory;
  public static int sp;
  public static int pc;
   // private Memory memory;
    public static final int MAX_INT = 65535;
  VirtualMachine() {
    memory = new VirtualMemory(2);
    sp = 0;
    pc = 0;
  }
  String com;
while ((com = getCommand(pc)) != 'HALT'){
      PhysicalMachine.resolveCommand(com);
    }
  public void fillMemory() throws IOException {
    String program = ExternalMemory.read(PhysicalMachine.programs.get(0), 0);
    String[] statements = program.split(";");
    String status = "begin";
    for (String statement : statements) {
      if (status.equals("begin")) {
        if (statement.equals("#DATASEG")) {
          status = "data";
          continue;
        }
        else {
          throw new IOException();
        }
      }
      if (status.equals("data")) {
        if (statement.equals("#CODESEG")) {
          status = "code";
          continue;
        }
        memory.getBlock(0).push(statement);
      }
    }
  }

  //  Jeigu rezultatas netelpa, OF = 1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
  public static void ADD() {
      String a = memory.getBlock(0).pop();
      int a1 = Integer.parseInt(a, 16);

      String b = memory.getBlock(0).pop();
      int b1 = Integer.parseInt(b, 16);

    if (a1 + b1 > MAX_INT) {
      PhysicalMachine.setCF();
    }
    a1 += b1;
    if (((a1 >> 6) & 1) == 1) {
      PhysicalMachine.setSF();
    }
      memory.getBlock(0).push(String.valueOf(b1));
      memory.getBlock(0).push(String.valueOf(a1));
    ++PhysicalMachine.pc;
  }

  public static void SUB() {
      String a = memory.getBlock(0).pop();
      int a1 = Integer.parseInt(a, 16);

      String b = memory.getBlock(0).pop();
      int b1 = Integer.parseInt(b, 16);

    if (a1 - b1 < 0) {
      PhysicalMachine.setOF();
      return;
    } else {
      a1 -= b1;
    }
    if (((a1 >> 6) & 1) == 1) {
      PhysicalMachine.setSF();
    }
      memory.getBlock(0).push(String.valueOf(b1));
      memory.getBlock(0).push(String.valueOf(a1));
    ++PhysicalMachine.pc;
  }
  public static void MUL() {
      String a = memory.getBlock(0).pop();
      int a1 = Integer.parseInt(a, 16);

      String b = memory.getBlock(0).pop();
      int b1 = Integer.parseInt(b, 16);
    if (a1 * b1 > MAX_INT) {
      PhysicalMachine.setOF();
      return;
    } else {
      a1 *= b1;
    }
    if (((a1 >> 6) & 1) == 1) {
      PhysicalMachine.setSF();
    }
      memory.getBlock(0).push(String.valueOf(b1));
      memory.getBlock(0).push(String.valueOf(a1));
    ++PhysicalMachine.pc;
  }

  // Padalina R1 iš R2, įrašoma į R1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
  public static void DIV() {
      String a = memory.getBlock(0).pop();
      int a1 = Integer.parseInt(a, 16);

      String b = memory.getBlock(0).pop();
      int b1 = Integer.parseInt(b, 16);
    a1 /= b1;
    if (((a1 >> 6) & 1) == 1) {
      PhysicalMachine.setSF();
    }
      memory.getBlock(0).push(String.valueOf(b1));
      memory.getBlock(0).push(String.valueOf(a1));
    ++PhysicalMachine.pc;
  }

  //Ši komanda palygina registre R1 ir R2 ęsančias reikšmes. Jeigu reikšmės lygios, ZF = 1, priešingu atveju ZF = 0.
  public static void CMP() {
      String a = memory.getBlock(0).pop();
      int a1 = Integer.parseInt(a, 16);

      String b = memory.getBlock(0).pop();
      int b1 = Integer.parseInt(b, 16);

    if (a1 == b1) {
      PhysicalMachine.setZF();
    } else {
      PhysicalMachine.clearZF();
    }
      memory.getBlock(0).push(String.valueOf(b1));
      memory.getBlock(0).push(String.valueOf(a1));
    ++PhysicalMachine.pc;
  }
  //TODO VISI JUMPAI NUŠOKA DUOTU ADRESU
  //JMx1x2 - besąlyginio valdymo perdavimo komanda. Ji reiškia, kad valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16 * x1 + x2
  public static void JM(String address) {
     pc = Integer.parseInt(address, 16);

  }

  //JEx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 1
  public static void JE(String address) {
    if (PhysicalMachine.getZF() == 1) {
      pc = Integer.parseInt(address, 16);
    }

  }
  //JNx1x2 - valdymas turi būti perduotas kodo segmentui, nurodytam adresu 16*x1+x2, jeigu ZF = 0
  public static void JN(String address){
    if(PhysicalMachine.getZF() == 0) {
     pc = Integer.parseInt(address, 16);
    }

  }
  //JAx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu CF = OF
  public static void JA(String address) {
    if (PhysicalMachine.getCF() == 0) {
      pc = Integer.parseInt(address, 16);
    }
  }
  //JBx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu CF=1
  public static void JB(String address){
    if(PhysicalMachine.getCF() == 1){
      pc = Integer.parseInt(address, 16);
    }
  }
  //JGx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 0 IR SF = OF
  public static void JG(String address) {
    if (PhysicalMachine.getZF() == 0 && PhysicalMachine.getSF() == PhysicalMachine.getOF()) {
     pc = Integer.parseInt(address, 16);
    }
  }

  //JLx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu SF != OF
  public static void JL(String address) {
    if (PhysicalMachine.getSF() != PhysicalMachine.getOF()) {
      pc = Integer.parseInt(address, 16);
    }
  }

  public static void PUSH(){
      memory.getBlock(0).push(String.valueOf(PhysicalMachine.r));
      ++PhysicalMachine.pc;
  }
  public static void POP(){
     PhysicalMachine.r = Integer.parseInt(memory.getBlock(0).pop(), 16);
    ++PhysicalMachine.pc;
  }
  public static void PRNL(){
    System.out.print('\n');
    ++PhysicalMachine.pc;
  }
  //TODO SUTARKYT GD IR PD
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