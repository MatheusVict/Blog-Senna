package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.request.user.UserPostRequestBody;
import dev.matheusvictor.blogsenna.request.user.UserPutRequestBody;
import dev.matheusvictor.blogsenna.services.UserService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

  @InjectMocks
  private UserController userController;

  @Mock
  private UserService userService;

  @BeforeEach
  void setUp() {
    PageImpl<User> userPage = new PageImpl<>(List.of(UserCreator.createValidUser()));
    BDDMockito.when(userService.findAllPageable(ArgumentMatchers.any()))
            .thenReturn(userPage);
    BDDMockito.when(userService.findAll())
            .thenReturn(List.of(UserCreator.createValidUser()));

    BDDMockito.when(userService.findById(ArgumentMatchers.anyLong()))
            .thenReturn(UserCreator.createValidUser());

    BDDMockito.when(userService.create(ArgumentMatchers.any(UserPostRequestBody.class)))
            .thenReturn(UserCreator.createValidUser());

    BDDMockito.when(userService.update(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(UserPutRequestBody.class)))
            .thenReturn(UserCreator.createValidUser());

    BDDMockito.doNothing().when(userService).delete(ArgumentMatchers.anyLong());
  }


  @Test
  @DisplayName("findAll returns list of user when successful")
  public void should_ReturnListOfUser_When_Successful() {
    String expectedName = UserCreator.createValidUser().getName();
    List<User> users = userController.findAll().getBody();

    assertNotNull(users);
    assertEquals(expectedName, users.get(0).getName());
  }

  @Test
  @DisplayName("findAll returns list of user inside page object when successful")
  public void should_ReturnListOfUserInsidePageObject_When_Successful() {
    String expectedName = UserCreator.createValidUser().getName();
    Page<User> userPage = userController.findAllPageable(null).getBody();

    assertNotNull(userPage);
    assertNotNull(userPage.toList());
    assertEquals(expectedName, userPage.toList().get(0).getName());
  }

  @Test
  @DisplayName("findById returns user when successful")
  public void should_ReturnUser_When_Successful() {
    Long expectedId = UserCreator.createValidUser().getId();
    User user = userController.findById(1L).getBody();

    assertNotNull(user);
    assertNotNull(user.getId());
    assertEquals(expectedId, user.getId());
  }

  @Test
  @DisplayName("create returns user when successful")
  public void should_SavesUser_When_Successful() {
    Long expectedId = UserCreator.createValidUser().getId();
    User user = userController.create(UserPostRequestBodyCreator.createUserPostRequestBody()).getBody();

    assertNotNull(user);
    assertNotNull(user.getId());
    assertEquals(expectedId, user.getId());
  }

  @Test
  @DisplayName("update returns user when successful")
  public void should_UpdateUser_When_Successful() {
    Long expectedId = UserCreator.createValidUser().getId();
    User user = userController.update(1L, UserPutRequestBodyCreator.createUserPutRequestBody()).getBody();

    assertNotNull(user);
    assertNotNull(user.getId());
    assertEquals(expectedId, user.getId());
  }

  @Test
  @DisplayName("delete removes user when successful")
  public void should_RemoveUser_When_Successful() {
    ResponseEntity<Void> entity = userController.delete(1L);


    assertNotNull(entity.getStatusCode());
    assertTrue(entity.getStatusCode().is2xxSuccessful());
  }

}