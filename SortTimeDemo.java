import java.util.*;

public class SortTimeDemo {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the number of elements to sort: ");
    int n = input.nextInt();
    
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < n; i++)
      list.add(i);
    
    Collections.shuffle(list);
    
    long startTime = System.currentTimeMillis();
    Collections.sort(list);
    System.out.println("Sort time in Java is " + 
      (System.currentTimeMillis() - startTime) / 1000 + " seconds");
  }
}