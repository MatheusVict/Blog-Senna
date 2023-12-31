package dev.matheusvictor.blogsenna.repository;

import dev.matheusvictor.blogsenna.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
  UserDetails findByEmail(String email);

  Boolean existsByEmail(String email);

}
