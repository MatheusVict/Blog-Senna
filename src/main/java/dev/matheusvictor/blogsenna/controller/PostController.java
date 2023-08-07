package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.domain.post.Post;
import dev.matheusvictor.blogsenna.request.post.PostPutRequestBody;
import dev.matheusvictor.blogsenna.request.post.PostRequestBody;
import dev.matheusvictor.blogsenna.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

  private final PostService postService;

  @GetMapping
  public ResponseEntity<List<Post>> findAll() {
    return ResponseEntity.ok(postService.findAll());
  }

  @GetMapping("/pageable")
  public ResponseEntity<Page<Post>> findAllPageable(Pageable pageable) {
    return ResponseEntity.ok(postService.findAllPageable(pageable));
  }

  @GetMapping("/slug/{slug}")
  public ResponseEntity<Post> findBySlug(@PathVariable String slug) {
    return ResponseEntity.ok(postService.findBySlug(slug));
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<Post> findById(@PathVariable Long id) {
    return ResponseEntity.ok(postService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Post> create(@RequestBody PostRequestBody postRequestBody) {
    return ResponseEntity.ok(postService.create(postRequestBody));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Post> update(
          @PathVariable Long id,
          @RequestBody PostPutRequestBody postPutRequestBody
  ) {
    return ResponseEntity.ok(postService.update(id, postPutRequestBody));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    postService.delete(id);
    return ResponseEntity.ok().build();
  }
}
