package database_update_event;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    // query method that retrieves a list of Event entities
    // from the database where the processed field is false.
    List<Event> findByProcessedFalse();
}
