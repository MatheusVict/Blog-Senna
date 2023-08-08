package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.domain.category.Category;
import dev.matheusvictor.blogsenna.domain.post.Post;
import dev.matheusvictor.blogsenna.request.category.CategoryPostRequestBody;
import dev.matheusvictor.blogsenna.request.category.CategoryPutRequestBody;
import dev.matheusvictor.blogsenna.request.post.PostPutRequestBody;
import dev.matheusvictor.blogsenna.request.post.PostRequestBody;
import dev.matheusvictor.blogsenna.services.PostService;
import dev.matheusvictor.blogsenna.util.CategoryCreator;
import dev.matheusvictor.blogsenna.util.PostCreator;
import dev.matheusvictor.blogsenna.util.PostPutRequestBodyCreator;
import dev.matheusvictor.blogsenna.util.PostRequestBodyCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PostControllerTest {

  @InjectMocks
  private PostController postController;

  @Mock
  private PostService postService;

  @BeforeEach
  void setUp() {
    PageImpl<Post> postPagePage = new PageImpl<>(List.of(PostCreator.createValidPost()));
    BDDMockito.when(postService.findAllPageable(ArgumentMatchers.any()))
            .thenReturn(postPagePage);

    BDDMockito.when(postService.findAll())
            .thenReturn(List.of(PostCreator.createValidPost()));

    BDDMockito.when(postService.findById(ArgumentMatchers.anyLong()))
            .thenReturn(PostCreator.createValidPost());

    BDDMockito.when(postService.findBySlug(ArgumentMatchers.anyString()))
            .thenReturn(PostCreator.createValidPost());

    BDDMockito.when(postService.create(ArgumentMatchers.any(PostRequestBody.class)))
            .thenReturn(PostCreator.createValidPost());

    BDDMockito.when(postService.update(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(PostPutRequestBody.class)))
            .thenReturn(PostCreator.createValidPost());

    BDDMockito.doNothing().when(postService).delete(ArgumentMatchers.anyLong());
  }

  @Test
  @DisplayName("findAllPageable returns list of posts inside page object when successful")
  public void should_ReturnListOfPostsInsidePageObject_WhenSuccessful() {
    String expectedTitle = PostCreator.createValidPost().getTitle();
    String expectedSlug = PostCreator.createValidPost().getSlug();
    PageImpl<Post> postPagePage = (PageImpl<Post>) postController.findAllPageable(null).getBody();

    assertNotNull(postPagePage);

    assertEquals(expectedTitle, postPagePage.getContent().get(0).getTitle());
    assertEquals(expectedSlug, PostCreator.getSlug(expectedTitle));
  }

  @Test
  @DisplayName("findAll returns list of posts when successful")
  public void should_ReturnListOfPosts_WhenSuccessful() {
    String expectedTitle = PostCreator.createValidPost().getTitle();
    String expectedSlug = PostCreator.createValidPost().getSlug();
    List<Post> posts = postController.findAll().getBody();

    assertNotNull(posts);

    assertEquals(expectedTitle, posts.get(0).getTitle());
    assertEquals(expectedSlug, PostCreator.getSlug(expectedTitle));
  }

  @Test
  @DisplayName("findBySlug returns a post when successful")
  public void should_ReturnPostBySlug_WhenSuccessful() {
    String expectedTitle = PostCreator.createValidPost().getTitle();
    String expectedSlug = PostCreator.createValidPost().getSlug();
    Post post = postController.findBySlug("post-1").getBody();

    assertNotNull(post);

    assertEquals(expectedTitle, post.getTitle());
    assertEquals(expectedSlug, PostCreator.getSlug(expectedTitle));
  }

  @Test
  @DisplayName("findById returns a post when successful")
  public void should_ReturnPostById_WhenSuccessful() {
    Long expectedId = PostCreator.createValidPost().getId();
    String expectedTitle = PostCreator.createValidPost().getTitle();
    String expectedSlug = PostCreator.createValidPost().getSlug();
    Post post = postController.findById(expectedId).getBody();

    assertNotNull(post);

    assertEquals(expectedTitle, post.getTitle());
    assertEquals(expectedSlug, PostCreator.getSlug(expectedTitle));
    assertEquals(expectedId, post.getId());
  }

  @Test
  @DisplayName("create returns a post when successful")
  public void should_SaveAndReturnPost_WhenSuccessful() {
    Long expectedId = PostCreator.createValidPost().getId();
    String expectedTitle = PostCreator.createValidPost().getTitle();
    String expectedSlug = PostCreator.createValidPost().getSlug();
    Post post = postController.create(PostRequestBodyCreator.createPostRequestBody()).getBody();

    assertNotNull(post);
    assertNotNull(post.getId());
    assertEquals(expectedTitle, post.getTitle());
    assertEquals(expectedSlug, PostCreator.getSlug(expectedTitle));
    assertEquals(expectedId, post.getId());
  }

  @Test
  @DisplayName("update returns a post when successful")
  public void should_UpdatesAndReturnPost_WhenSuccessful() {
    Long expectedId = PostCreator.createValidPost().getId();
    String expectedTitle = PostCreator.createValidPost().getTitle();
    String expectedSlug = PostCreator.createValidPost().getSlug();
    Post post = postController.update(expectedId, PostPutRequestBodyCreator.createPostPutRequestBody()).getBody();

    assertNotNull(post);
    assertNotNull(post.getId());
    assertEquals(expectedTitle, post.getTitle());
    assertEquals(expectedSlug, PostCreator.getSlug(expectedTitle));
    assertEquals(expectedId, post.getId());
  }

  @Test
  @DisplayName("delete removes a post when successful")
  public void should_RemovePost_WhenSuccessful() {
    assertDoesNotThrow(() -> postController.delete(1L));
  }

}