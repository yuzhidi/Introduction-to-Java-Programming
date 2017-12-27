<!-- ComputeLoan2.jsp -->
<html>
  <head>
    <title>ComputeLoan Using the Loan Class</title>
  </head>
  <body>
    <%@ page import = "chapter43.Loan" %>
    <jsp:useBean id = "loan" class = "chapter43.Loan"
      scope = "page" ></jsp:useBean>
    <jsp:setProperty name = "loan" property = "*" />
    Loan Amount: <%= loan.getLoanAmount() %><br />
    Annual Interest Rate: <%= loan.getAnnualInterestRate() %><br />
    Number of Years: <%= loan.getNumberOfYears() %><br />
    <b>Monthly Payment: <%= loan.getMonthlyPayment() %><br />
    Total Payment: <%= loan.getTotalPayment() %><br /></b>
  </body>
</html>
