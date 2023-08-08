package dev.matheusvictor.blogsenna.repository;

import dev.matheusvictor.blogsenna.domain.category.Category;
import dev.matheusvictor.blogsenna.util.CategoryCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Category Repository")
class CategoryRepositoryTest {

  @Autowired
  private CategoryRepository categoryRepository;


  @Test
  @DisplayName("Should save a category")
  public void should_SaveCategory_WhenSuccessful() {
    Category categoryToBeSaved = CategoryCreator.createCategoryToBeSaved();
    Category savedCategory = categoryRepository.save(categoryToBeSaved);

    assertNotNull(savedCategory);
    assertNotNull(savedCategory.getId());
    assertEquals(categoryToBeSaved.getName(), savedCategory.getName());
    assertEquals(categoryToBeSaved.getSlug(), savedCategory.getSlug());
    assertEquals(categoryToBeSaved.getDescription(), savedCategory.getDescription());
  }

  @Test
  @DisplayName("Should update a category")
  public void should_UpdateCategory_WhenSuccessful() {
    Category categoryToBeSaved = CategoryCreator.createCategoryToBeSaved();
    Category savedCategory = categoryRepository.save(categoryToBeSaved);

    savedCategory.setName("Category 2");
    savedCategory.setSlug("category-2");
    savedCategory.setDescription("Category 2 description");

    Category updatedCategory = categoryRepository.save(savedCategory);

    assertNotNull(updatedCategory);
    assertNotNull(updatedCategory.getId());
    assertEquals(savedCategory.getName(), updatedCategory.getName());
    assertEquals(savedCategory.getSlug(), updatedCategory.getSlug());
    assertEquals(savedCategory.getDescription(), updatedCategory.getDescription());
  }

  @Test
  @DisplayName("Should delete a category")
  public void should_DeleteCategory_WhenSuccessful() {
    Category categoryToBeSaved = CategoryCreator.createCategoryToBeSaved();
    Category savedCategory = categoryRepository.save(categoryToBeSaved);

    categoryRepository.delete(savedCategory);

    var category = categoryRepository.findById(savedCategory.getId());
    assertTrue(category.isEmpty());
  }

  @Test
  @DisplayName("Should find a category by slug")
  void should_FindACategory_BySlug() {
    Category categoryCreated = CategoryCreator.createCategoryToBeSaved();
    Category savedCategory = categoryRepository.save(categoryCreated);

    String slug = savedCategory.getSlug();

    var category = categoryRepository.findBySlug(slug);
    assertTrue(category.isPresent());
    assertEquals(savedCategory, category.get());
    assertEquals(savedCategory.getSlug(), category.get().getSlug());
  }

}