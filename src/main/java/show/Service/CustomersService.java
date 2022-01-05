package show.Service;
import book.my.show.book.my.show.Entity.Bookings;
import book.my.show.book.my.show.Entity.Customers;
import book.my.show.book.my.show.Entity.Movies;
import book.my.show.book.my.show.Entity.Theatres;
import book.my.show.book.my.show.Repo.BookingsRepo;
import book.my.show.book.my.show.Repo.CustomerRepo;
import book.my.show.book.my.show.Repo.MoviesRepo;
import book.my.show.book.my.show.Repo.TheatresRepo;
import book.my.show.book.my.show.Request.BookingsRequestModel;
import book.my.show.book.my.show.Request.CancelRequestModel;
import book.my.show.book.my.show.Request.CustomerLoginRequestModel;
import book.my.show.book.my.show.Response.BookingResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomersService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    MoviesRepo moviesRepo;
    @Autowired
    TheatresRepo theatresRepo;
    @Autowired
    BookingsRepo bookingsRepo;
    public ResponseEntity<?> addCustomer(Customers customers) {
        Customers savedCustomers= customerRepo.save(customers);
        return ResponseEntity.status(HttpStatus.OK).body(savedCustomers);
    }

    public ResponseEntity<?> bookMovie(BookingsRequestModel bookings, long customerId, long movieId, long theatreId) {
        Customers customers= customerRepo.findById(customerId).get();
        Movies movies= moviesRepo.findById(movieId).get();
        Theatres theatres= theatresRepo.findById(theatreId).get();
        customers.setMovies(movies);
        customers.setTheatres(theatres);
        Bookings bookings1= new Bookings();
        bookings1.setCustomers(customers);
        bookings1.setBookingStatus(bookings.getBookingStatus());
        bookings1.setMovieName(bookings.getMovieName());
        bookings1.setPaymentAmount(bookings.getPaymentAmount());
        bookings1.setTheatreName(bookings.getTheatreName());
        bookings1.setPaymentMode(bookings.getPaymentMode());
        Set<Bookings> set= new LinkedHashSet<>();
        set.add(bookings1);
        customers.setBookings(set);
        BookingResponseModel bookingResponseModel= new BookingResponseModel();

        bookingResponseModel.setBookings(bookings1);
        bookingResponseModel.setMovies(movies);
        bookingResponseModel.setUserEmail(customers.getUserEmail());
        bookingResponseModel.setTheatreAddress(theatres.getTheatreAddress());
        bookingResponseModel.setCustomerId(customerId);
        customerRepo.save(customers);
        return ResponseEntity.status(HttpStatus.OK).body(bookingResponseModel);
    }

    public ResponseEntity<?> cancelBookedMovie(CancelRequestModel cancelRequestModel, long customerId, long bookingId) {
        Customers customers= customerRepo.findById(customerId).get();
        Bookings bookings5= bookingsRepo.findById(bookingId).get();
        if (bookings5.getBookingId()!=bookingId) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No booking found with bookingId " + bookings5 + " for this customer " + customerId);
        }
        Set<Bookings> bookings= customers.getBookings();
        for (Bookings bookings1 : bookings) {
            if (bookings1.getCustomers().getCustomerId()==customerId && bookings1.getBookingId()==bookingId) {
                if (bookings1.getBookingStatus().equalsIgnoreCase("Booked")) {
                    bookings1.setBookingStatus("Cancelled");
                    Bookings savedBookings= bookingsRepo.save(bookings1);
                    return ResponseEntity.status(HttpStatus.OK).body(savedBookings);
                }
            }
        }
        return null;
    }

    public ResponseEntity<?> viewAllBookings(long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingsRepo.findAllById(Collections.singleton(customerId)));
    }

    public ResponseEntity<?> viewAllMovies() {
        List<Movies> moviesList= moviesRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(moviesList);
    }

    public ResponseEntity<?> login(CustomerLoginRequestModel customerLoginRequestModel, long customerId) {
        Customers customers= customerRepo.findById(customerId).get();
        if (customers.getUserEmail().equals(customerLoginRequestModel.getUserEmail()) &&
                customers.getUserPassword().equals(customerLoginRequestModel.getUserPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body("User logged in successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email and password not found");
    }
}