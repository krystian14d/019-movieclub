package pl.javastart.movieclub.domain.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class CommentRepositoryTest {

    @Autowired
    private CommentRepository underTest;

    @Test
    void itShouldFindAllCommentsByMovieIdPaginated() {
        //given
        long movieId = 1L;
        int pageNo = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        //when
        Page<Comment> pagedComments = underTest.findAllByMovie_Id(movieId, pageable);

        //then
        assertThat(pagedComments.getContent()).isInstanceOf(List.class)
                .isNotEmpty()
                .hasSize(pageSize);
    }
}