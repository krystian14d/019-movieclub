package pl.javastart.movieclub.domain.genre;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public Optional<GenreDto> findGenreByName(String name) {
        return genreRepository.findByNameIgnoreCase(name)
                .map(GenreDtoMapper::map);
    }
}
