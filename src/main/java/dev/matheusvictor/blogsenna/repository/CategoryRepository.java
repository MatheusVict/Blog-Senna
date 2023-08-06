package dev.matheusvictor.blogsenna.repository;

import dev.matheusvictor.blogsenna.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findBySlug(String slug);
}
