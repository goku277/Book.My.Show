package show.Repo;
import book.my.show.book.my.show.Entity.Theatres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatresRepo extends JpaRepository<Theatres,Long> {
}