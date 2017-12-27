import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopupMenuDemo extends JApplet {
  private JPopupMenu jPopupMenu1 = new JPopupMenu();
  private JMenuItem jmiNew = new JMenuItem("New",
    new ImageIcon(getClass().getResource("image/new.gif")));
  private JMenuItem jmiOpen = new JMenuItem("Open",
    new ImageIcon(getClass().getResource("image/open.gif")));
  private JMenuItem jmiPrint = new JMenuItem("Print",
    new ImageIcon(getClass().getResource("image/print.gif")));
  private JMenuItem jmiExit = new JMenuItem("Exit");
  private JTextArea jTextArea1 = new JTextArea();

  public PopupMenuDemo() {
    jPopupMenu1.add(jmiNew);
    jPopupMenu1.add(jmiOpen);
    jPopupMenu1.addSeparator();
    jPopupMenu1.add(jmiPrint);
    jPopupMenu1.addSeparator();
    jPopupMenu1.add(jmiExit);
    jPopupMenu1.add(jmiExit);

    add(new JScrollPane(jTextArea1), BorderLayout.CENTER);

    jmiNew.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Process New");
      }
    });
    jmiOpen.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Process Open");
      }
    });
    jmiPrint.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Process Print");
      }
    });
    jmiExit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(1);
      }
    });
    jTextArea1.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) { // For Motif
        showPopup(e);
      }

      @Override
      public void mouseReleased(MouseEvent e) { // For Windows
        showPopup(e);
      }
    });
  }

  /** Display popup menu when triggered */
  private void showPopup(java.awt.event.MouseEvent evt) {
    if (evt.isPopupTrigger())
      jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
  }

  public static void main(String[] args) {
    PopupMenuDemo applet = new PopupMenuDemo();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("PopupMenuDemo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
