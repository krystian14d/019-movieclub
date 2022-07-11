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
public class SearchController {

    private final MovieService movieService;

    @GetMapping("/search")
    public String searchByTitle(@RequestParam(value = "title") String title,
                                @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
                                Model model){

        Page<MovieDto> moviesPaged = movieService.findPagedMoviesByTitle(title, pageNo, pageSize);
        List<MovieDto> movies = moviesPaged.getContent();

        model.addAttribute("heading", "Wyniki wyszukiwania dla hasła: " + title);
        model.addAttribute("description", String.format("Znaleziono %s filmów w bazie.", moviesPaged.getTotalElements()));
        model.addAttribute("movies", movies);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", moviesPaged.getTotalPages());
        model.addAttribute("totalItems", moviesPaged.getTotalElements());

        return "movie-listing";
    }
}
