package show.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "theatres")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Theatres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theatreId;
    @Column(nullable = true)
    private String theatreName;
    @Column(nullable = true)
    private String[] theatreAddress;
    @Column(nullable = true)
    private String movieStartTimes[];
    @Lob
    private String theatresImage;
    @OneToMany(mappedBy = "theatres", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
  //  @JsonIgnore
    private Set<Movies> movies;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customers customers;
}