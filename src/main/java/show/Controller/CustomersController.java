package show.Controller;

import book.my.show.book.my.show.Entity.Customers;
import book.my.show.book.my.show.Request.BookingsRequestModel;
import book.my.show.book.my.show.Request.CancelRequestModel;
import book.my.show.book.my.show.Request.CustomerLoginRequestModel;
import book.my.show.book.my.show.Service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_my_show/v1")
public class CustomersController {
    @Autowired
    CustomersService customersService;

    /**
     * Add or register a new Customer
     * @param customers
     * @return
     */
    @PostMapping("/register/new_customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customers customers) {
        return customersService.addCustomer(customers);
    }

    /**
     * Book a movie
     * @param bookings
     * @param customerId
     * @param movieId
     * @param theatreId
     * @return
     */
    @PutMapping("/book/{customerId}/{movieId}/{theatreId}")
    public ResponseEntity<?> bookMovie(@RequestBody BookingsRequestModel bookings, @PathVariable(value = "customerId") long customerId,
                                       @PathVariable(value = "movieId") long movieId,
                                       @PathVariable(value = "theatreId") long theatreId) {
        return customersService.bookMovie(bookings, customerId, movieId, theatreId);
    }

    /**
     * Cancel a pre-booked movie
     * @param cancelRequestModel
     * @param customerId
     * @param bookingId
     * @return
     */
    @PutMapping("/cancel/booking/{customerId}/{bookingId}")
    public ResponseEntity<?> cancelBookedTickets(@RequestBody CancelRequestModel cancelRequestModel,
                                                 @PathVariable(value = "customerId") long customerId,
                                                 @PathVariable(value = "bookingId") long bookingId) {
        return customersService.cancelBookedMovie(cancelRequestModel, customerId, bookingId);
    }

    /**
     * View all booked movies
     * @param customerId
     * @return
     */
    @GetMapping("/view/bookings/{customerId}")
    public ResponseEntity<?> viewAllBookings(@PathVariable(value = "customerId") long customerId) {
        return customersService.viewAllBookings(customerId);
    }

    /**
     * View All Movies
     * @return
     */
    @GetMapping("/view/movies/all")
    public ResponseEntity<?> viewAllMovies() {
        return customersService.viewAllMovies();
    }

    /**
     *  Customer login
     */

    @PostMapping("/customer/login/{customerId}")
    public ResponseEntity<?> customerLogin(@RequestBody CustomerLoginRequestModel customerLoginRequestModel,
                                           @PathVariable(value = "customerId") long customerId) {
        return customersService.login(customerLoginRequestModel, customerId);
    }
}