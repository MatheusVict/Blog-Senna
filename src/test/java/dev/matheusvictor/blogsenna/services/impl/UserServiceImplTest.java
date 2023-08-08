package dev.matheusvictor.blogsenna.services.impl;

import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.repository.UserRepository;
import dev.matheusvictor.blogsenna.util.UserCreator;
import dev.matheusvictor.blogsenna.util.UserPostRequestBodyCreator;
import dev.matheusvictor.blogsenna.util.UserPutRequestBodyCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;


  @BeforeEach
  void setUp() {
    PageImpl<User> usersPage = new PageImpl<>(List.of(UserCreator.createValidUser()));
    BDDMockito.when(userRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
            .thenReturn(usersPage);

    BDDMockito.when(userRepository.findAll())
            .thenReturn(List.of(UserCreator.createValidUser()));

    BDDMockito.when(userRepository.findById(ArgumentMatchers.anyLong()))
            .thenReturn(Optional.of(UserCreator.createValidUser()));

    BDDMockito.when(userRepository.save(ArgumentMatchers.any(User.class)))
            .thenReturn(UserCreator.createValidUser());

    BDDMockito.doNothing().when(userRepository).delete(ArgumentMatchers.any(User.class));
  }


  @Test
  @DisplayName("FindALlPageable should return a user list inside a page object")
  public void should_ReturnUserListInsidePageObject_WhenSuccess() {
    String expectedName = UserCreator.createValidUser().getName();

    Page<User> userPagePage = userService.findAllPageable(PageRequest.of(1, 1));

    assertNotNull(userPagePage);
    assertNotNull(userPagePage.toList());
    assertEquals(userPagePage.toList().get(0).getName(), expectedName);
  }

  @Test
  @DisplayName("FindAll should return a user list")
  public void should_ReturnUserList_WhenSuccess() {
    String expectedName = UserCreator.createValidUser().getName();
    List<User> userList = userService.findAll();


    assertNotNull(userList);
    assertTrue(userList.size() > 0);
    assertEquals(userList.get(0).getName(), expectedName);
  }

  @Test
  @DisplayName("FindById should return a user from Id")
  public void should_ReturnUserById_WhenSuccess() {
    Long expectedId = UserCreator.createValidUser().getId();

    User user = userService.findById(expectedId);


    assertEquals(user.getId(), expectedId);
  }

  @Test
  @DisplayName("Create should saves a User")
  public void should_SavesUser_WhenSuccess() {
    User userToBeSaved = UserCreator.createUserToBeSaved();

    User userSaved = userService.create(UserPostRequestBodyCreator.createUserPostRequestBody());

    assertNotNull(userSaved.getId());
    assertEquals(userToBeSaved.getName(), userSaved.getName());
    assertEquals(userSaved.getName(), userToBeSaved.getName());

  }

  @Test
  @DisplayName("Update should update and saves a user")
  public void should_UpdateAndSavesUser_WhenSuccess() {
    User userToBeUpdate = UserCreator.createValidUser();

    User userUpdated = userService
            .update(userToBeUpdate.getId(), UserPutRequestBodyCreator.createUserPutRequestBody());

    assertNotNull(userUpdated);
    assertEquals(userUpdated.getId(), userToBeUpdate.getId());
  }

  @Test
  @DisplayName("Delete should delete a user")
  public void should_DeleteUser_WhenSuccess() {
    User userToBeDeleted = UserCreator.createValidUser();
    userService.delete(userToBeDeleted.getId());

    verify(userRepository, times(1)).delete(ArgumentMatchers.any(User.class));
  }

}