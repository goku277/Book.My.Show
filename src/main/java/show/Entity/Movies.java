package show.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Table(name = "movies")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    @Column(nullable = true)
    private String movieName;
    @Column(nullable = true)
    private String movieGenre;
    @Column(nullable = true)
    private String movieIMBDRating;
    @Column(nullable = true)
    private String movieActors[];
    private double movieTicketPrice;
    @Lob
    private String moviesImage;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theatre_id", nullable = false)
    @JsonIgnore
    private Theatres theatres;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customers customers;
}