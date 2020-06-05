package be.projetblog.technofuturtic.projetblog.repositories;

import be.projetblog.technofuturtic.projetblog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT c " +
            "FROM Comment c " +
            "LEFT JOIN c.post Post " +
            "WHERE c.post.id = ?1")
    List<Comment> findByPostId(Long id);
}
