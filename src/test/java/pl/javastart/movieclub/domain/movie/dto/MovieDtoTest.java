package pl.javastart.movieclub.domain.movie.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovieDtoTest {

    private MovieDto underTest;
//TODO: fix the test - poster field added in movie class
//    @Test
//    void itShouldCreateMovieDto() {
//        //GIVEN
//        long id = 1L;
//        String title = "Forrest Gump";
//        String originalTitle = "Original title of Forrest Gump";
//        String shortDesciption = "Short description about movie Forrest Gump.";
//        String description = "Long description about movie Forrest Gump.";
//        String youtubeTrailerId = "linkToYouTube";
//        int releaseYear = 1997;
//        boolean promoted = false;
//        String genreName = "Drama";
//
//        //WHEN
//        underTest = new MovieDto(
//                id,
//                title,
//                originalTitle,
//                shortDesciption,
//                description,
//                youtubeTrailerId,
//                releaseYear,
//                genreName,
//                promoted
//        );
//
//        //THEN
//        assertThat(underTest.getId()).isEqualTo(id);
//        assertThat(underTest.getTitle()).isEqualTo(title);
//        assertThat(underTest.getOriginalTitle()).isEqualTo(originalTitle);
//        assertThat(underTest.getShortDescription()).isEqualTo(shortDesciption);
//        assertThat(underTest.getDescription()).isEqualTo(description);
//        assertThat(underTest.getYoutubeTrailerId()).isEqualTo(youtubeTrailerId);
//        assertThat(underTest.getReleaseYear()).isEqualTo(releaseYear);
//        assertThat(underTest.isPromoted()).isEqualTo(promoted);
//        assertThat(underTest.getGenre()).isEqualTo(genreName);
//    }
}