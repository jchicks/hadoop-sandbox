package etl.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import etl.flume.FlumeRpcClient;

@Component
public class TwitterStreamListener extends DefaultTwitterListener {
  
  private final ObjectMapper mapper = new ObjectMapper();
    
  @Autowired
  FlumeRpcClient flumeRpcClient;
  
  @Override
  public void onTweet(Tweet tweet) {
    String json;
    
    try {  
      json = mapper.writeValueAsString(tweet);

      System.out.println(json);

      flumeRpcClient.sendToFlume(json);
    } 
    catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
