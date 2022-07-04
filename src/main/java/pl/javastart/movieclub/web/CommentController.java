package pl.javastart.movieclub.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.movieclub.domain.comment.Comment;
import pl.javastart.movieclub.domain.comment.CommentService;
import pl.javastart.movieclub.domain.comment.dto.NewMovieCommentDto;

import java.util.List;

@AllArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/film/{id}/komentarze")
    public String getComments(@PathVariable Long id,
                              @RequestParam(defaultValue = "0") Integer pageNo,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "id") String sortBy,
                              Model model) {
//        Page<Comment> pagedComments = commentService.findAllCommentsByMovieId(id, pageNo, pageSize, sortBy);
        List<Comment> comments = commentService.findAllCommentsByMovieId(id, pageNo, pageSize, sortBy);
        model.addAttribute("comments", comments);
        return "movie";
    }

    @PostMapping("/add-comment")
    public String addMovieComment(@RequestParam Long movieId, @RequestParam String newComment, @RequestHeader String referer, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        commentService.addNewComment(movieId, newComment, currentUserEmail);
        return "redirect:" + referer;
    }

}
