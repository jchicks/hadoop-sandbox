package mapreduce;

import java.io.IOException;

import mapreduce.mapper.WordMapper;
import mapreduce.reduce.SumReducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application implements CommandLineRunner {

  @Bean
  Job createJob(Configuration configuration) {
    Job job = null;
    try {
      job = Job.getInstance(new Configuration());
      
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(IntWritable.class);
      
      job.setMapperClass(WordMapper.class); 
      job.setReducerClass(SumReducer.class);  

      job.setInputFormatClass(TextInputFormat.class);
      job.setOutputFormatClass(TextOutputFormat.class);
    } 
    catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
    
    return job;
  }
  
  @Autowired
  Job job;
  
  @Override
  public void run(String... args) throws Exception {
    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    job.setJarByClass(Application.class);

    job.submit();
  }

  public static void main(String[] args) {
    
    if (args.length != 2) {
      System.out.println("usage: [input] [output]");
      System.exit(-1);
    }
    
    SpringApplication.run(Application.class, args);
  }
}
