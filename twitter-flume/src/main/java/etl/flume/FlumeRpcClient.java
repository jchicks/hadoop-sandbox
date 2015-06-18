package etl.flume;

import java.nio.charset.Charset;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.event.EventBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import etl.config.FlumeConfig;

@Component
public class FlumeRpcClient {
  
  @Autowired
  RpcClient flumeClient;
  
  public void sendToFlume(String json) {
    Event event = 
      EventBuilder.withBody(
        json, Charset.forName("UTF-8"));

    // Send the event
    try {
      flumeClient.append(event);
    } 
    catch (EventDeliveryException e) {
      // clean up and recreate the client
      flumeClient.close();
      flumeClient = null;
      flumeClient = FlumeConfig.getFlumeRpcClient();
    }
  }
}
