public class Calculator {
  /** Main method */
  public static void main(String[] args) {
    // Check number of strings passed
    if (args.length != 1) {
      System.out.println(
        "Usage: java Calculator \"operand1 operator operand2\"");
      System.exit(0);
    }

    // The result of the operation
    int result = 0;

    // Split items from a string 
    String[] tokens = args[0].split(" ");

    // Determine the operator
    switch (tokens[1].charAt(0)) {
      case '+': result = Integer.parseInt(tokens[0]) +
                         Integer.parseInt(tokens[2]);
                break;
      case '-': result = Integer.parseInt(tokens[0]) -
                         Integer.parseInt(tokens[2]);
                break;
      case '*': result = Integer.parseInt(tokens[0]) *
                         Integer.parseInt(tokens[2]);
                break;
      case '/': result = Integer.parseInt(tokens[0]) /
                         Integer.parseInt(tokens[2]);
    }

    // Display result
    System.out.println(tokens[0] + ' ' + tokens[1] + ' ' 
      + tokens[2] + " = " + result);
  }
}
