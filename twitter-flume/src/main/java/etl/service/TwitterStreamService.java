package etl.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.FilterStreamParameters;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import etl.listener.TwitterStreamListener;

@Service
public class TwitterStreamService {
  @Resource(name="consumer-key")
  String consumerKey;
  
  @Resource(name="consumer-secret")
  String consumerSecret;
  
  @Resource(name="access-token")
  String accessToken;
  
  @Resource(name="access-token-secret")
  String accessTokenSecret;
  
  @Autowired
  TwitterStreamListener twitterStreamListener;

  private Twitter getTwitterTemplate() {
    TwitterTemplate twitterTemplate =
      new TwitterTemplate(consumerKey, 
                          consumerSecret, 
                          accessToken, 
                          accessTokenSecret);
    
    return twitterTemplate;
  }
  
  @PostConstruct
  public void runService() {
    StreamingOperations    streamOps = null;
    Twitter                twitter   = getTwitterTemplate();
    FilterStreamParameters params    = new FilterStreamParameters();
    List<StreamListener>   listeners = new LinkedList<>();
 
    streamOps = twitter.streamingOperations();
    
    listeners.add(twitterStreamListener);
    params.track("ipad");
    streamOps.filter(params, listeners);
  }
}
