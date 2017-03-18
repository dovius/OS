// 
// Decompiled by Procyon v0.5.30
// 

import java.awt.*;

public class Constant
{
    public static final int buttonHeight = 30;
    public static final int buttonWidth = 100;
    public static final Rectangle OUTPUT_WINDOW;
    public static final Rectangle MEMORY_BOUNDS;
    public static final Rectangle INPUT_WINDOW;
    public static final Rectangle MEMORY_LABEL;
    public static final Rectangle OUTPUT_LABEL;
    public static final Rectangle MAIN_WINDOW;
    public static final Rectangle INPUT;
    public static final Rectangle START;
    public static final Rectangle NEXT;
    public static final Rectangle MODE;
    public static final Rectangle LOAD;
    public static final Color ROW_FOREGROUND;
    public static final Color ROW_BACKGROUND;
    public static final int NEW_LINE = 1852730990;
    public static final String[] REGISTERS;
    private static final int ROWS_OF_REGISTERS = 8;
    private static final int Y = 425;
    private static final int VERTICAL_SPACE = 20;
    private static final Rectangle REGISTER_NAME_BOUNDS;
    private static int REGISTER_NAME_COUNT;
    private static final Rectangle REGISTER_VALUE_BOUNDS;
    private static int REGISTER_VALUE_COUNT;
    
    public static Rectangle registerNameBounds() {
        if (Constant.REGISTER_NAME_COUNT != 8) {
            final Rectangle register_NAME_BOUNDS = Constant.REGISTER_NAME_BOUNDS;
            register_NAME_BOUNDS.y += 20;
        }
        else {
            Constant.REGISTER_NAME_BOUNDS.y = 445;
            final Rectangle register_NAME_BOUNDS2 = Constant.REGISTER_NAME_BOUNDS;
            register_NAME_BOUNDS2.x += 150;
        }
        ++Constant.REGISTER_NAME_COUNT;
        return Constant.REGISTER_NAME_BOUNDS;
    }
    
    public static Rectangle registerValueBounds() {
        if (Constant.REGISTER_VALUE_COUNT != 8) {
            final Rectangle register_VALUE_BOUNDS = Constant.REGISTER_VALUE_BOUNDS;
            register_VALUE_BOUNDS.y += 20;
        }
        else {
            Constant.REGISTER_VALUE_BOUNDS.y = 445;
            final Rectangle register_VALUE_BOUNDS2 = Constant.REGISTER_VALUE_BOUNDS;
            register_VALUE_BOUNDS2.x += 150;
        }
        ++Constant.REGISTER_VALUE_COUNT;
        return Constant.REGISTER_VALUE_BOUNDS;
    }
    
    static {
        OUTPUT_WINDOW = new Rectangle(850, 30, 310, 200);
        MEMORY_BOUNDS = new Rectangle(20, 30, 800, 580);
        INPUT_WINDOW = new Rectangle(850, 250, 220, 20);
        MEMORY_LABEL = new Rectangle(Constant.MEMORY_BOUNDS.x, 0, Constant.MEMORY_BOUNDS.width, 30);
        OUTPUT_LABEL = new Rectangle(Constant.OUTPUT_WINDOW.x, 0, Constant.OUTPUT_WINDOW.width, 30);
        MAIN_WINDOW = new Rectangle(75, 40, 1200, 660);
        INPUT = new Rectangle(Constant.INPUT_WINDOW.x + Constant.INPUT_WINDOW.width + 10, Constant.INPUT_WINDOW.y, 79, Constant.INPUT_WINDOW.height);
        START = new Rectangle(Constant.INPUT_WINDOW.x, 300, 100, 30);
        NEXT = new Rectangle(1050, 400, 100, 30);
        MODE = new Rectangle(1050, 330, 100, 30);
        LOAD = new Rectangle(Constant.START.x, Constant.NEXT.y, 100, 30);
        ROW_FOREGROUND = Color.BLACK;
        ROW_BACKGROUND = Color.YELLOW;
        REGISTERS = new String[] { "AX", "BX", "CX", "DS", "CS", "SS", "IP", "SP", "PR", "SF", "CH1", "CH2", "CH3", "CH4", "MODE", "TIMER" };
        REGISTER_NAME_BOUNDS = new Rectangle(825, 425, 60, 20);
        Constant.REGISTER_NAME_COUNT = 0;
        REGISTER_VALUE_BOUNDS = new Rectangle(900, 425, 65, 20);
        Constant.REGISTER_VALUE_COUNT = 0;
    }
}
