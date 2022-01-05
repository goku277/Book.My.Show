package show.Response;

import book.my.show.book.my.show.Entity.Bookings;
import book.my.show.book.my.show.Entity.Movies;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BookingResponseModel {
    private long customerId;
    private String userEmail;
    private Movies movies;
    private String[] theatreAddress;
    private Bookings bookings;
}