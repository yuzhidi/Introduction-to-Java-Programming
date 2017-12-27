import java.awt.event.*;

public class TestCourseWithActionEvent {
  CourseWithActionEvent course = 
    new CourseWithActionEvent("Java Programming");
  
  public TestCourseWithActionEvent() {
    course.setEnrollmentCap(2);
    ActionListener listener = new Listener();
    course.addActionListener(listener);
    course.addStudent("John");
    course.addStudent("Jim");
    course.addStudent("Tim");
  }
  
  public static void main(String[] args) {
    new TestCourseWithActionEvent();
  }

  private class Listener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      System.out.println("Enrollment cap exceeded"); 
    }
  }
}
