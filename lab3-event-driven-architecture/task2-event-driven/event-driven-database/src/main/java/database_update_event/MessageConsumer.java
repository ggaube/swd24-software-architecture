package database_update_event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    /**
     * The @RabbitListener annotation in Spring is used to designate a
     * method as a listener for messages from a specified RabbitMQ queue.
     * When a message arrives in the queue, the annotated method is invoked
     * with the message as a parameter.
     * This allows you to process messages asynchronously and
     * handle them within your Spring application
     * @param message
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_ONE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Guten Tag SWD24, I received message from Queue Database: " + message);
    }
}