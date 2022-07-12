package pl.javastart.movieclub.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.movieclub.domain.movie.MovieService;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;

import java.util.List;

@AllArgsConstructor
@Controller
public class HomeController {

    private final MovieService movieService;

    @GetMapping("/")
    public String home(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
            Model model) {

        Page<MovieDto> promotedMoviesPaged = movieService.findAllPromotedMovies(pageNo, pageSize);
        List<MovieDto> promotedMovies = promotedMoviesPaged.getContent();

        model.addAttribute("heading", "Promowane filmy");
        model.addAttribute("description", "Filmy polecane przez nasz zespół");
        model.addAttribute("movies", promotedMovies);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", promotedMoviesPaged.getTotalPages());
        model.addAttribute("totalItems", promotedMoviesPaged.getTotalElements());

        return "movie-listing";
    }
}
