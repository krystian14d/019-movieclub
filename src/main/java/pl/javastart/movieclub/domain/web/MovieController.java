package pl.javastart.movieclub.domain.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.javastart.movieclub.domain.movie.MovieService;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/film/{id}")
    public String getMovie(@PathVariable Long id, Model model) {
        Optional<MovieDto> optionalMovie = movieService.findMovieById(id);
        optionalMovie.ifPresent(movie -> model.addAttribute("movie", model));
        return "movie";
    }
}
