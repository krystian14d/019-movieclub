package pl.javastart.movieclub.domain.comment;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.javastart.movieclub.domain.exception.MovieNotFoundException;
import pl.javastart.movieclub.domain.exception.UserNotFoundException;
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

    public void addNewComment(long movieId, String comment, String email) throws UserNotFoundException, MovieNotFoundException {

        User author = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User %s does not exist.", email))
                        );
        Movie commentedMovie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new MovieNotFoundException(String.format("Movie with id %s not found.", movieId))
                );

        Comment newComment = createNewComment(author, commentedMovie, comment);
        commentRepository.save(newComment);
    }


    public Page<Comment> findAllPagedCommentsByMovieId(Long id, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return commentRepository.findAllByMovie_Id(id, pageable);
    }


    Comment createNewComment(User author, Movie movie, String comment) {
        Comment newComment = new Comment();
        newComment.setUser(author);
        newComment.setMovie(movie);
        newComment.setComment(comment);
        newComment.setDateAdded(LocalDateTime.now());
        return newComment;
    }
}
