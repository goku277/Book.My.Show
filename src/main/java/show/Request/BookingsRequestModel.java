package show.Request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component



public class BookingsRequestModel {
    private String paymentMode;
    private double paymentAmount;
    private String theatreName;
    private String movieName;
    private String bookingStatus;
    private String movieStartTimes[];
}