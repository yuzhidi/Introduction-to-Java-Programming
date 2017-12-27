import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class ParityGameDemo extends JApplet {
  int SIZE = 10;
  // Create and initialize cells
  private Cell[][] cells =  new Cell[SIZE][SIZE];

  /** Initialize UI */
  public ParityGameDemo() {
    // Panel p to hold cells
    JPanel p = new JPanel(new GridLayout(SIZE, SIZE, 0, 0));
    for (int i = 0; i < SIZE; i++)
      for (int j = 0; j < SIZE; j++)
        p.add(cells[i][j] = new Cell());

    // Set line borders on the cells panel and the status label
    p.setBorder(new LineBorder(Color.red, 1));

    // Place the panel and the label to the applet
    add(p, BorderLayout.CENTER);
  }

  // An inner class for a cell
  public class Cell extends JPanel {
    // Token used for this cell
    private char token = ' ';

    public Cell() {
      setBorder(new LineBorder(Color.black, 1)); // Set cell's border
      addMouseListener(new MyMouseListener());  // Register listener
    }

    /** Return token */
    public char getToken() {
      return token;
    }

    /** Set a new token */
    public void setToken(char c) {
      token = c;
      repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      if (token == 'X') {
        g.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
        g.drawLine(getWidth() - 10, 10, 10, getHeight() - 10);
      }
    }

    private class MyMouseListener extends MouseAdapter {
      /** Handle mouse click on a cell */
      public void mouseClicked(MouseEvent e) {
        // If cell is empty and game is not over
        if (token == ' ') 
          setToken('X'); // Set token in the cell
        else 
          setToken(' '); // Set token in the cell
      }
    }
  }

  /** This main method enables the applet to run as an application */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("ParityGameDemo");

    // Create an instance of the applet
    ParityGameDemo applet = new ParityGameDemo();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Display the frame
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
