/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf2demo;

import java.sql.*;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class Test {
  
  @Resource(name="jdbc:mysql://localhost:3306/javabook")
  DataSource dataSource;

  public static void main(String[] args)
      throws SQLException, ClassNotFoundException {
    new Test();
  }
  
  public Test()
      throws SQLException, ClassNotFoundException {
    // Load the JDBC driver
//    Class.forName("com.mysql.jdbc.Driver");
//    System.out.println("Driver loaded");

    // Establish a connection
    Connection connection = dataSource.getConnection();
    
//      ("jdbc:mysql://localhost/javabook", "scott", "tiger");
//    System.out.println("Database connected");

    // Create a statement
    Statement statement = connection.createStatement();

    // Execute a statement
    ResultSet resultSet = statement.executeQuery
      ("select firstName, mi, lastName from Student where lastName "
        + " = 'Smith'");

    // Iterate through the result and print the student names
    while (resultSet.next())
      System.out.println(resultSet.getString(1) + "\t" +
        resultSet.getString(2) + "\t" + resultSet.getString(3));

    // Close the connection
    connection.close();
  }
}
