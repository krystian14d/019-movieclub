package pl.javastart.movieclub.domain.movie.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;
import pl.javastart.movieclub.domain.genre.Genre;

import static org.assertj.core.api.Assertions.assertThat;

class MovieSaveDtoTest {

    @Mock
    private Genre genre;

    private MovieSaveDto underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldCreateMovieSaveDto() {
        //given
        String title = "Forrest Gump";
        String originalTitle = "Original title of Forrest Gump";
        String shortDesciption = "Short description about movie Forrest Gump.";
        String description = "Long description about movie Forrest Gump.";
        String youtubeTrailerId = "linkToYouTube";
        int releaseYear = 1997;
        boolean promoted = false;
        long genreId = 1L;
        MultipartFile poster = null;

        //when
        underTest = new MovieSaveDto();
        underTest.setTitle(title);
        underTest.setOriginalTitle(originalTitle);
        underTest.setShortDescription(shortDesciption);
        underTest.setDescription(description);
        underTest.setYoutubeTrailerId(youtubeTrailerId);
        underTest.setReleaseYear(releaseYear);
        underTest.setGenreId(genreId);
        underTest.setPromoted(promoted);
        underTest.setPoster(null);

        //then
        assertThat(underTest.getTitle()).isEqualTo(title);
        assertThat(underTest.getOriginalTitle()).isEqualTo(originalTitle);
        assertThat(underTest.getShortDescription()).isEqualTo(shortDesciption);
        assertThat(underTest.getDescription()).isEqualTo(description);
        assertThat(underTest.getYoutubeTrailerId()).isEqualTo(youtubeTrailerId);
        assertThat(underTest.getReleaseYear()).isEqualTo(releaseYear);
        assertThat(underTest.isPromoted()).isEqualTo(promoted);
        assertThat(underTest.getPoster()).isEqualTo(poster);
        assertThat(underTest.getGenreId()).isEqualTo(genreId);
    }
}