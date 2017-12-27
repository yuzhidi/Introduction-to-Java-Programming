import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;
import java.util.Arrays;

public class ParallelSum extends RecursiveTask<Integer> {
  private int[] list;
  private int low;
  private int high;

  private static final int THRESHOLD = 50;

  public ParallelSum(int[] list, int low, int high) {
    this.list = list;
    this.low = low;
    this.high = high;
  }

  @Override
  public Integer compute() {
    if (high - low < THRESHOLD) {
      int sum = 0;

      for (int i = low; i < high; ++i)
        sum += list[i];

      return new Integer(sum);
    } 
    else {
      int mid = (low + high) / 2;

      RecursiveTask<Integer> left = new ParallelSum(list, low, mid);
      RecursiveTask<Integer> right = new ParallelSum(list, mid, high);

      right.fork();
      left.fork(); 
      return new Integer(left.join().intValue() + right.join().intValue());
    }
  }
  
  public static void main(String[] args) {
    int[] list = new int[1000000];
    for (int i = 0; i < list.length; i++)
      list[i] = 1;
    
    RecursiveTask<Integer> sumTask = new ParallelSum(list, 0, list.length);
    ForkJoinPool mainPool = new ForkJoinPool();
 
    int result = mainPool.invoke(sumTask);
    System.out.println(result);
  }
}