import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class Test1 extends JFrame  {
  public static void main(String[] args) {
    // Create a frame and set its properties
    JFrame frame = new Test1();
    frame.setTitle("Mnemonic Demo");
    frame.setSize(200, 100);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public Test1() {
    ImageIcon usIcon = new ImageIcon("image/usIcon.gif");
    ImageIcon caIcon = new ImageIcon("image/caIcon.gif");
    ImageIcon ukIcon = new ImageIcon("image/ukIcon.gif");

    JButton jbt = new JButton("Click it");
    jbt.setPressedIcon(caIcon);
    jbt.setRolloverIcon(ukIcon);

    jbt.setFont(new Font("Courier", Font.PLAIN, 20));
    jbt.setMnemonic(KeyEvent.VK_T);
   
    add(jbt);
  }
}
