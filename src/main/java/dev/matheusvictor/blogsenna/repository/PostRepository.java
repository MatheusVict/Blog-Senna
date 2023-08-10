package dev.matheusvictor.blogsenna.repository;

import dev.matheusvictor.blogsenna.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
  Optional<Post> findBySlug(String slug);

  boolean existsBySlug(String slug);

  List<Post> findByCategorySlug(String categorySlug);
}
