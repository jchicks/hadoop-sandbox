package etl.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration 
@EnableWebMvc 
@ComponentScan({"etl.service", "etl.listener"})
public class TwitterConfig {

  private static final String consumerKey       = "F7Bt7bG8xV5BZ8eFvqAcWcXgd";
  private static final String consumerSecret    = "nnnr5UU5JNjNysRwKnZJ31oSvfsNqalQhArVgq1PDAmBPwdWT1";
  private static final String accessToken       = "2891642223-VPUBKKQFyFs8nlHPxucUtRi4iXNZK6Tb1hJLeeb";
  private static final String accessTokenSecret = "ZZl4wzH0NG1X792tcbVK916NwadNzG71lovDI5jfIIUqt";
  
  @Bean(name="consumer-key")
  public static String getConsumerkey() {
    return consumerKey;
  }
  
  @Bean(name="consumer-secret")
  public static String getConsumersecret() {
    return consumerSecret;
  }
  
  @Bean(name="access-token")
  public static String getAccesstoken() {
    return accessToken;
  }
  
  @Bean(name="access-token-secret")
  public static String getAccesstokensecret() {
    return accessTokenSecret;
  }
}