package pl.javastart.movieclub.domain.movie;

import lombok.Getter;
import lombok.Setter;
import pl.javastart.movieclub.domain.genre.Genre;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String originalTitle;
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(
            name = "genre_id",
            referencedColumnName = "id"
    )
    private Genre genre;
    private boolean promoted;
}
