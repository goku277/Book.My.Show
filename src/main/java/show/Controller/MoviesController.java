package show.Controller;

import book.my.show.book.my.show.Entity.Movies;
import book.my.show.book.my.show.Service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book_my_show/v1")
public class MoviesController {
    @Autowired
    MoviesService moviesService;

    /**
     * Add a movie independently, without adding this movie to any theatre
     * @param movies
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<?> addMovie(@RequestBody Movies movies) {
        return moviesService.addNewMovie(movies);
    }
}