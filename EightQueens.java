import java.awt.*;
import javax.swing.*;

public class EightQueens extends JApplet {
  public static final int SIZE = 8; // The size of the chess board
  // queens are placed at (i, queens[i])
  // -1 indicates that no queen is currently placed in the ith row
  // Initially, place a queen at (0, 0) in the 0th row
  private int[] queens = {-1, -1, -1, -1, -1, -1, -1, -1}; 

  public EightQueens() {
    if (search()) // Search for a solution 
      add(new ChessBoard(), BorderLayout.CENTER);
    else
      JOptionPane.showMessageDialog(null, "No solution found");
  }

  /** Search for a solution */
  private boolean search() {
    // k - 1 indicates the number of queens placed so far
    // We are looking for a position in the kth row to place a queen
    int k = 0;
    while (k >= 0 && k < SIZE) {
      // Find a position to place a queen in the kth row
      int j = findPosition(k);
      if (j < 0) {
        queens[k] = -1;
        k--; // back track to the previous row
      } else {
        queens[k] = j;
        k++;
      }
    }
    
    if (k == -1)
      return false; // No solution
    else
      return true; // A solution is found
  }

  public int findPosition(int k) {
    int start = queens[k] + 1; // Search for a new placement

    for (int j = start; j < SIZE; j++) {
      if (isValid(k, j))
        return j; // (k, j) is the place to put the queen now
    }

    return -1;
  }
  
  /** Return true if a queen can be placed at (row, column) */
  public boolean isValid(int row, int column) {
    for (int i = 1; i <= row; i++)
      if (queens[row - i] == column // Check column
        || queens[row - i] == column - i // Check upleft diagonal
        || queens[row - i] == column + i) // Check upright diagonal
        return false; // There is a conflict
    return true; // No conflict
  }
  
  class ChessBoard extends JPanel {
    private java.net.URL url 
      = getClass().getResource("image/queen.jpg");
    private Image queenImage = new ImageIcon(url).getImage();

    ChessBoard() {
      setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      // Paint the queens
      for (int i = 0; i < SIZE; i++) {
        int j = queens[i]; // The position of the queen in row i
        g.drawImage(queenImage, j * getWidth() / SIZE, 
          i * getHeight() / SIZE, getWidth() / SIZE, 
          getHeight() / SIZE, this);
      }

      // Draw the horizontal and vertical lines
      for (int i = 1; i < SIZE; i++) {
        g.drawLine(0, i * getHeight() / SIZE, 
          getWidth(), i * getHeight() / SIZE);
        g.drawLine(i * getWidth() / SIZE, 0, 
          i * getWidth() / SIZE, getHeight());
      }
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("EightQueens");
    EightQueens applet = new EightQueens();
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}