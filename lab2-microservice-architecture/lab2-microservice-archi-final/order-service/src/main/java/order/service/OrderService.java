package order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Service layer class responsible for handling business logic related to Orders
 */
@Service
public class OrderService {

    // Repository used to interact with the database layer

    // @Autowired tells the Spring container: Find a bean of type OrderRepository
    // in the application context and inject it here automatically.
    @Autowired
    private OrderRepository orderRepository;

    // RestTemplate used to make HTTP calls to external microservices
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Retrieves an order by its ID and enriches it with
     * user and product data from external services.
     *
     * @param id the ID of the order
     * @return a formatted string containing order details,
     *         or a message if the order is not found
     */
    public String getOrder(Long id) {

        // Attempt to fetch the order from the database
        Optional<Orders> order = orderRepository.findById(id);

        // Check if the order exists
        if (order.isPresent()) {

            // Extract the order object from Optional
            Orders currentOrder = order.get();

            // Fetch user details from UserService using REST call
            // Assumes UserService is running on port 8081
            String user = restTemplate.getForObject(
                    "http://localhost:8081/users/" + currentOrder.getUserId(),
                    String.class
            );

            // Fetch product details from ProductService using REST call
            // Assumes ProductService is running on port 8083
            String product = restTemplate.getForObject(
                    "http://localhost:8083/products/" + currentOrder.getProductId(),
                    String.class
            );

            // Construct and return a combined response string
            return "Order: " + currentOrder.getId() +
                    ", " + user +
                    ", " + product +
                    ", Quantity: " + currentOrder.getQuantity();
        }

        // Return fallback message if order is not found
        return "Order not found";
    }
}