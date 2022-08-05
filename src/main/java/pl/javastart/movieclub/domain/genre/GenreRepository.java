package pl.javastart.movieclub.domain.genre;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

}
