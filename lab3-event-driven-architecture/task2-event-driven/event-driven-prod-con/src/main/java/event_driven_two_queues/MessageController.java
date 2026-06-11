package event_driven_two_queues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageProducerOne messageProducerOne;
    private final MessageProducerTwo messageProducerTwo;

    @Autowired
    public MessageController(MessageProducerOne messageProducerOne, MessageProducerTwo messageProducerTwo) {
        this.messageProducerOne = messageProducerOne;
        this.messageProducerTwo = messageProducerTwo;
    }

    @PostMapping("/queueOne")
    public void sendMessageToQueueOne(@RequestBody String message) {
        messageProducerOne.sendMessage(message);
    }

    @PostMapping("/queueTwo")
    public void sendMessageToQueueTwo(@RequestBody String message) {
        messageProducerTwo.sendMessage(message);
    }
}

