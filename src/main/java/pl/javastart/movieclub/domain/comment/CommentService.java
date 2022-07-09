package pl.javastart.movieclub.domain.comment;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.javastart.movieclub.domain.exception.CommentNotFoundException;
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
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("dateAdded"));
        return commentRepository.findAllByMovie_Id(id, pageable);
    }

    public Comment findCommentById(Long id) throws CommentNotFoundException {
        return commentRepository.findById(id).orElseThrow(() ->
                new CommentNotFoundException(String.format("Comment with ID %s does not exist.", id)));
    }

    public Comment updateComment(Comment updatedComment) throws CommentNotFoundException {
        Comment commentToUpdate = commentRepository.findById(updatedComment.getId()).orElseThrow(() ->
                new CommentNotFoundException(String.format("Comment with ID %s does not exist.", updatedComment.getId())));
        commentToUpdate.setCommentContent(updatedComment.getCommentContent());
        commentRepository.save(commentToUpdate);
        return commentToUpdate;
    }

    public void deleteComment(long id){
        commentRepository.deleteById(id);
    }


    Comment createNewComment(User author, Movie movie, String comment) {
        Comment newComment = new Comment();
        newComment.setUser(author);
        newComment.setMovie(movie);
        newComment.setCommentContent(comment);
        newComment.setDateAdded(LocalDateTime.now());
        return newComment;
    }
}
