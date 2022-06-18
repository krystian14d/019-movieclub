package pl.javastart.movieclub.domain.genre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class GenreRepositoryTest {

    @Autowired
    private GenreRepository underTest;

    @Test
    void itShouldSaveAndFindGenreByName() {
        //GIVEN
//        long genreId = 100L;
        Genre genre = new Genre();
//        genre.setId(genreId);
        String genreName = "Genre name";
        genre.setName(genreName);
        String genreDescription = "Genre description";
        genre.setDescription(genreDescription);
        underTest.save(genre);

        //WHEN
        Optional<Genre> genreOptional = underTest.findByNameIgnoreCase(genreName);

        //THEN
        assertThat(genreOptional).isPresent();
        assertThat(genreOptional.get()).usingRecursiveComparison().ignoringFields("id").isEqualTo(genre);

    }
}