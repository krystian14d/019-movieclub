package pl.javastart.movieclub.domain.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javastart.movieclub.domain.movie.Movie;
import pl.javastart.movieclub.domain.movie.MovieRepository;
import pl.javastart.movieclub.domain.user.User;
import pl.javastart.movieclub.domain.user.UserRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommentService {

    private UserRepository userRepository;
    private MovieRepository movieRepository;
    private CommentRepository commentRepository;

    public void addNewComment(Long movieId, String comment, String email) {
        User author = userRepository.findByEmail(email).orElseThrow();
        Movie commentedMovie = movieRepository.findById(movieId).orElseThrow();
        Comment newComment = createNewComment(author, commentedMovie, comment);
        commentRepository.save(newComment);
    }

    Comment createNewComment(User author, Movie movie, String comment){
        Comment newComment = new Comment();
        newComment.setUser(author);
        newComment.setMovie(movie);
        newComment.setComment(comment);
        newComment.setDateAdded(LocalDateTime.now());
        return newComment;
    }
}
