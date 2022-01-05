package show.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "customers")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    private String userEmail;
    private String userPassword;
    @OneToOne(mappedBy = "customers", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Movies movies;
    @OneToOne(mappedBy = "customers", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Theatres theatres;
    @OneToMany(mappedBy = "customers", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    //  @JsonIgnore
    private Set<Bookings> bookings;
}