package be.projetblog.technofuturtic.projetblog.repositories;

import be.projetblog.technofuturtic.projetblog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT p " +
            "FROM Post p " +
            "LEFT JOIN p.category Category " +
            "WHERE p.category.id = ?1")
    List<Post> findByCategoryId(Long id);
}
