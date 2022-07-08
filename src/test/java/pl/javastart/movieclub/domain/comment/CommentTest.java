package pl.javastart.movieclub.domain.comment;

import org.junit.jupiter.api.Test;
import pl.javastart.movieclub.domain.movie.Movie;
import pl.javastart.movieclub.domain.user.User;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    private Comment underTest;

    @Test
    void itShouldCreateComment() {
        //given
        Long id = 1L;
        Movie movie = new Movie();
        User user = new User();
        String comment = "comment content";
        LocalDateTime dateAdded = LocalDateTime.of(2022, 07, 07, 12, 00);

        //when
        underTest = new Comment();
        underTest.setId(id);
        underTest.setMovie(movie);
        underTest.setUser(user);
        underTest.setCommentContent(comment);
        underTest.setDateAdded(dateAdded);

        //then
        assertThat(underTest.getId()).isEqualTo(id);
        assertThat(underTest.getMovie()).isEqualTo(movie);
        assertThat(underTest.getUser()).isEqualTo(user);
        assertThat(underTest.getCommentContent()).isEqualTo(comment);
        assertThat(underTest.getDateAdded()).isEqualTo(dateAdded);

    }
}