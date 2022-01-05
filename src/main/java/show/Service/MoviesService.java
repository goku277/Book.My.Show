package show.Service;

import book.my.show.book.my.show.Entity.Movies;
import book.my.show.book.my.show.Repo.CustomerRepo;
import book.my.show.book.my.show.Repo.MoviesRepo;
import book.my.show.book.my.show.Repo.TheatresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesService {
    @Autowired
    TheatresRepo theatresRepo;
    @Autowired
    MoviesRepo moviesRepo;
    @Autowired
    CustomerRepo customerRepo;

    public ResponseEntity<?> addNewMovie(Movies movies) {
        return ResponseEntity.status(HttpStatus.OK).body(moviesRepo.save(movies));
    }

    public List<Movies> getAllFiles() {
        return moviesRepo.findAll();
    }
}