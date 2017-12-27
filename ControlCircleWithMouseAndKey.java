import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlCircleWithMouseAndKey extends JFrame {
  private JButton jbtEnlarge = new JButton("Enlarge");
  private JButton jbtShrink = new JButton("Shrink");
  private CirclePanel canvas = new CirclePanel();
  
  public ControlCircleWithMouseAndKey() {
    JPanel panel = new JPanel(); // Use the panel to group buttons
    panel.add(jbtEnlarge);
    panel.add(jbtShrink);
    
    this.add(canvas, BorderLayout.CENTER); // Add canvas to center
    this.add(panel, BorderLayout.SOUTH); // Add buttons to the frame 
    
    jbtEnlarge.addActionListener(new ActionListener() { 
      @Override 
      public void actionPerformed(ActionEvent e) {
        canvas.enlarge();
        canvas.requestFocusInWindow();
      }
    });
    
    jbtShrink.addActionListener(new ActionListener() { 
      @Override 
      public void actionPerformed(ActionEvent e) {
        canvas.shrink();
        canvas.requestFocusInWindow();
      }
    });
    
    canvas.addMouseListener(new MouseAdapter() {
      @Override 
      public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) 
          canvas.enlarge();
        else if (e.getButton() == MouseEvent.BUTTON3) 
          canvas.shrink();
      }
    });
    
    canvas.setFocusable(true);
    canvas.addKeyListener(new KeyAdapter() {
      @Override 
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) 
          canvas.enlarge();
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) 
          canvas.shrink();
      }
    });
  }
  
  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new ControlCircleWithMouseAndKey();
    frame.setTitle("ControlCircle");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 200);
    frame.setVisible(true);
  }
  
  class CirclePanel extends JPanel { // Inner class
    private int radius = 5; // Default circle radius
      
    /** Enlarge the circle */
    public void enlarge() {
      radius++;
      repaint();
    }
    
    /** Shrink the circle */
    public void shrink() {
      if (radius >= 1) radius--;
      repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawOval(getWidth() / 2 - radius, getHeight() / 2 - radius, 
        2 * radius, 2 * radius);
    }
  }   
}  
