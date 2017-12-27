package chapter45; 

import myWebservice.ScoreWebService;
import myWebservice.ScoreService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FindScoreApplet extends JApplet {
  // Declare a service object and a proxy object
  private ScoreWebService scoreWebService = new ScoreWebService();
  private ScoreService proxy = scoreWebService.getScoreServicePort();

  private JButton jbtGetScore = new JButton("Get Score");
  private JTextField jtfName = new JTextField();
  private JTextField jtfScore = new JTextField();

  public void init() {
    JPanel jPanel1 = new JPanel();
    jPanel1.setLayout(new GridLayout(2, 2));
    jPanel1.add(new JLabel("Name"));
    jPanel1.add(jtfName);
    jPanel1.add(new JLabel("Score"));
    jPanel1.add(jtfScore);

    add(jbtGetScore, BorderLayout.SOUTH);
    add(jPanel1, BorderLayout.CENTER);

    jbtGetScore.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        getScore();
      }
    });
  }

  private void getScore() {
    try {
      // Get student score
      double score = proxy.findScore(jtfName.getText().trim());

      // Display the result
      if (score < 0)
        jtfScore.setText("Not found");
      else
        jtfScore.setText(new Double(score).toString());
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
