package spark.service;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class TweetService2 {

  public static void main(String[] args) {
    String path = 
      "/home/ec2-user/spark/spark-1.2.1-bin-hadoop2.4/README.md";
    
    SparkConf conf = 
      new SparkConf().setAppName("Simple Application")
                     .set("spark.executor.memory", "256m");
    
    JavaSparkContext sc = 
      new JavaSparkContext(conf);
    
    JavaRDD<String> logData = 
      sc.textFile(path).cache();

    long numAs = logData.filter((String line) -> {
      return line.contains("a");
    }).count();
    
    long numBs = logData.filter((String line) -> {
      return line.contains("b");
    }).count();

    System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
  }
}
