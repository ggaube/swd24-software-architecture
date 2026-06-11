package event_driven_two_queues;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_ONE_NAME = "queueOne";
    public static final String QUEUE_TWO_NAME = "queueTwo";

    @Bean
    public Queue queueOne() {
        return new Queue(QUEUE_ONE_NAME, false);
    }

    @Bean
    public Queue queueTwo() {
        return new Queue(QUEUE_TWO_NAME, false);
    }
}

