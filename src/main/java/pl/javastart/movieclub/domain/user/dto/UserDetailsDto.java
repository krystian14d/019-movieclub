package pl.javastart.movieclub.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.javastart.movieclub.domain.comment.Comment;
import pl.javastart.movieclub.domain.rating.Rating;
import pl.javastart.movieclub.domain.user.UserRole;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class UserDetailsDto {

    private Long id;
    private String email;
    private List<UserRole> roles;
    private List<Comment> comments;
    private List<Rating> ratings;
}
