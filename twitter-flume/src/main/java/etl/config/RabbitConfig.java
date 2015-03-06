package etl.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("etl")
public class RabbitConfig {

  public final static String TWITTER_UPDATE_QUEUE = "twitter-update-queue";

  @Bean
  @Qualifier(TWITTER_UPDATE_QUEUE)
  public Queue queue() {
    return new Queue(TWITTER_UPDATE_QUEUE, false);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(TWITTER_UPDATE_QUEUE);
  }

  @Bean
  public Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue)
                         .to(exchange)
                         .with(TWITTER_UPDATE_QUEUE);
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory =
      new CachingConnectionFactory(
        "localhost", 5672);

    return connectionFactory;
  }

  @Bean
  public AmqpAdmin amqpAdmin() {
    return new RabbitAdmin(connectionFactory());
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    return new RabbitTemplate(connectionFactory());
  }
}