package pl.javastart.movieclub.web;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.movieclub.domain.comment.CommentService;

@AllArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    //TODO: zaimplementować opcję dodawania komentarza, przesłać w MovieManagementController obiekt MovieCommentDto z polami movieID oraz comment
    @PostMapping("/dodaj-komentarz")
    public String addMovieComment(@RequestParam Long movieId, @RequestParam String comment,
                                  @RequestHeader String referer, Authentication authentication) {
        String currentUserEmail = authentication.getName();

        return "redirect:" + referer;
    }
}
