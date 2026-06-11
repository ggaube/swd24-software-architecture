package database_update_event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EventService(EventRepository eventRepository, RabbitTemplate rabbitTemplate) {
        this.eventRepository = eventRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    // The @Transactional annotation
    // is used to define the scope of a single database transaction
    @Scheduled(fixedRate = 5000) // Poll every 5 seconds
    @Transactional
    public void pollDatabaseAndSendMessages() {
        List<Event> events = eventRepository.findByProcessedFalse();
        for (Event event : events) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.QUEUE_ONE_NAME, event.getMessage());
            event.setProcessed(true);
            eventRepository.save(event);
        }
    }
}

