import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AnimationDemo extends JFrame {
  public AnimationDemo() {
    // Create two MovingMessagePanel for displaying two moving messages
    this.setLayout(new GridLayout(2, 1));
    add(new MovingMessagePanel("message 1 moving?"));
    add(new MovingMessagePanel("message 2 moving?"));
  }

  /** Main method */
  public static void main(String[] args) {
    AnimationDemo frame = new AnimationDemo();
    frame.setTitle("AnimationDemo");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(280, 100);
    frame.setVisible(true);
  }

  // Inner class: Displaying a moving message
  static class MovingMessagePanel extends JPanel {
    private String message = "Welcome to Java";
    private int xCoordinate = 0;
    private int yCoordinate = 20;
    private Timer timer = new Timer(1000, new TimerListener());
     
    public MovingMessagePanel(String message) {
      this.message = message;

      // Start timer for animation
      timer.start();
      
      // Control animation speed using mouse buttons
      this.addMouseListener(new MouseAdapter() {
        @Override 
        public void mouseClicked(MouseEvent e) {
          int delay = timer.getDelay();
      	  if (e.getButton() == MouseEvent.BUTTON1) 
      	    timer.setDelay(delay > 10 ? delay - 10 : 0);
      	  else if (e.getButton() == MouseEvent.BUTTON3) 
      	    timer.setDelay(delay < 50000 ? delay + 10 : 50000);
      	}
      });
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      if (xCoordinate > getWidth()) {
        xCoordinate = -20;
      }
      xCoordinate += 5;
      g.drawString(message, xCoordinate, yCoordinate);
    }

    class TimerListener implements ActionListener {
      @Override 
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    }
  }
}
