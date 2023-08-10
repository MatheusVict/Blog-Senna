package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.domain.post.Post;
import dev.matheusvictor.blogsenna.request.post.PostPutRequestBody;
import dev.matheusvictor.blogsenna.request.post.PostRequestBody;
import dev.matheusvictor.blogsenna.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

  private final PostService postService;

  @GetMapping
  @Operation(summary = "List all posts", description = "List all posts in database")
  @Tag(name = "posts")
  public ResponseEntity<List<Post>> findAll() {
    return ResponseEntity.ok(postService.findAll());
  }

  @GetMapping("/pageable")
  @Operation(summary = "List all posts pageable", description = "List all posts in database pageable, the default size is 20")
  @Tag(name = "posts")
  public ResponseEntity<Page<Post>> findAllPageable(@ParameterObject Pageable pageable) {
    return ResponseEntity.ok(postService.findAllPageable(pageable));
  }

  @GetMapping("/slug/{slug}")
  @Operation(summary = "Find post by slug", description = "Find post by slug in database")
  @Tag(name = "posts")
  public ResponseEntity<Post> findBySlug(@PathVariable String slug) {
    return ResponseEntity.ok(postService.findBySlug(slug));
  }

  @GetMapping("/byCategory")
  @Operation(summary = "Find all post by category slug", description = "Find all post by category slug in database")
  @Tag(name = "posts")
  public ResponseEntity<List<Post>> findByCategorySlug(@RequestParam(name = "slug") String slug) {
    return ResponseEntity.ok(postService.findAllPostsByCategorySlug(slug));
  }

  @GetMapping("/id/{id}")
  @Operation(summary = "Find post by id")
  @Tag(name = "posts")
  public ResponseEntity<Post> findById(@PathVariable Long id) {
    return ResponseEntity.ok(postService.findById(id));
  }

  @PostMapping
  @Operation(summary = "Create a new post")
  @Tag(name = "posts")
  @ApiResponse(responseCode = "201", description = "Created")
  @ApiResponse(responseCode = "400", description = "Bad request")
  public ResponseEntity<Post> create(@Valid @RequestBody PostRequestBody postRequestBody) {
    return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(postRequestBody));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a post")
  @Tag(name = "posts")
  public ResponseEntity<Post> update(
          @PathVariable Long id,
          @Valid @RequestBody PostPutRequestBody postPutRequestBody
  ) {
    return ResponseEntity.ok(postService.update(id, postPutRequestBody));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a post")
  @Tag(name = "posts")
  @ApiResponse(responseCode = "204", description = "No content")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    postService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
