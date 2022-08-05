package pl.javastart.movieclub.domain.movieError;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.javastart.movieclub.domain.movie.Movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie_error_reports")

public class MovieError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_movie_id")
    private Movie movie;

    @Column(name = "error_description")
    private String errorDescription;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;
}
