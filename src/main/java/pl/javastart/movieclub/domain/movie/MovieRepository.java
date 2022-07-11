package pl.javastart.movieclub.domain.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Page<Movie> findAllByGenre_NameIgnoreCase(String genre, Pageable pageable);

    @Query("select m from Movie m join m.ratings r group by m order by avg(r.rating) desc")
    List<Movie> findTopByRating(Pageable page);

    Set<Movie> findAllByGenre_Id(Long id);

    Page<Movie> findAllByPromotedIsTrue(Pageable pageable);

////    @Query("SELECT m FROM Movie m WHERE m.title LIKE CONCAT('%',UPPER(:title),'%') OR m.originalTitle LIKE CONCAT('%',UPPER(:title),'%')")
//    Page<Movie> findAllByTitleOrOriginalTitleContainingIgnoreCase(String title, Pageable pageable);
//
//    @Query("SELECT m FROM Movie m WHERE m.title LIKE CONCAT('%',UPPER(:title),'%') OR m.originalTitle LIKE CONCAT('%',UPPER(:title),'%')")
//    Page<Movie> findMoviesByTitle(String title, Pageable pageable);

    Page<Movie> findAllByTitleContainingIgnoreCaseOrOriginalTitleContainingIgnoreCase(String title, String originalTitle, Pageable pageable);
}
