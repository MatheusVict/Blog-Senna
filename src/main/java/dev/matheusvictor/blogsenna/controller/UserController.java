package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.request.user.UserPostRequestBody;
import dev.matheusvictor.blogsenna.request.user.UserPutRequestBody;
import dev.matheusvictor.blogsenna.services.UserService;
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
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  @Operation(summary = "List all users", description = "List all users in database")
  @Tag(name = "users")
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/pageable")
  @Operation(summary = "List all users pageable", description = "List all users in database pageable, the default size is 20")
  @Tag(name = "users")
  public ResponseEntity<Page<User>> findAllPageable(@ParameterObject Pageable pageable) {
    return ResponseEntity.ok(userService.findAllPageable(pageable));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Find user by id")
  @Tag(name = "users")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @PostMapping
  @Operation(summary = "Create a new user")
  @Tag(name = "users")
  @ApiResponse(responseCode = "201", description = "Created")
  @ApiResponse(responseCode = "400", description = "Bad request")
  public ResponseEntity<User> create(@Valid @RequestBody UserPostRequestBody userPostRequestBody) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userPostRequestBody));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a user")
  @Tag(name = "users")
  public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody UserPutRequestBody userPutRequestBody) {
    return ResponseEntity.ok(userService.update(id, userPutRequestBody));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a user")
  @Tag(name = "users")
  @ApiResponse(responseCode = "204", description = "No content")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
