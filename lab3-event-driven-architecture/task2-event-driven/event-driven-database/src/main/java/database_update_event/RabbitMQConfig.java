package database_update_event;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_ONE_NAME = "queueDatabase";
    public static final String EXCHANGE_NAME = "exchange";

    /**
     * false: The queue is not durable. This means that if
     * the RabbitMQ broker restarts, the queue and its contents will
     * not be available.
     * true: The queue is durable. It will survive
     * broker restarts, but this requires more storage and handling by RabbitMQ.
     */
    @Bean
    public Queue queueOne() {
        return new Queue(QUEUE_ONE_NAME, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingQueueOne(Queue queueOne, TopicExchange exchange) {
        return BindingBuilder.bind(queueOne).to(exchange).with(QUEUE_ONE_NAME);
    }


}

