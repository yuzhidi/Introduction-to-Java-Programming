import java.util.*;

public class CourseWithEnrollmentEvent {
  private String courseName = "default name";
  private ArrayList<String> students = new ArrayList<String>();
  private int enrollmentCap = 10;
  private ArrayList<EnrollmentListener> enrollmentListenerList;

  public CourseWithEnrollmentEvent() {
  }

  public CourseWithEnrollmentEvent(String courseName) {
    this.courseName = courseName;
  }

  public String getCourseName() {
    return courseName;
  }

  public void addStudent(String student) {
    if (students.size() == enrollmentCap) // Fire EnrollmentEvent
      processEvent(new EnrollmentEvent(this,
        student, enrollmentCap));
    else
      students.add(student);
  }

  public ArrayList<String> getStudents() {
    return students;
  }

  public int getNumberOfStudents() {
    return students.size();
  }

  public int getEnrollmentCap() {
    return enrollmentCap;
  }

  public void setEnrollmentCap(int enrollmentCap) {
    this.enrollmentCap = enrollmentCap;
  }

  /** Register an action event listener */
  public synchronized void addEnrollmentListener
      (EnrollmentListener listener) {
    if (enrollmentListenerList == null) {
      enrollmentListenerList = new ArrayList<EnrollmentListener>(2);
    }

    if (!enrollmentListenerList.contains(listener)) {
      enrollmentListenerList.add(listener);
    }
  }

  /** Remove an action event listener */
  public synchronized void removeEnrollmentListener
      (EnrollmentListener listener) {
    if (enrollmentListenerList != 
        null && enrollmentListenerList.contains(listener)) {
      enrollmentListenerList.remove(listener);
    }
  }

  /** Fire EnrollmentEvent */
  private void processEvent(EnrollmentEvent e) {
    ArrayList<EnrollmentListener> list;

    synchronized (this) {
      if (enrollmentListenerList == null) return;
      list = (ArrayList<EnrollmentListener>)
        enrollmentListenerList.clone();
    }

    for (int i = 0; i < list.size(); i++) {
      EnrollmentListener listener = (EnrollmentListener)list.get(i);
      listener.enrollmentExceeded(e);
    }
  }
}
