package pl.javastart.movieclub.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.movieclub.domain.comment.Comment;
import pl.javastart.movieclub.domain.comment.CommentService;
import pl.javastart.movieclub.domain.comment.dto.NewMovieCommentDto;
import pl.javastart.movieclub.domain.movie.MovieService;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;
import pl.javastart.movieclub.domain.rating.RatingService;

import java.util.List;

@AllArgsConstructor
@Controller
public class MovieController {

    private final MovieService movieService;
    private final RatingService ratingService;
    private final CommentService commentService;

    @GetMapping("/movie/{id}")
    public String getMovie(@PathVariable Long id, Model model, Authentication authentication) {
        MovieDto movie = movieService.findMovieById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("movie", movie);

        if(authentication != null){
            String currentUserEmail = authentication.getName();
            Integer rating = ratingService.getUserRatingForMovie(currentUserEmail, id).orElse(0);
            model.addAttribute("userRating", rating);
        }
        List<Comment> comments = commentService.findAllCommentsByMovieId(id, 0, 5, "id");
        model.addAttribute("comments", comments);
        return "movie";
    }

    @GetMapping("/top10")
    public String findTop10(Model model){
        List<MovieDto> top10Movies = movieService.findTopMovies(10);
        model.addAttribute("heading", "Filmowe TOP 10");
        model.addAttribute("description", "Filmy najlepiej oceniane przez użytkowników");
        model.addAttribute("movies", top10Movies);
        return "movie-listing";
    }
}
