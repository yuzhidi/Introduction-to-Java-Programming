import javax.swing.*;

public class DisplayBST extends JApplet {
  public DisplayBST() {
    add(new TreeControl(new BST<Integer>()));
  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("DisplayBST");
    JApplet applet = new DisplayBST();
    frame.add(applet);
    frame.setSize(500, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}