import java.io.*;
import javax.swing.*;

public class SignedAppletDemo extends JApplet {
  public SignedAppletDemo() {
    JLabel label = new JLabel();
    add(label);
    try {
      File file = new File(".");
      label.setText("The current directory is " + file.getAbsolutePath());
    } catch (Exception ex) {
      ex.printStackTrace();
      label.setText("Security exception ");
    }
  }
}