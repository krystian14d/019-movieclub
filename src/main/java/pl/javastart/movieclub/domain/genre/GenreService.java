package pl.javastart.movieclub.domain.genre;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javastart.movieclub.domain.genre.dto.GenreDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public Optional<GenreDto> findGenreByName(String name) {
        return genreRepository.findByNameIgnoreCase(name)
                .map(GenreDtoMapper::map);
    }

    public List<GenreDto> findAllGenres(){
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false)
                .map(GenreDtoMapper::map)
                .toList();
    }

    @Transactional
    public void addGenre(GenreDto genre){
        Genre genreToSave = new Genre();
        genreToSave.setName(genre.getName());
        genreToSave.setDescription(genre.getDescription());
        genreRepository.save(genreToSave);
    }

    public Optional<GenreDto> findGenreById(Long id){
        return genreRepository.findById(id)
                .map(GenreDtoMapper::map);
    }
}
