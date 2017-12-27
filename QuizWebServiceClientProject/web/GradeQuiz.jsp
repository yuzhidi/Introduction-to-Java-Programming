<!-- GradeQuiz.jsp -->
<%@ page import = "myWebservice.QuizWebService" %>
<%@ page import = "myWebservice.QuizService" %>
<jsp:useBean id = "quizWebService" scope = "session" 
  class = "myWebservice.QuizWebService">
</jsp:useBean>

<html>
<body>
<%
QuizService proxy = quizWebService.getQuizServicePort();
java.util.List<String> quiz = proxy.getQuestions();

// Get the answer from the DisplayQuiz page
java.util.List<Boolean> answers = new java.util.ArrayList<Boolean>();
for (int i = 0; i < quiz.size(); i++) {
  String trueOrFalse = request.getParameter("question" + i);
  if (trueOrFalse.equals("True"))
    answers.add(true); // Answered true
  else if (trueOrFalse.equals("False"))
    answers.add(false); // Answered false
}

// Grade answers
java.util.List<Boolean> result = proxy.gradeQuiz(answers);

// Find the correct count
int correctCount = 0;
for (int i = 0; i < result.size(); i++) {
  if (result.get(i)) 
    correctCount++;
}
%>  

Out of <%= result.size() %> questions, <%= correctCount %> correct.
</body>
</html> 
