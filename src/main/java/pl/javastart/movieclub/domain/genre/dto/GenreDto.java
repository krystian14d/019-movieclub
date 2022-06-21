package pl.javastart.movieclub.domain.genre.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {

    private Long id;
    private String name;
    private String description;

}
