package show.Repo;
import book.my.show.book.my.show.Entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepo extends JpaRepository<Bookings,Long> {
}