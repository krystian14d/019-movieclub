package pl.javastart.movieclub.domain.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByMovie_Id(Long id);

//    Page<Comment> findAllByMovie_Id(Long id, PageRequest paging);

}
