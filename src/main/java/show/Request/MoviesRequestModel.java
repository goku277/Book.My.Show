package show.Request;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;


@Getter
@Setter
@Component

public class MoviesRequestModel {
    private String movieName;
    private String movieGenre;
    private String movieIMBDRating;
    private String movieActors[];
    private double movieTicketPrice;
    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(length = 8000000)
    private String moviesImage;
}