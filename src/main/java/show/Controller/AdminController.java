package show.Controller;
import book.my.show.book.my.show.Request.AdminRequestModel;
import book.my.show.book.my.show.Request.MoviesRequestModel;
import book.my.show.book.my.show.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_my_show/v1")
public class AdminController {
    @Autowired
    AdminService adminService;

    /**
     *
     * @param moviesRequestModel
     * @param movieId
     * @return
     * Editing a movie through Admin's portal
     */
    @PutMapping("/admin/edit/movies/{movieId}")
    public ResponseEntity<?> editMovies(@RequestBody MoviesRequestModel moviesRequestModel,
                                        @PathVariable(value = "movieId") long movieId) {
        return adminService.editMovies(moviesRequestModel, movieId);
    }

    /**
     * Delete an existing movie by movieId
     * @param moviesId
     * @return
     */
    @DeleteMapping("/admin/delete/movies/{moviesId}")
    public ResponseEntity<?> deleteMovies(@PathVariable(value = "moviesId") long moviesId) {
        return adminService.deleteMovies(moviesId);
    }

    /**
     * Delete all the movies by Admin
     * @return
     */
    @DeleteMapping("/admin/delete/movies/all")
    public ResponseEntity<?> deleteAllMovies() {
        return adminService.deleteAllMovies();
    }

    /**
     * View all the movies
     * @return
     */
    @GetMapping("/admin/movies/view/all")
    public ResponseEntity<?> viewAllMovies() {
        return adminService.viewAllMovies();
    }

    /**
     * Book all the movies
     * @return
     */
    @GetMapping("/admin/view/bookings/all")
    public ResponseEntity<?> viewAllBookingsByAdmin() {
        return adminService.viewAllBookingsByAdmin();
    }

    /**
     * Add or register an Admin
     * @param adminRequestModel
     * @return
     */

    @PostMapping("/admin/register")
    public ResponseEntity<?> adminRegister(@RequestBody AdminRequestModel adminRequestModel) {
        return adminService.adminRegister(adminRequestModel);
    }

    /**
     * Login to Admin's portal
     * @param adminRequestModel
     * @param adminId
     * @return
     */

    @PostMapping("/admin/login/{adminId}")
    public ResponseEntity<?> adminLogin(@RequestBody AdminRequestModel adminRequestModel,
                                        @PathVariable(value = "adminId") long adminId) {
        return adminService.adminLogin(adminRequestModel, adminId);
    }
}