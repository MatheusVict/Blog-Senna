package dev.matheusvictor.blogsenna.repository;

import dev.matheusvictor.blogsenna.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  Boolean existsByEmail(String email);

}
