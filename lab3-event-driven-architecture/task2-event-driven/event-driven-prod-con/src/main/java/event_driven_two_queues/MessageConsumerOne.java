package event_driven_two_queues;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerOne {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ONE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Received message from Queue One: " + message);
    }
}