package pl.javastart.movieclub.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.movieclub.domain.movie.MovieService;

@AllArgsConstructor
@Controller
public class SearchController {

    private final MovieService movieService;

    @GetMapping("/search")
    public String searchByTitle(@RequestParam String title,
                                @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
                                Model model){




        return "movie-listing";
    }
}
