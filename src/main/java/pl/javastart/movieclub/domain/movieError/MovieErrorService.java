package pl.javastart.movieclub.domain.movieError;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MovieErrorService {

    private final MovieErrorRepository movieErrorRepository;

    public Page<MovieError> findMovieErrorReportsByMovieId(Long movieId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("dateAdded"));
        return movieErrorRepository.findAllByMovie_Id(movieId, pageable);
    }

    public void deleteMovieErrorReport(Long id){
        movieErrorRepository.deleteById(id);
    }

}
