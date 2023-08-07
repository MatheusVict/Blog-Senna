package dev.matheusvictor.blogsenna.services.impl;

import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.exception.EmailAlreadyInUseException;
import dev.matheusvictor.blogsenna.exception.NotFoundException;
import dev.matheusvictor.blogsenna.mapper.UserMapper;
import dev.matheusvictor.blogsenna.repository.UserRepository;
import dev.matheusvictor.blogsenna.request.user.UserPostRequestBody;
import dev.matheusvictor.blogsenna.request.user.UserPutRequestBody;
import dev.matheusvictor.blogsenna.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User create(UserPostRequestBody userPostRequestBody) {
    validateUniqueEmail(userPostRequestBody.getEmail());
    User user = mapUserRequestBodyToUser(userPostRequestBody);
    return userRepository.save(user);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Page<User> findAllPageable(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  public User findById(Long id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found"));
  }

  @Override
  public User update(Long id, UserPutRequestBody userPutRequestBody) {
    User user = validateUniqueEmail(userPutRequestBody.getEmail(), id);
    User userMapped = mapUserRequestBodyToUser(userPutRequestBody);
    userMapped.setId(user.getId());
    return userRepository.save(userMapped);
  }

  @Override
  public void delete(Long id) {
    User user = findById(id);
    userRepository.delete(user);
  }

  private User mapUserRequestBodyToUser(UserPostRequestBody userPostRequestBody) {
    userPostRequestBody.setPassword(encryptPassword(userPostRequestBody.getPassword()));
    return UserMapper.INSTANCE.toCategory(userPostRequestBody);
  }

  private User mapUserRequestBodyToUser(UserPutRequestBody userPutRequestBody) {
    userPutRequestBody.setPassword(encryptPassword(userPutRequestBody.getPassword()));
    return UserMapper.INSTANCE.toCategory(userPutRequestBody);
  }

  private String encryptPassword(String password) {
    return new BCryptPasswordEncoder().encode(password);
  }

  private void validateUniqueEmail(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new EmailAlreadyInUseException("Email already in use");
    }
  }

  private User validateUniqueEmail(String email, Long userId) {
    User user = findById(userId);
    if (userRepository.existsByEmail(email)) {
      throw new EmailAlreadyInUseException("Email already in use");
    }
    return user;
  }
}
