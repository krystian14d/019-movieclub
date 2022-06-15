package pl.javastart.movieclub.domain.movie;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javastart.movieclub.domain.movie.dto.MovieDto;

import java.util.List;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieDto> findAllPromotedMovies() {
        return movieRepository.findAllByPromotedIsTrue()
                .stream()
                .map(MovieDtoMapper::map)
                .toList();
    }
}
