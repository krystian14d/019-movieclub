package pl.javastart.movieclub.web.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.javastart.movieclub.domain.genre.GenreService;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;
import pl.javastart.movieclub.domain.movie.MovieService;
import pl.javastart.movieclub.domain.movie.dto.MovieSaveDto;

import java.util.List;

@AllArgsConstructor
@Controller
public class MovieManagementController {

    private final MovieService movieService;
    private final GenreService genreService;

    @GetMapping("/admin/dodaj-film")
    public String addMovieForm(Model model){
        List<GenreDto> allGenres = genreService.findAllGenres();
        model.addAttribute("genres", allGenres);
        MovieSaveDto movie = new MovieSaveDto();
        model.addAttribute("movie", movie);
        return "admin/movie-form";
    }

    @PostMapping("/admin/dodaj-film")
    public String addMovie(MovieSaveDto movie, RedirectAttributes redirectAttributes){
        movieService.addMovie(movie);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Film %s został zapisany.".formatted(movie.getTitle())
        );
        return "redirect:/admin";
    }
}
