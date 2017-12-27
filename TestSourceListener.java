import javax.swing.*;
import java.awt.event.*;

public class TestSourceListener {
  public static void main(String[] args) {
    JFrame frame = new JFrame("TestSourceListener");
    // Create a source object
    JButton jbt = new JButton("OK");
    frame.add(jbt);
    frame.setSize(200, 200);
    frame.setVisible(true);

    // Create a listener
    MyListener listener = new MyListener();

    // Register a listener
    jbt.addActionListener(listener);
  }
}

/** MyListener class */
class MyListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("I will process it!");
  }
}
