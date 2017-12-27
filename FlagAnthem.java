import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

public class FlagAnthem extends JApplet {
  private final static int NUMBER_OF_NATIONS = 7;
  private int current = 0;
  private ImageIcon[] icons = new ImageIcon[NUMBER_OF_NATIONS];
  private AudioClip[] audioClips = new AudioClip[NUMBER_OF_NATIONS];
  private AudioClip currentAudioClip;

  private JLabel jlblImageLabel = new JLabel();
  private JButton jbtResume = new JButton("Resume");
  private JButton jbtSuspend = new JButton("Suspend");
  private JComboBox jcboNations = new JComboBox(new Object[]
    {"Denmark", "Germany", "China", "India", "Norway", "UK", "US"});

  public FlagAnthem() {
    // Load image icons and audio clips
    for (int i = 0; i < NUMBER_OF_NATIONS; i++) {
      icons[i] = new ImageIcon(getClass().getResource(
        "image/flag" + i + ".gif"));
      audioClips[i] = Applet.newAudioClip(
        getClass().getResource("audio/anthem" + i + ".mid"));
    }

    JPanel panel = new JPanel();
    panel.add(jbtResume);
    panel.add(jbtSuspend);
    panel.add(new JLabel("Select"));
    panel.add(jcboNations);
    add(jlblImageLabel, BorderLayout.CENTER);
    add(panel, BorderLayout.SOUTH);

    jbtResume.addActionListener(new ActionListener() {
      @Override 
      public void actionPerformed(ActionEvent e) {
        start();
      }
    });
    jbtSuspend.addActionListener(new ActionListener() {
      @Override 
      public void actionPerformed(ActionEvent e) {
        stop();
      }
    });
    jcboNations.addActionListener(new ActionListener() {
      @Override 
      public void actionPerformed(ActionEvent e) {
        stop();
        current = jcboNations.getSelectedIndex();
        presentNation(current);
      }
    });

    jlblImageLabel.setIcon(icons[0]);
    jlblImageLabel.setHorizontalAlignment(JLabel.CENTER);
    currentAudioClip = audioClips[0];
    currentAudioClip.play();
  }

  private void presentNation(int index) {
    jlblImageLabel.setIcon(icons[index]);
    jcboNations.setSelectedIndex(index);
    currentAudioClip = audioClips[index];
    currentAudioClip.play();
  }

  @Override 
  public void start() {
    currentAudioClip.play();
  }

  @Override 
  public void stop() {
    currentAudioClip.stop();
  }

  /** Main method */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("FlagAnthem");

    // Create an instance of the applet
    FlagAnthem applet = new FlagAnthem();
    applet.init();

    // Add the applet instance to the frame
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Display the frame
    frame.setSize(400, 300);
    frame.setVisible(true);
  }
}
