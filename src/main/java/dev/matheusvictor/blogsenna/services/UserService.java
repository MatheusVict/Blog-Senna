package dev.matheusvictor.blogsenna.services;

import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.request.user.UserPostRequestBody;
import dev.matheusvictor.blogsenna.request.user.UserPutRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
  User create(UserPostRequestBody user);

  List<User> findAll();

  Page<User> findAllPageable(Pageable pageable);

  User findById(Long id);

  User update(Long id, UserPutRequestBody user);

  void delete(Long id);
}
