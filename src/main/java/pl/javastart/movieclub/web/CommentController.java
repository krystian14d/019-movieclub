package pl.javastart.movieclub.web;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.movieclub.domain.comment.CommentService;
import pl.javastart.movieclub.domain.exception.MovieNotFoundException;
import pl.javastart.movieclub.domain.exception.UserNotFoundException;

@AllArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/add-comment")
    public String addMovieComment(@RequestParam long movieId,
                                  @RequestParam String newComment,
                                  @RequestHeader String referer,
                                  Authentication authentication) throws UserNotFoundException, MovieNotFoundException {
        String currentUserEmail = authentication.getName();
        commentService.addNewComment(movieId, newComment, currentUserEmail);
        return "redirect:" + referer;
    }

}
