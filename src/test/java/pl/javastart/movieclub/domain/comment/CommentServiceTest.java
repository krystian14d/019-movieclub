package pl.javastart.movieclub.domain.comment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import pl.javastart.movieclub.domain.exception.CommentNotFoundException;
import pl.javastart.movieclub.domain.exception.MovieNotFoundException;
import pl.javastart.movieclub.domain.exception.UserNotFoundException;
import pl.javastart.movieclub.domain.movie.Movie;
import pl.javastart.movieclub.domain.movie.MovieRepository;
import pl.javastart.movieclub.domain.user.User;
import pl.javastart.movieclub.domain.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class CommentServiceTest {

    @Autowired
    private CommentService underTest;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new CommentService(userRepository, movieRepository, commentRepository);
    }

    @Test
    void itShouldAddNewComment() throws UserNotFoundException, MovieNotFoundException {
        //given
        String email = "user@example.com";
        Long movieId = 1L;
        User author = new User();
        Movie movie = new Movie();
        String comment = "Comment content";

        given(userRepository.findByEmail(email)).willReturn(Optional.of(author));
        given(movieRepository.findById(movieId)).willReturn(Optional.of(movie));

        //when
        underTest.addNewComment(movieId, comment, email);

        //then
        ArgumentCaptor<Comment> commentArgumentCaptor = ArgumentCaptor.forClass(Comment.class);
        then(commentRepository).should().save(commentArgumentCaptor.capture());
        Comment commentArgumentCaptorValue = commentArgumentCaptor.getValue();

        assertThat(commentArgumentCaptorValue.getCommentContent()).isEqualTo(comment);
        assertThat(commentArgumentCaptorValue.getMovie()).isEqualTo(movie);
        assertThat(commentArgumentCaptorValue.getUser()).isEqualTo(author);
    }

    @Test
    void itShouldThrowMovieNotFoundException() {
        //given
        String email = "user@example.com";
        Long movieId = 1L;
        User author = new User();
        String comment = "Comment content";

        given(userRepository.findByEmail(email)).willReturn(Optional.of(author));
        given(movieRepository.findById(movieId)).willReturn(Optional.empty());

        //when//then
        assertThatThrownBy(() -> underTest.addNewComment(movieId, comment, email))
                .isInstanceOf(MovieNotFoundException.class)
                .hasMessageContaining(String.format("Movie with id %s not found.", movieId));
    }

    @Test
    void itShouldThrowUserNotFoundException() {
        //given
        String email = "user@example.com";
        Long movieId = 1L;
        User author = new User();
        Movie movie = new Movie();
        String comment = "Comment content";

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        //when//then
        assertThatThrownBy(() -> underTest.addNewComment(movieId, comment, email))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(String.format("User %s does not exist.", email));
    }

    @Test
    void itShouldThrowExceptionWhenCommentNotFound() {
        //given
        long id = 1L;
        given(commentRepository.findById(id)).willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> underTest.findCommentById(id))
                .isInstanceOf(CommentNotFoundException.class)
                .hasMessageContaining(String.format("Comment with ID %s does not exist.", id));
    }

    @Test
    void itShouldUpdateComment() throws CommentNotFoundException {
        //given
        Comment updatedComment = new Comment();
        updatedComment.setCommentContent("Comment content");

        long id = 1L;
        Movie movie = new Movie();
        User user = new User();
        Comment commentToUpdate = new Comment();
        commentToUpdate.setUser(user);
        commentToUpdate.setMovie(movie);
        commentToUpdate.setDateAdded(LocalDateTime.now());

        given(commentRepository.findById(updatedComment.getId())).willReturn(Optional.of(commentToUpdate));

        //when
        underTest.updateComment(updatedComment);

        ArgumentCaptor<Comment> commentArgumentCaptor = ArgumentCaptor.forClass(Comment.class);
        //then
        then(commentRepository).should().save(commentArgumentCaptor.capture());
        Comment commentArgumentCaptorValue = commentArgumentCaptor.getValue();

        assertThat(commentArgumentCaptorValue).usingRecursiveComparison()
                .ignoringFields("commentContent")
                .isEqualTo(commentToUpdate);

        assertThat(commentArgumentCaptorValue.getCommentContent()).isEqualTo(updatedComment.getCommentContent());
    }

    @Test
    void itShouldThrowExceptionWhenCommentToUpdateNotFound() {
        //given
        Comment updatedComment = new Comment();
        Long id = 1L;
        updatedComment.setId(id);
        updatedComment.setCommentContent("Comment content");

        given(commentRepository.findById(updatedComment.getId())).willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> underTest.updateComment(updatedComment))
                .isInstanceOf(CommentNotFoundException.class)
                .hasMessageContaining(String.format("Comment with ID %s does not exist.", updatedComment.getId()));
    }
}