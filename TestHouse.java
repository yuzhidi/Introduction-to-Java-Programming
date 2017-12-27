
public class TestHouse {
  public static void main(String[] args) {
    House house1 = new House(1, 1750.0);
    House house2 = (House) (house1.clone());
    System.out.println("house1: " + house1);
    System.out.println("house2: " + house2);

  }
}
