import java.util.Scanner;

public class P1 {
  public static void main(String[] args) {
    System.out.print("Enter two numbers: ");
    Scanner input = new Scanner(System.in);
    
    double number1 = input.nextDouble();
    double number2 = input.nextDouble();
    
    System.out.println(number1 + number2);
    System.out.println(number1 - number2);
    System.out.println(number1 * number2);
    System.out.println(number1 / number2);
    
  }
}
