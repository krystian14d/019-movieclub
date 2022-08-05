package pl.javastart.movieclub.web.admin;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.javastart.movieclub.domain.movieError.MovieError;
import pl.javastart.movieclub.domain.movieError.MovieErrorService;

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

        Page<MovieError> movieErrorsPaged = movieErrorService.findMovieErrorReportsByMovieId(id, pageNo, pageSize);
        List<MovieError> movieErrors = movieErrorsPaged.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", movieErrorsPaged.getTotalPages());
        model.addAttribute("totalItems", movieErrorsPaged.getTotalElements());
        model.addAttribute("movieErrors", movieErrors);

        return "admin/movie-error-reports";
    }

    @PostMapping("/movie-error-report/delete/{id}")
    public String deleteMovieErrorReport(
            @PathVariable Long id,
            @RequestHeader String referer,
            RedirectAttributes redirectAttributes
    ) {

        movieErrorService.deleteMovieErrorReport(id);

        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Zgłoszenie zostało usunięte.");

        return "redirect:" + referer;
    }
}
