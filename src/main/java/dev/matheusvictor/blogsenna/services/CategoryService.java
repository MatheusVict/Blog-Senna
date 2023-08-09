package dev.matheusvictor.blogsenna.services;

import dev.matheusvictor.blogsenna.domain.category.Category;
import dev.matheusvictor.blogsenna.request.category.CategoryPostRequestBody;
import dev.matheusvictor.blogsenna.request.category.CategoryPutRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
  Category create(CategoryPostRequestBody categoryPostRequestBody);

  List<Category> findAll();

  Page<Category> findaAllPageble(Pageable pageable);

  Category findBySlug(String slug);

  Category findById(Long id);

  Category update(Long id, CategoryPutRequestBody categoryPutRequestBody);

  void delete(Long id);
}
