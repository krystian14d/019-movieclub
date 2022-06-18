package pl.javastart.movieclub.domain.movie;

import pl.javastart.movieclub.domain.movie.dto.MovieDto;

public class MovieDtoMapper {
    public static MovieDto map(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getOriginalTitle(),
                movie.getShortDescription(),
                movie.getDescription(),
                movie.getYoutubeTrailerId(),
                movie.getReleaseYear(),
                movie.getGenre().getName(),
                movie.isPromoted()
        );
    }
}
