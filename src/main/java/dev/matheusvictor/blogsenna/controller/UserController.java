package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.request.user.UserPutRequestBody;
import dev.matheusvictor.blogsenna.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/pageable")
  public ResponseEntity<Page<User>> findAllPageable(Pageable pageable) {
    return ResponseEntity.ok(userService.findAllPageable(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> update(Long id, UserPutRequestBody userPutRequestBody) {
    return ResponseEntity.ok(userService.update(id, userPutRequestBody));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
