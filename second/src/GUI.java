import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dovydas on 17.3.14.
 */
public class GUI {
  private boolean isLoaded;
  private boolean isRunning;
  private boolean step;
  private String buffer;
  private JTextField input;
  private JTextArea output;
  private JFrame window;
  private JPanel mainPanel;
  private JTextField[] registers;
  private JCheckBox mode;
  private JButton load;

  public GUI() {
    this.isLoaded = false;
    this.isRunning = false;
    this.step = false;
    this.registers = new JTextField[Constant.REGISTERS.length];
    (this.window = new JFrame()).setBounds(Constant.MAIN_WINDOW);
    this.window.setDefaultCloseOperation(3);
    this.window.setResizable(false);
    (this.mainPanel = new JPanel()).setVisible(true);
    this.mainPanel.setLayout(null);
    JLabel label = new JLabel();
    label.setText("Memory");
    label.setVisible(true);
    label.setBounds(Constant.MEMORY_LABEL);
    label.setVerticalAlignment(0);
    label.setHorizontalAlignment(0);
    final Table myTable = new Table();
    final JTable table = new JTable(myTable);
    table.setRowSelectionAllowed(false);
    table.setVisible(true);
    table.getTableHeader().setReorderingAllowed(false);
    table.setDefaultRenderer(myTable.getColumnClass(0), new CustomRenderer());
    JScrollPane scrollPane = new JScrollPane(table);
    table.setFillsViewportHeight(true);
    scrollPane.setBounds(Constant.MEMORY_BOUNDS);
    this.mainPanel.add(label);
    this.mainPanel.add(scrollPane);
    for (int i = 0; i < Constant.REGISTERS.length; ++i) {
      label = new JLabel();
      label.setText(Constant.REGISTERS[i]);
      label.setFont(label.getFont().deriveFont(1, 14.0f));
      label.setBounds(Constant.registerNameBounds());
      label.setHorizontalAlignment(4);
      label.setVisible(true);
      this.mainPanel.add(label);
    }
    for (int j = 0; j < Constant.REGISTERS.length; ++j) {
      final JTextField register = new JTextField();
      register.setBounds(Constant.registerValueBounds());
      register.setVisible(true);
      register.setEditable(false);
      register.setBackground(Color.WHITE);
      this.mainPanel.add(register);
      this.registers[j] = register;
    }
    this.seedRegisters();
    (this.mode = new JCheckBox()).setBounds(Constant.MODE);
    this.mode.setText("Step-mode");
    this.mode.setFocusable(false);
    this.mainPanel.add(this.mode);
    JButton button = new JButton();
    button.setBounds(Constant.START);
    button.setText("START");
    button.setFocusable(false);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        if (!GUI.this.isRunning & GUI.this.isLoaded) {
          GUI.this.isRunning = true;
        }
      }
    });
    this.mainPanel.add(button);
    button = new JButton();
    button.setBounds(Constant.NEXT);
    button.setText("NEXT");
    button.setFocusable(false);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        GUI.this.step = true;
      }
    });
    this.mainPanel.add(button);
    (this.output = new JTextArea()).setEditable(true);
    this.output.setBounds(0, 0, Constant.OUTPUT_WINDOW.width, Constant.OUTPUT_WINDOW.height);
    this.output.setBackground(Color.WHITE);
    this.output.setWrapStyleWord(true);
    this.output.setLineWrap(true);
    this.output.setVisible(true);
    this.output.insert("wuhu", 0);
    scrollPane = new JScrollPane(this.output);
    scrollPane.setVisible(true);
    scrollPane.setBounds(Constant.OUTPUT_WINDOW);
    scrollPane.setOpaque(true);
    this.mainPanel.add(scrollPane);
    label = new JLabel();
    label.setBounds(Constant.OUTPUT_LABEL);
    label.setText("Output");
    label.setVisible(true);
    label.setHorizontalAlignment(0);
    this.mainPanel.add(label);
    (this.input = new JTextField()).setEnabled(true);
    this.input.setBounds(Constant.INPUT_WINDOW);
    this.input.setVisible(true);
    this.mainPanel.add(this.input);
    button = new JButton();
    button.setBounds(Constant.INPUT);
    button.setVisible(true);
    button.setText("INPUT");
    button.setFocusable(false);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        GUI.this.buffer = GUI.this.input.getText();
        GUI.this.input.setEnabled(true);
        GUI.this.input.setText("insert");
      }
    });
    this.mainPanel.add(button);
    (this.load = new JButton()).setBounds(Constant.LOAD);
    this.load.setVisible(true);
    this.load.setText("LOAD");
    this.load.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        GUI.this.load.setEnabled(false);
        GUI.this.isLoaded = true;
      }
    });
    this.mainPanel.add(this.load);
    this.window.setContentPane(this.mainPanel);
    this.mainPanel.repaint();
    this.window.setVisible(true);
    this.window.repaint();
  }
}
