package pl.javastart.movieclub.web.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.movieclub.domain.comment.Comment;
import pl.javastart.movieclub.domain.comment.CommentService;
import pl.javastart.movieclub.domain.exception.CommentNotFoundException;

@AllArgsConstructor
@Controller
public class CommentManagementController {

    private final CommentService commentService;

    @GetMapping("/admin/edit-comment")
    public String editComment(@RequestParam long commentId, Model model) throws CommentNotFoundException {
        Comment comment = commentService.updateComment(commentId);
        model.addAttribute("comment", comment);
        return "edit-comment";
    }
}
