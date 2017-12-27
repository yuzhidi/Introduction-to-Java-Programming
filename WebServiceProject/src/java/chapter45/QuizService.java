package chapter45;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import java.util.ArrayList;
import com.sun.xml.ws.developer.servlet.HttpSessionScope;

@HttpSessionScope
@WebService(name = "QuizService", serviceName = "QuizWebService")
public class QuizService {
  private ArrayList<Object[]> quiz = new ArrayList<Object[]>();
  
  public QuizService() {
    // Initialize questions and answers
    quiz.add(new Object[]{
      "Is Atlanta the capital of Georgia?", true});
    quiz.add(new Object[]{
      "Is Columbia the capital of South Carolina?", true});
    quiz.add(new Object[]{
      "Is Fort Wayne the capital of Indiana?", false});
    quiz.add(new Object[]{
      "Is New Orleans the capital of Louisiana?", false});
    quiz.add(new Object[]{
      "Is Chicago the capital of Illinois?", false});      
    
    // Shuffle to generate a random quiz for a client
    java.util.Collections.shuffle(quiz); 
  }

  @WebMethod(operationName = "getQuestions")
  public java.util.List<String> getQuestions() {        
    // Extract questions from quiz
    List<String> questions = new  ArrayList<String>();
    for (int i = 0; i < quiz.size(); i++) {
      questions.add((String)(quiz.get(i)[0]));
    }
    
    return questions; // Return questions in the quiz
  }
    
  @WebMethod(operationName = "gradeQuiz")
  public List<Boolean> gradeQuiz(List<Boolean> answers) {       
    List<Boolean> result = new ArrayList<Boolean>();
    for (int i = 0; i < quiz.size(); i++)
      result.add(quiz.get(i)[1] == answers.get(i));
    
    return result;
  }
}