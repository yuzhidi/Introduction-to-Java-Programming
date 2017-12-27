import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIDemoPickFourCards extends JApplet {
  private JLabel jlblCard1 = new JLabel();
  private JLabel jlblCard2 = new JLabel();
  private JLabel jlblCard3 = new JLabel();
  private JLabel jlblCard4 = new JLabel();

  private JButton jbtRefresh = new JButton("Refresh");
  private ImageIcon[] cardIcons = new ImageIcon[52];
  private JPanel panel1 = new JPanel();

  private ArrayList<Integer> list = new ArrayList<Integer>();

  public GUIDemoPickFourCards() {
    for (int i = 0; i < 52; i++)
      list.add(i);

    // Load the image icons
    for (int i = 0; i < 52; i++)
      cardIcons[i] = new ImageIcon("image/card/" + (i + 1) + ".png");

    panel1.add(jlblCard1);
    panel1.add(jlblCard2);
    panel1.add(jlblCard3);
    panel1.add(jlblCard4);

    JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    panel3.add(jbtRefresh);

    add(panel1, BorderLayout.CENTER);
    add(panel3, BorderLayout.SOUTH);

    refresh();

    jbtRefresh.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        refresh();
      }
    });

  }

  private void refresh() {
    Collections.shuffle(list);

    int index1 = list.get(0);
    int index2 = list.get(1);
    int index3 = list.get(2);
    int index4 = list.get(3);

    jlblCard1.setIcon(cardIcons[index1]);
    jlblCard2.setIcon(cardIcons[index2]);
    jlblCard3.setIcon(cardIcons[index3]);
    jlblCard4.setIcon(cardIcons[index4]);
  }

  public static void main(String[] args) {
    GUIDemoPickFourCards applet = new GUIDemoPickFourCards();
    JFrame frame = new JFrame();
    // EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("GUIDemoPickFourCards");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.pack();
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}