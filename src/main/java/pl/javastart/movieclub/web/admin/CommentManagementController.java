package pl.javastart.movieclub.web.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.javastart.movieclub.domain.comment.Comment;
import pl.javastart.movieclub.domain.comment.CommentService;
import pl.javastart.movieclub.domain.exception.CommentNotFoundException;

@AllArgsConstructor
@Controller
public class CommentManagementController {

    private final CommentService commentService;

    @GetMapping("/admin/edit-comment/{commentId}")
    public String editComment(@PathVariable long commentId, Model model) throws CommentNotFoundException {
        Comment comment = commentService.findCommentById(commentId);
        model.addAttribute("comment", comment);
        return "/admin/edit-comment-form";
    }

    @PostMapping("/admin/edit-comment")
    public String saveEditedComment(Comment comment) throws CommentNotFoundException {
        Long id = commentService.updateComment(comment);
        return "redirect:/movie/" + id.toString();
    }

//    @PostMapping("/admin/delete-comment")
//    public String deleteComment(@RequestParam long commentId,
//                                @RequestParam String movieId, RedirectAttributes redirectAttributes) {
//        commentService.deleteComment(commentId);
//        redirectAttributes.addFlashAttribute(
//                AdminController.NOTIFICATION_ATTRIBUTE,
//                "Komentarz został usunięty.");
//        return "redirect:/movie/" + movieId;
//    }

    @PostMapping("/admin/delete-comment")
    public String deleteComment(@RequestParam long commentId,
                                @RequestParam String movieId,
                                @RequestHeader String referer,
                                RedirectAttributes redirectAttributes) {
        commentService.deleteComment(commentId);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Komentarz został usunięty.");
        return "redirect:" + referer;
    }

}

