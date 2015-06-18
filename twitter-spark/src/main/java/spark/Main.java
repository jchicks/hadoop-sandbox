package spark;

public class Main {
  
  public static void test(Runnable run) {
    run.run();
  }
  
  public static void main(String[] args) {
    String foo = "foo";
    
    test(() -> {
      System.out.println("sup " + foo); 
    });
    
    
  }
}
