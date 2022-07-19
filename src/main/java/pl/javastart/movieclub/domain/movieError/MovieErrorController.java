package pl.javastart.movieclub.domain.movieError;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class MovieErrorController {

    private final MovieErrorService movieErrorService;

    @GetMapping("/movie/{id}/error-reports")
    public String getMovieErrors(
            @PathVariable Long id,
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
            Model model) {

        Page<MovieError> movieErrorsPaged = movieErrorService.findErrorReportsByMovieId(id, pageNo, pageSize);
        List<MovieError> movieErrors = movieErrorsPaged.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", movieErrorsPaged.getTotalPages());
        model.addAttribute("totalItems", movieErrorsPaged.getTotalElements());
        model.addAttribute("movieErrors", movieErrors);

        return "movie-error-report";
    }
}
