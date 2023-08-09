package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.domain.category.Category;
import dev.matheusvictor.blogsenna.request.category.CategoryPostRequestBody;
import dev.matheusvictor.blogsenna.request.category.CategoryPutRequestBody;
import dev.matheusvictor.blogsenna.services.CategoryService;
import dev.matheusvictor.blogsenna.util.CategoryCreator;
import dev.matheusvictor.blogsenna.util.CategoryPostRequestBodyCreator;
import dev.matheusvictor.blogsenna.util.CategoryPutRequestBodyCreator;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CategoryControllerTest {

  @InjectMocks
  private CategoryController categoryController;

  @Mock
  private CategoryService categoryService;

  @BeforeEach
  void setUp() {
    PageImpl<Category> categoriesPage = new PageImpl<>(List.of(CategoryCreator.createValidCategory()));
    BDDMockito.when(categoryService.findaAllPageble(ArgumentMatchers.any()))
            .thenReturn(categoriesPage);

    BDDMockito.when(categoryService.findAll())
            .thenReturn(List.of(CategoryCreator.createValidCategory()));

    BDDMockito.when(categoryService.findById(ArgumentMatchers.anyLong()))
            .thenReturn(CategoryCreator.createValidCategory());

    BDDMockito.when(categoryService.findBySlug(ArgumentMatchers.anyString()))
            .thenReturn(CategoryCreator.createValidCategory());

    BDDMockito.when(categoryService.create(ArgumentMatchers.any(CategoryPostRequestBody.class)))
            .thenReturn(CategoryCreator.createValidCategory());

    BDDMockito.when(categoryService.update(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(CategoryPutRequestBody.class)))
            .thenReturn(CategoryCreator.createValidCategory());

    BDDMockito.doNothing().when(categoryService).delete(ArgumentMatchers.anyLong());
  }

  @Test
  @DisplayName("findAllPageable returns list of categories inside page object when successful")
  public void should_ReturnListOfCategoriesInsidePageObject_When_Successful() {
    String expectedName = CategoryCreator.createValidCategory().getName();

    Page<Category> categories = categoryController.findAllPageable(null).getBody();

    assertNotNull(categories);
    assertFalse(categories.isEmpty());
    assertEquals(expectedName, categories.toList().get(0).getName());
  }

  @Test
  @DisplayName("findAll returns list of categories when successful")
  public void should_ReturnListOfCategories_When_Successful() {
    String expectedName = CategoryCreator.createValidCategory().getName();

    List<Category> categories = categoryController.findAll().getBody();

    assertNotNull(categories);
    assertFalse(categories.isEmpty());
    assertEquals(expectedName, categories.get(0).getName());
  }

  @Test
  @DisplayName("findBySlug returns category when successful")
  public void should_ReturnCategoryBySlug_When_Successful() {
    String expectedName = CategoryCreator.createValidCategory().getName();

    Category category = categoryController.findBySlug("slug").getBody();

    assertNotNull(category);
    assertEquals(expectedName, category.getName());
  }

  @Test
  @DisplayName("create returns category when successful")
  public void should_SavesAndReturnCategory_When_Successful() {
    String expectedName = CategoryCreator.createValidCategory().getName();

    Category category = categoryController.create(CategoryPostRequestBodyCreator.createCategoryPostRequestBody()).getBody();

    assertNotNull(category);
    assertNotNull(category.getSlug());
    assertEquals(expectedName, category.getName());
    assertEquals(category.getSlug(), CategoryCreator.getSlug(category.getName()));
  }

  @Test
  @DisplayName("update returns category when successful")
  public void should_UpdateAndReturnCategory_When_Successful() {
    String expectedName = CategoryCreator.createValidCategory().getName();

    Category category = categoryController
            .update(1L, CategoryPutRequestBodyCreator.createCategoryPutRequestBody()).getBody();

    assertNotNull(category);
    assertNotNull(category.getSlug());
    assertEquals(expectedName, category.getName());
    assertEquals(category.getSlug(), CategoryCreator.getSlug(category.getName()));
  }

  @Test
  @DisplayName("delete removes category when successful")
  public void should_RemoveCategory_When_Successful() {
    assertDoesNotThrow(() -> categoryController.delete(1L));
  }

}