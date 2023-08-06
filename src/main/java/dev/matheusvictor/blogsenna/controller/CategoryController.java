package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.domain.category.Category;
import dev.matheusvictor.blogsenna.request.category.CategoryPostRequestBody;
import dev.matheusvictor.blogsenna.request.category.CategoryPutRequestBody;
import dev.matheusvictor.blogsenna.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;


  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    return ResponseEntity.ok(categoryService.findAll());
  }

  @GetMapping("/pageable")
  public ResponseEntity<Page<Category>> findAllPageable(Pageable pageable) {
    return ResponseEntity.ok(categoryService.findaAllPageble(pageable));
  }

  @GetMapping("/{slug}")
  public ResponseEntity<Category> findBySlug(@PathVariable String slug) {
    return ResponseEntity.ok(categoryService.findBySlug(slug));
  }

  @PostMapping
  public ResponseEntity<Category> create(@RequestBody CategoryPostRequestBody categoryPostRequestBody) {
    return ResponseEntity.ok(categoryService.create(categoryPostRequestBody));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> update(
          @PathVariable Long id,
          @RequestBody CategoryPutRequestBody categoryPutRequestBody
  ) {
    return ResponseEntity.ok(categoryService.update(id, categoryPutRequestBody));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    categoryService.delete(id);
    return ResponseEntity.ok().build();
  }

}
