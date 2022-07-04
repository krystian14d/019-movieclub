package pl.javastart.movieclub.domain.comment;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.javastart.movieclub.domain.movie.Movie;
import pl.javastart.movieclub.domain.movie.MovieRepository;
import pl.javastart.movieclub.domain.user.User;
import pl.javastart.movieclub.domain.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    public List<Comment> findAllCommentsByMovieId(Long id, Integer pageNo, Integer pageSize, String sortBy) {

//        Page<Comment> pagedComments = commentRepository.findAllByMovie_Id(id,
//                PageRequest.of(pageNo, pageSize, Sort.by(sortBy)));
        return commentRepository.findAllByMovie_Id(id);
//        return pagedComments;
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
