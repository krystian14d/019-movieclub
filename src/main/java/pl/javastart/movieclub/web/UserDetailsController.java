package pl.javastart.movieclub.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.javastart.movieclub.domain.comment.Comment;
import pl.javastart.movieclub.domain.exception.UserNotFoundException;
import pl.javastart.movieclub.domain.rating.Rating;
import pl.javastart.movieclub.domain.user.UserRole;
import pl.javastart.movieclub.domain.user.UserService;
import pl.javastart.movieclub.domain.user.dto.UserDetailsDto;

import java.util.List;

@AllArgsConstructor
@Controller
public class UserDetailsController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String getUserProfileDetails(
            @PathVariable Long id, Model model) throws UserNotFoundException {

        UserDetailsDto userDetails = userService.getUserDetails(id);
        List<UserRole> userRoles = userDetails.getRoles();
        List<Comment> userComments = userDetails.getComments();
        List<Rating> userRatings = userDetails.getRatings();

        model.addAttribute("user", userDetails);
        model.addAttribute("roles", userRoles);
        model.addAttribute("comments", userComments);
        model.addAttribute("ratings", userRatings);

        return "user-details";
    }

    @GetMapping("/panel/{userEmail}")
    public String getUserIdByEmail(@PathVariable String userEmail) throws UserNotFoundException {
        Long userId = userService.getUserIdByEmail(userEmail);
        return "redirect:/user/" + userId;
    }


}
