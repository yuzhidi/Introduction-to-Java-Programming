package jsf2demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.sql.*;
import javax.sql.rowset.CachedRowSet;

@ManagedBean
@ApplicationScoped
public class CourseName {
  private PreparedStatement studentStatement = null;
  private CachedRowSet rowSet; // For course titles
  private String choice; // Selected course
  private String[] titles; // Course titles

  /** Creates a new instance of CourseName */
  public CourseName() {
    initializeJdbc();
  }

  /** Initialize database connection */
  private void initializeJdbc() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");
      
      // Connect to the sample database
      Connection connection = DriverManager.getConnection(
              "jdbc:mysql://localhost/javabook", "scott", "tiger");

      // Get course titles
      PreparedStatement statement = connection.prepareStatement(
        "select title from course");
      rowSet = new com.sun.rowset.CachedRowSetImpl();
      rowSet.populate(statement.executeQuery());
      titles = new String[rowSet.size()];
      int i = 0;
      try {
        while (rowSet.next()) {
          titles[i++] = rowSet.getString(1);
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }

      // Define a SQL statement for getting students
      studentStatement = connection.prepareStatement(
              "select Student.ssn, "
              + "student.firstName, Student.mi, Student.lastName, "
              + "Student.phone, Student.birthDate, Student.street, "
              + "Student.zipCode, Student.deptId "
              + "from Student, Enrollment, Course "
              + "where Course.title = ? "
              + "and Student.ssn = Enrollment.ssn "
              + "and Enrollment.courseId = Course.courseId;");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public String[] getTitles() {
    return titles;
  }

  public String getChoice() {
    return choice;
  }

  public void setChoice(String choice) {
    this.choice = choice;
  }

  public ResultSet getStudents() throws SQLException {
    if (choice == null) {
      if (titles == null)
        return null;
      else
        studentStatement.setString(1, titles[0]);
    } else {
      studentStatement.setString(1, choice); // Set course title
    }
    
    // Get students for the specified course
    CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
    rowSet.populate(studentStatement.executeQuery());
    return rowSet;
  }
}
