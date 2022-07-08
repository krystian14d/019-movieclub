package pl.javastart.movieclub.domain.comment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import pl.javastart.movieclub.domain.exception.MovieNotFoundException;
import pl.javastart.movieclub.domain.exception.UserNotFoundException;
import pl.javastart.movieclub.domain.movie.Movie;
import pl.javastart.movieclub.domain.movie.MovieRepository;
import pl.javastart.movieclub.domain.user.User;
import pl.javastart.movieclub.domain.user.UserRepository;

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
    void itShouldFindPagedComments() {
        //given

        //when

        //then

    }
}