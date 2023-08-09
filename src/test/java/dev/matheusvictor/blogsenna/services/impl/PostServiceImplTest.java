package dev.matheusvictor.blogsenna.services.impl;

import dev.matheusvictor.blogsenna.domain.post.Post;
import dev.matheusvictor.blogsenna.repository.PostRepository;
import dev.matheusvictor.blogsenna.request.post.PostPutRequestBody;
import dev.matheusvictor.blogsenna.util.*;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class PostServiceImplTest {

  @InjectMocks
  private PostServiceImpl postService;

  @Mock
  private PostRepository postRepository;

  @Mock
  private CategoryServiceImpl categoryService;

  @Mock
  private UserServiceImpl userService;


  @BeforeEach
  void setUp() {
    var postCreate = PostCreator.createValidPost();
    postCreate.setCategory(CategoryCreator.createValidCategory());
    postCreate.setUser(UserCreator.createValidUser());
    BDDMockito.when(categoryService.findById(ArgumentMatchers.anyLong()))
            .thenReturn(CategoryCreator.createValidCategory());

    BDDMockito.when(userService.findById(ArgumentMatchers.anyLong()))
            .thenReturn(UserCreator.createValidUser());

    PageImpl<Post> postPagePage = new PageImpl<>(List.of(PostCreator.createValidPost()));
    BDDMockito.when(postRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
            .thenReturn(postPagePage);

    BDDMockito.when(postRepository.findAll())
            .thenReturn(List.of(PostCreator.createValidPost()));

    BDDMockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
            .thenReturn(Optional.of(PostCreator.createValidPost()));

    BDDMockito.when(postRepository.save(ArgumentMatchers.any(Post.class)))
            .thenReturn(postCreate);

    BDDMockito.when(postRepository.findBySlug(ArgumentMatchers.anyString()))
            .thenReturn(Optional.of(PostCreator.createValidPost()));

    BDDMockito.doNothing().when(postRepository).delete(ArgumentMatchers.any(Post.class));
  }

  @Test
  @DisplayName("FindALlPageable should return a post list inside a page object")
  public void should_ReturnPostListInsidePageObject_WhenSuccess() {
    String expectedName = PostCreator.createValidPost().getTitle();

    Page<Post> postPagePage = postService.findAllPageable(PageRequest.of(1, 1));

    assertNotNull(postPagePage);
    assertNotNull(postPagePage.toList());
    assertEquals(postPagePage.toList().get(0).getTitle(), expectedName);
  }

  @Test
  @DisplayName("FindAll should return a post list")
  public void should_ReturnPostList_WhenSuccess() {
    String expectedName = PostCreator.createValidPost().getTitle();
    List<Post> postList = postService.findAll();


    assertNotNull(postList);
    assertNotNull(postList.get(0).getUser());
    assertNotNull(postList.get(0).getCategory());
    assertEquals(postList.get(0).getTitle(), expectedName);
  }

  @Test
  @DisplayName("FindBySlug should return a post from slug")
  public void should_ReturnPostBySlug_WhenSuccess() {
    String expectedSlug = PostCreator.createValidPost().getSlug();

    Post postFound = postService.findBySlug(expectedSlug);

    assertNotNull(postFound);
    assertEquals(postFound.getSlug(), PostCreator.getSlug(postFound.getTitle()));
    assertEquals(postFound.getSlug(), expectedSlug);
  }

  @Test
  @DisplayName("FindById should return a post from Id")
  public void should_ReturnPostById_WhenSuccess() {
    Long expectedId = PostCreator.createValidPost().getId();

    Post postFound = postService.findById(expectedId);


    assertEquals(postFound.getId(), expectedId);
  }

  @Test
  @DisplayName("Create should saves a Post")
  public void should_SavesPost_WhenSuccess() {
    Post postToBeSaved = PostCreator.createPostToBeSaved();
    postToBeSaved.setUser(UserCreator.createValidUser());
    postToBeSaved.setCategory(CategoryCreator.createValidCategory());

    Post postSaved = postService.create(PostRequestBodyCreator.createPostRequestBody());

    assertNotNull(postSaved.getId());
    assertEquals(postToBeSaved.getTitle(), postSaved.getTitle());
    assertEquals(postSaved.getSlug(), PostCreator.getSlug(postToBeSaved.getTitle()));

  }

  @Test
  @DisplayName("Update should update and saves a Post")
  public void should_UpdateAndSavesPost_WhenSuccess() {
    Post postToBeUpdate = PostCreator.createValidPost();

    Post postUpdated = postService
            .update(postToBeUpdate.getId(), PostPutRequestBodyCreator.createPostPutRequestBody());

    assertNotNull(postUpdated);
    assertEquals(postUpdated.getId(), postToBeUpdate.getId());
  }

  @Test
  @DisplayName("Delete should delete a user")
  public void should_DeleteUser_WhenSuccess() {
    Post postToBeDeleted = PostCreator.createValidPost();
    postService.delete(postToBeDeleted.getId());

    verify(postRepository, times(1)).delete(ArgumentMatchers.any(Post.class));
  }
}