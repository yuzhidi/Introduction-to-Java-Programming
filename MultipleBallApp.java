import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MultipleBallApp extends JApplet {
  public MultipleBallApp() {
    add(new BallControl());
  }

  class BallControl extends JPanel {
    private BallPanel ballPanel = new BallPanel();
    private JButton jbtSuspend = new JButton("Suspend");
    private JButton jbtResume = new JButton("Resume");
    private JButton jbtAdd = new JButton("+1");
    private JButton jbtSubtract = new JButton("-1");
    private JScrollBar jsbDelay = new JScrollBar();

    public BallControl() {
      // Group buttons in a panel
      JPanel panel = new JPanel();
      panel.add(jbtSuspend);
      panel.add(jbtResume);
      panel.add(jbtAdd);
      panel.add(jbtSubtract);

      // Add ball and buttons to the panel
      ballPanel.setBorder(
        new javax.swing.border.LineBorder(Color.red));
      jsbDelay.setOrientation(JScrollBar.HORIZONTAL);
      ballPanel.setDelay(jsbDelay.getMaximum());
      setLayout(new BorderLayout());
      add(jsbDelay, BorderLayout.NORTH);
      add(ballPanel, BorderLayout.CENTER);
      add(panel, BorderLayout.SOUTH);

      // Register listeners
      jbtSuspend.addActionListener(new Listener());
      jbtResume.addActionListener(new Listener());
      jbtAdd.addActionListener(new Listener());
      jbtSubtract.addActionListener(new Listener());
      jsbDelay.addAdjustmentListener(new AdjustmentListener() {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
          ballPanel.setDelay(jsbDelay.getMaximum() - e.getValue());
        }
      });
    }

    class Listener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtSuspend) 
          ballPanel.suspend();
        else if (e.getSource() == jbtResume) 
          ballPanel.resume();
        else if (e.getSource() == jbtAdd) 
          ballPanel.add();
        else if (e.getSource() == jbtSubtract) 
          ballPanel.subtract();
      }
    }
  }

  class BallPanel extends JPanel {
    private int delay = 10;
    private ArrayList<Ball> list = new ArrayList<Ball>();

    // Create a timer with the initial dalay
    protected Timer timer = new Timer(delay, new ActionListener() {
      @Override /** Handle the action event */
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    });

    public BallPanel() {
      timer.start();
    }

    public void add() {
      list.add(new Ball());
    }

    public void subtract() {
      if (list.size() > 0)
        list.remove(list.size() - 1); // Remove the last ball
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      for (int i = 0; i < list.size(); i++) {
        Ball ball = (Ball)list.get(i); // Get a ball
        g.setColor(ball.color); // Set ball color

        // Check boundaries
        if (ball.x < 0 || ball.x > getWidth()) 
          ball.dx = -ball.dx;

        if (ball.y < 0 || ball.y > getHeight()) 
          ball.dy = -ball.dy;

        // Adjust ball position
        ball.x += ball.dx;
        ball.y += ball.dy;
        g.fillOval(ball.x - ball.radius, ball.y - ball.radius, 
          ball.radius * 2, ball.radius * 2);
      }
    }

    public void suspend() {
      timer.stop(); 
    }

    public void resume() {
      timer.start(); 
    }

    public void setDelay(int delay) {
      this.delay = delay;
      timer.setDelay(delay);
    }
  }

  class Ball {
    int x = 0;
    int y = 0; // Current ball position
    int dx = 2; // Increment on ball's x-coordinate
    int dy = 2; // Increment on ball's y-coordinate
    int radius = 5; // Ball radius
    Color color = new Color((int)(Math.random() * 256),
        (int)(Math.random() * 256), (int)(Math.random() * 256));
  }

  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JApplet applet = new MultipleBallApp();
    frame.add(applet);
    frame.setTitle("MultipleBallApp");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 200);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}