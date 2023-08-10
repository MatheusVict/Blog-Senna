package dev.matheusvictor.blogsenna.repository;

import dev.matheusvictor.blogsenna.domain.category.Category;
import dev.matheusvictor.blogsenna.domain.post.Post;
import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.util.CategoryCreator;
import dev.matheusvictor.blogsenna.util.PostCreator;
import dev.matheusvictor.blogsenna.util.UserCreator;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for PostRepository")
class PostRepositoryTest {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  public void setup() {
    postRepository.deleteAll();
    categoryRepository.deleteAll();
    userRepository.deleteAll();
  }

  @Test
  @DisplayName("Save persists post when successful")
  public void should_SaveAndPersistPost_WhenSuccessful() {
    Category category = CategoryCreator.createCategoryToBeSaved();
    User user = UserCreator.createUserToBeSaved();

    Category savedCategory = this.categoryRepository.save(category);
    User saveUser = this.userRepository.save(user);

    Post postToBeSaved = PostCreator.createPostToBeSaved();
    postToBeSaved.setCategory(savedCategory);
    postToBeSaved.setUser(saveUser);

    Post postSaved = this.postRepository.save(postToBeSaved);

    assertNotNull(postSaved);
    assertNotNull(postSaved.getId());
    assertEquals(postSaved.getTitle(), postToBeSaved.getTitle());
    assertEquals(postSaved.getCategory(), postToBeSaved.getCategory());
    assertEquals(postSaved.getUser(), postToBeSaved.getUser());
  }

  @Test
  @DisplayName("Save updates post when successful")
  public void should_UpdateAndSavesPost_WhenSuccessful() {
    Category category = CategoryCreator.createCategoryToBeSaved();
    User user = UserCreator.createUserToBeSaved();

    Category saveCategory = this.categoryRepository.save(category);
    User saveUser = this.userRepository.save(user);

    Post postToBeSaved = PostCreator.createPostToBeSaved();
    postToBeSaved.setCategory(saveCategory);
    postToBeSaved.setUser(saveUser);

    Post postSaved = this.postRepository.save(postToBeSaved);

    postSaved.setTitle("Post 2");
    postSaved.setSlug("post-2");
    postSaved.setContent("Post 2 content");
    postSaved.setDescription("Post 2 description");

    Post postUpdated = this.postRepository.save(postSaved);

    assertNotNull(postUpdated);
    assertNotNull(postUpdated.getId());
    assertEquals(postUpdated.getTitle(), postSaved.getTitle());
    assertEquals(postUpdated.getCategory(), postSaved.getCategory());
    assertEquals(postUpdated.getUser(), postSaved.getUser());
  }

  @Test
  @DisplayName("Delete removes post when successful")
  public void should_RemovePost_WhenSuccessful() {
    Category category = CategoryCreator.createCategoryToBeSaved();
    User user = UserCreator.createUserToBeSaved();

    Category saveCategory = this.categoryRepository.save(category);
    User saveUser = this.userRepository.save(user);

    Post postToBeSaved = PostCreator.createPostToBeSaved();
    postToBeSaved.setCategory(saveCategory);
    postToBeSaved.setUser(saveUser);

    Post postSaved = this.postRepository.save(postToBeSaved);

    this.postRepository.delete(postSaved);

    assertTrue(this.postRepository.findAll().isEmpty());
  }

  @Test
  @DisplayName("Find by slug returns post when successful")
  public void should_ReturnPost_WhenSuccessful() {
    Category category = CategoryCreator.createCategoryToBeSaved();
    User user = UserCreator.createUserToBeSaved();

    Category saveCategory = this.categoryRepository.save(category);
    User saveUser = this.userRepository.save(user);

    Post postToBeSaved = PostCreator.createPostToBeSaved();
    postToBeSaved.setCategory(saveCategory);
    postToBeSaved.setUser(saveUser);

    Post postSaved = this.postRepository.save(postToBeSaved);

    Post post = this.postRepository.findBySlug(postSaved.getSlug()).orElse(null);

    assertNotNull(post);
    assertNotNull(post.getId());
    assertEquals(post.getTitle(), postSaved.getTitle());
    assertEquals(post.getCategory(), postSaved.getCategory());
    assertEquals(post.getUser(), postSaved.getUser());
    assertEquals(post.getSlug(), postSaved.getSlug());
  }

  @Test
  @DisplayName("FindById returns post when successful")
  public void should_ReturnPostId_WhenSuccessful() {
    Category category = CategoryCreator.createCategoryToBeSaved();
    User user = UserCreator.createUserToBeSaved();

    Category saveCategory = this.categoryRepository.save(category);
    User saveUser = this.userRepository.save(user);

    Post postToBeSaved = PostCreator.createPostToBeSaved();
    postToBeSaved.setCategory(saveCategory);
    postToBeSaved.setUser(saveUser);

    Post postSaved = this.postRepository.save(postToBeSaved);

    Post post = this.postRepository.findById(postSaved.getId()).orElse(null);

    assertNotNull(post);
    assertNotNull(post.getId());
    assertEquals(post.getTitle(), postSaved.getTitle());
    assertEquals(post.getCategory(), postSaved.getCategory());
    assertEquals(post.getUser(), postSaved.getUser());
    assertEquals(post.getSlug(), postSaved.getSlug());
  }

  @Test
  @DisplayName("Exists by slug returns true when successful")
  public void should_ReturnTrue_WhenSuccessful() {
    Category category = CategoryCreator.createCategoryToBeSaved();
    User user = UserCreator.createUserToBeSaved();

    Category saveCategory = this.categoryRepository.save(category);
    User saveUser = this.userRepository.save(user);

    Post postToBeSaved = PostCreator.createPostToBeSaved();
    postToBeSaved.setCategory(saveCategory);
    postToBeSaved.setUser(saveUser);

    Post postSaved = this.postRepository.save(postToBeSaved);

    boolean exists = this.postRepository.existsBySlug(postSaved.getSlug());

    assertTrue(exists);
  }
  @Test
  @DisplayName("Find by category slug returns true when successful")
  public void should_ReturnListOfPostByCategorySlug_WhenSuccessful() {
    Category category = CategoryCreator.createCategoryToBeSaved();
    User user = UserCreator.createUserToBeSaved();

    Category saveCategory = this.categoryRepository.save(category);
    User saveUser = this.userRepository.save(user);

    Post postToBeSaved = PostCreator.createPostToBeSaved();
    postToBeSaved.setCategory(saveCategory);
    postToBeSaved.setUser(saveUser);

    Post postSaved = this.postRepository.save(postToBeSaved);

    List<Post> byCategorySlug = this.postRepository.findByCategorySlug(postSaved.getCategory().getSlug());

    String expectedCategorySlug = postSaved.getCategory().getSlug();

    assertNotNull(byCategorySlug);
    assertFalse(byCategorySlug.isEmpty());
    assertEquals(byCategorySlug.get(0).getCategory().getSlug(), expectedCategorySlug);
  }

}