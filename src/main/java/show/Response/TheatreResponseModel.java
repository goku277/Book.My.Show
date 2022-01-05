package show.Response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TheatreResponseModel {
    private long theatreId;
    private String theatreName;
    private String[] theatreAddress;
    private String movieStartTimes[];
}