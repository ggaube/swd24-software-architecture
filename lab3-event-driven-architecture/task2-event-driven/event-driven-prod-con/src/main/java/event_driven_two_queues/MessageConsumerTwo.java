package event_driven_two_queues;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerTwo {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_TWO_NAME)
    public void receiveMessage(String message) {
        System.out.println("Received message from Queue Two: " + message);
    }
}