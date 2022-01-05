package show.Service;
import book.my.show.book.my.show.Entity.Movies;
import book.my.show.book.my.show.Entity.Theatres;
import book.my.show.book.my.show.Repo.MoviesRepo;
import book.my.show.book.my.show.Repo.TheatresRepo;
import book.my.show.book.my.show.Request.MovieRequestModel;
import book.my.show.book.my.show.Request.MoviesRequestModel;
import book.my.show.book.my.show.Response.TheatreResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class TheatresService {
    @Autowired
    TheatresRepo theatresRepo;
    @Autowired
    MoviesRepo moviesRepo;
//    @Autowired
//    TheatreResponseModel theatreResponseModel;
    public ResponseEntity<?> addTheatre(Theatres theatres) {
        theatresRepo.save(theatres);
        return ResponseEntity.status(HttpStatus.OK).body(theatres);
    }

    public ResponseEntity<?> addMovie(Movies movies, long theatre_id) {
        Theatres existingTheatre= theatresRepo.findById(theatre_id).orElse(null);
        List<Movies> moviesList= moviesRepo.findAll();
        Set<Movies> set=find(moviesList, theatre_id);
        Set<Movies> moviesSet= new LinkedHashSet<>();
     //   Movies receivedMovies= mapMoviesModelToMovies(movies);
        moviesSet.add(movies);
        moviesSet.addAll(set);
        movies.setTheatres(existingTheatre);
        existingTheatre.setMovies(moviesSet);
        Theatres updatedTheatres= theatresRepo.save(existingTheatre);
      //  Movies updatedMovies= moviesRepo.save(movies);
        TheatreResponseModel theatreResponseModel= new TheatreResponseModel();
        theatreResponseModel.setTheatreId(existingTheatre.getTheatreId());
    //    theatreResponseModel.setMovies(moviesSet);
        theatreResponseModel.setTheatreName(existingTheatre.getTheatreName());
        theatreResponseModel.setMovieStartTimes(existingTheatre.getMovieStartTimes());
//        theatreResponseModel.setPriceOfMovie(existingTheatre.getPriceOfMovie());
        theatreResponseModel.setTheatreAddress(existingTheatre.getTheatreAddress());
     //   System.out.println("updated Theatres : " + updatedTheatres);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTheatres);
    }

    private Movies mapMoviesModelToMovies(MoviesRequestModel movies) {
        Movies movies1= new Movies();
        movies1.setMovieIMBDRating(movies.getMovieIMBDRating());
        movies1.setMovieGenre(movies.getMovieGenre());
        movies1.setMovieActors(movies.getMovieActors());
        movies1.setMoviesImage(movies.getMoviesImage());
        movies1.setMovieTicketPrice(movies.getMovieTicketPrice());
        movies1.setMovieName(movies.getMovieName());
        return movies1;
    }

    private Set<Movies> find(List<Movies> moviesList, long theatre_id) {
        Set<Movies> set= new LinkedHashSet<>();
        for (Movies movies : moviesList) {
            if (movies.getTheatres().getTheatreId()==theatre_id) {
                set.add(movies);
            }
        }
        return set;
    }

    public ResponseEntity<?> setMovieTicketPrices(MovieRequestModel movieRequestModel, long theatre_id, long movie_id) {
       List<Movies> existingMovies= moviesRepo.findAll();
       Movies filterMovie=findMovie(existingMovies,movie_id,theatre_id);
       filterMovie.setMovieTicketPrice(movieRequestModel.getMovieTicketPrice());
       moviesRepo.save(filterMovie);
       Theatres theatres= theatresRepo.findById(theatre_id).get();
       return ResponseEntity.status(HttpStatus.OK).body(theatres);
    }

    private Movies findMovie(List<Movies> existingMovies, long movie_id, long theatre_id) {
        for (Movies movies : existingMovies) {
            if (movies.getMovieId()==movie_id && movies.getTheatres().getTheatreId()==theatre_id) {
                return movies;
            }
        }
        return null;
    }

    public ResponseEntity<?> fetchAllTheatres() {
        List<Theatres> theatresList= theatresRepo.findAll();
        List<TheatreResponseModel> list= Filter(theatresList);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    private List<TheatreResponseModel> Filter(List<Theatres> theatresList) {
        List<TheatreResponseModel> theatreResponseModelList= new ArrayList<>();
        for (Theatres theatres : theatresList) {
            TheatreResponseModel theatreResponseModel= new TheatreResponseModel();
            theatreResponseModel.setTheatreId(theatres.getTheatreId());
            theatreResponseModel.setTheatreName(theatres.getTheatreName());
            theatreResponseModel.setTheatreAddress(theatres.getTheatreAddress());
            theatreResponseModel.setMovieStartTimes(theatres.getMovieStartTimes());
            theatreResponseModelList.add(theatreResponseModel);
        }
        return theatreResponseModelList;
    }

    public ResponseEntity<?> fetchAllTheatresWithMoviesList() {
        return ResponseEntity.status(HttpStatus.OK).body(theatresRepo.findAll());
    }
}