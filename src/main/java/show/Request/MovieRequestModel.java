package show.Request;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component

public class MovieRequestModel {
    private double movieTicketPrice;
}