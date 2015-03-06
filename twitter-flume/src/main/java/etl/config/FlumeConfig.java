package etl.config;

import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("etl.flume")
public class FlumeConfig {

  @Bean
  public static RpcClient getFlumeRpcClient() {
    String host = "localhost";
    int    port = 41414;
    
    return RpcClientFactory.getDefaultInstance(host, port);
  }
}
