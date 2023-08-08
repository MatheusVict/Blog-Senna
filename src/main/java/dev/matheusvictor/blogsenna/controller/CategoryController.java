package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.domain.category.Category;
import dev.matheusvictor.blogsenna.request.category.CategoryPostRequestBody;
import dev.matheusvictor.blogsenna.request.category.CategoryPutRequestBody;
import dev.matheusvictor.blogsenna.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;


  @GetMapping
  @Operation(summary = "List all categories")
  @Tag(name = "categories")
  public ResponseEntity<List<Category>> findAll() {
    return ResponseEntity.ok(categoryService.findAll());
  }

  @GetMapping("/pageable")
  @Operation(summary = "List all categories pageable")
  @Tag(name = "categories")
  public ResponseEntity<Page<Category>> findAllPageable(@ParameterObject Pageable pageable) {
    return ResponseEntity.ok(categoryService.findaAllPageble(pageable));
  }

  @GetMapping("/{slug}")
  @Operation(summary = "Find category by slug")
  @Tag(name = "categories")
  public ResponseEntity<Category> findBySlug(@PathVariable String slug) {
    return ResponseEntity.ok(categoryService.findBySlug(slug));
  }

  @PostMapping
  @Operation(summary = "Create a new category")
  @Tag(name = "categories")
  @ApiResponse(responseCode = "201", description = "Created")
  @ApiResponse(responseCode = "400", description = "Bad request")
  public ResponseEntity<Category> create(@Valid @RequestBody CategoryPostRequestBody categoryPostRequestBody) {
    return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(categoryPostRequestBody));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a category by id")
  @Tag(name = "categories")
  public ResponseEntity<Category> update(
          @PathVariable Long id,
          @Valid @RequestBody CategoryPutRequestBody categoryPutRequestBody
  ) {
    return ResponseEntity.ok(categoryService.update(id, categoryPutRequestBody));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a category by id")
  @Tag(name = "categories")
  @ApiResponse(responseCode = "204", description = "No content")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    categoryService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
