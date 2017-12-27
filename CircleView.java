import java.awt.*;
import java.awt.event.*;

public class CircleView extends javax.swing.JPanel {
  private CircleModel model;

  /** Set a model */
  public void setModel(CircleModel newModel) {
    model = newModel;

    if (model != null)
      // Register the view as listener for the model
      model.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          repaint();
        }
      });
  }

  public CircleModel getModel() {
    return model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (model != null) {
      super.paintComponent(g);
      g.setColor(model.getColor());

      int xCenter = getWidth() / 2;
      int yCenter = getHeight() / 2;
      int radius = (int)model.getRadius();

      if (model.isFilled()) {
        g.fillOval(xCenter - radius, yCenter - radius,
          2 * radius, 2 * radius);
      }
      else {
        g.drawOval(xCenter - radius, yCenter - radius,
          2 * radius, 2 * radius);
      }
    }
  }
}
