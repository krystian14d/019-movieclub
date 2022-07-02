package pl.javastart.movieclub.web;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.movieclub.domain.user.rating.RatingService;

@AllArgsConstructor
@Controller
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/ocen-film")
    public String addMovieRating(@RequestParam Long movieId, @RequestParam int rating,
                                 @RequestHeader String referer, Authentication authentication){
        String currentUserEmail = authentication.getName();
        ratingService.addOrUpdateRating(currentUserEmail, movieId, rating);
        return "redirect:" + referer;
    }
}
