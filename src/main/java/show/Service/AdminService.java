package show.Service;
import book.my.show.book.my.show.Entity.Admin;
import book.my.show.book.my.show.Entity.Movies;
import book.my.show.book.my.show.Repo.AdminRepo;
import book.my.show.book.my.show.Repo.BookingsRepo;
import book.my.show.book.my.show.Repo.MoviesRepo;
import book.my.show.book.my.show.Request.AdminRequestModel;
import book.my.show.book.my.show.Request.MoviesRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    MoviesRepo moviesRepo;
    @Autowired
    BookingsRepo bookingsRepo;
    public ResponseEntity<?> editMovies(MoviesRequestModel moviesRequestModel, long movieId) {
        Movies movies= moviesRepo.findById(movieId).get();
        movies.setMovieTicketPrice(moviesRequestModel.getMovieTicketPrice());
        movies.setMovieName(moviesRequestModel.getMovieName());
        movies.setMovieGenre(moviesRequestModel.getMovieGenre());
        movies.setMovieActors(moviesRequestModel.getMovieActors());
        movies.setMovieIMBDRating(moviesRequestModel.getMovieIMBDRating());
        Movies updatedMoviesByAdmin=moviesRepo.save(movies);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMoviesByAdmin);
    }

    public ResponseEntity<?> deleteMovies(long movieId) {
        moviesRepo.deleteById(movieId);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted a movie with Id " + movieId);
    }

    public ResponseEntity<?> deleteAllMovies() {
        moviesRepo.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted all Movies");
    }

    public ResponseEntity<?> viewAllMovies() {
        List<Movies> moviesList= moviesRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(moviesList);
    }

    public ResponseEntity<?> viewAllBookingsByAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingsRepo.findAll());
    }

    public ResponseEntity<?> adminRegister(AdminRequestModel adminRequestModel) {
        Admin admin= new Admin();
        admin.setEmailId(adminRequestModel.getEmailId());
        admin.setAdminPassword(adminRequestModel.getAdminPassword());
        return ResponseEntity.status(HttpStatus.OK).body(adminRepo.save(admin));
    }

    public ResponseEntity<?> adminLogin(AdminRequestModel adminRequestModel, long adminId) {
        Admin admin= adminRepo.findById(adminId).get();
        if (admin.getEmailId().equals(adminRequestModel.getEmailId()) &&
                admin.getAdminPassword().equals(adminRequestModel.getAdminPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body("Admin successfully logged in");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin with email and password not found");
    }
}