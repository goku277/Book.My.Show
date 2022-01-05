package show.Controller;

import book.my.show.book.my.show.Entity.Movies;
import book.my.show.book.my.show.Entity.Theatres;
import book.my.show.book.my.show.Request.MovieRequestModel;
import book.my.show.book.my.show.Service.TheatresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_my_show/v1")
public class TheatresController {
    @Autowired
    TheatresService theatresService;

    /**
     * Add a new Theatre
     * @param theatres
     * @return
     */
    @PostMapping("/addTheatre")
    public ResponseEntity<?> addTheatre(@RequestBody Theatres theatres) {
        return theatresService.addTheatre(theatres);
    }

    /**
     * Add a movie to the theatre by theatreId
     * @param movies
     * @param id
     * @return
     */
    @PutMapping("/addMovie/{id}")
    public ResponseEntity<?> addMovieToTheatre(@RequestBody Movies movies, @PathVariable(value = "id") long id) {
        return theatresService.addMovie(movies,id);
    }

    /**
     * Set a price tag to the imported movie by the theatre's authorities,
     * By default movie's price is 0, so setting the movie price is necessary by the individual's theatre's authorities.
     * @param movieRequestModel
     * @param theatre_id
     * @param movie_id
     * @return
     */
    @PutMapping("/setMovieTicket/Prices/{theatre_id}/{movie_id}")
    public ResponseEntity<?> setMovieTicketPrices(@RequestBody MovieRequestModel movieRequestModel, @PathVariable(value = "theatre_id") long theatre_id,
                                                  @PathVariable(value = "movie_id") long movie_id) {
        return theatresService.setMovieTicketPrices(movieRequestModel,theatre_id, movie_id);
    }

    /**
     * Fetch all theatres with their movie collections
     * @return
     */

    @GetMapping("/fetch/allTheatres/withMoviesList")
    public ResponseEntity<?> fetchAllTheatresWithMoviesList() {
        return theatresService.fetchAllTheatresWithMoviesList();
    }

    /**
     * Fetch only the theatres.
     * @return
     */

    @GetMapping("/fetch/allTheatres")
    public ResponseEntity<?> fetchAllTheatres() {
        return theatresService.fetchAllTheatres();
    }
}