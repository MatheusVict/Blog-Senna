package dev.matheusvictor.blogsenna.services.impl;

import dev.matheusvictor.blogsenna.domain.category.Category;
import dev.matheusvictor.blogsenna.repository.CategoryRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {

  @InjectMocks
  private CategoryServiceImpl categoryService;

  @Mock
  private CategoryRepository categoryRepository;


  @BeforeEach
  void setUp() {
    PageImpl<Category> categoriesPage = new PageImpl<>(List.of(CategoryCreator.createValidCategory()));
    BDDMockito.when(categoryRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
            .thenReturn(categoriesPage);

    BDDMockito.when(categoryRepository.findAll())
            .thenReturn(List.of(CategoryCreator.createValidCategory()));

    BDDMockito.when(categoryRepository.findById(ArgumentMatchers.anyLong()))
            .thenReturn(Optional.of(CategoryCreator.createValidCategory()));

    BDDMockito.when(categoryRepository.findBySlug(ArgumentMatchers.anyString()))
            .thenReturn(Optional.of(CategoryCreator.createValidCategory()));

    BDDMockito.when(categoryRepository.save(ArgumentMatchers.any(Category.class)))
            .thenReturn(CategoryCreator.createValidCategory());

    BDDMockito.doNothing().when(categoryRepository).delete(ArgumentMatchers.any(Category.class));
  }


  @Test
  @DisplayName("FindALlPageable should return a category list inside a page object")
  public void should_ReturnCategoryListInsidePageObject_WhenSuccess() {
    String expectedName = CategoryCreator.createValidCategory().getName();

    Page<Category> categoryPage = categoryService.findaAllPageble(PageRequest.of(1, 1));

    assertNotNull(categoryPage);
    assertNotNull(categoryPage.toList());
    assertEquals(categoryPage.toList().get(0).getName(), expectedName);
  }

  @Test
  @DisplayName("FindAll should return a category list")
  public void should_ReturnCategoryList_WhenSuccess() {
    String expectedName = CategoryCreator.createValidCategory().getName();
    List<Category> categoryList = categoryService.findAll();


    assertNotNull(categoryList);
    assertTrue(categoryList.size() > 0);
    assertEquals(categoryList.get(0).getName(), expectedName);
  }

  @Test
  @DisplayName("FindBySlug should return a category from slug")
  public void should_ReturnCategoryBySlug_WhenSuccess() {
    String expectedSlug = CategoryCreator.createValidCategory().getSlug();

    Category categoryFound = categoryService.findBySlug("category-1");

    assertNotNull(categoryFound);
    assertEquals(categoryFound.getSlug(), CategoryCreator.getSlug(categoryFound.getName()));
    assertEquals(categoryFound.getSlug(), expectedSlug);
  }

  @Test
  @DisplayName("FindById should return a category from Id")
  public void should_ReturnCategoryById_WhenSuccess() {
    Long expectedId = CategoryCreator.createValidCategory().getId();

    Category category = categoryService.findById(expectedId);


    assertEquals(category.getId(), expectedId);
  }

  @Test
  @DisplayName("Create should saves a Category")
  public void should_SavesCategory_WhenSuccess() {
    Category categoryToBeSaved = CategoryCreator.createCategoryToBeSaved();

    Category categorySaved = categoryService.create(CategoryPostRequestBodyCreator.createCategoryPostRequestBody());

    assertNotNull(categorySaved.getId());
    assertEquals(categoryToBeSaved.getName(), categorySaved.getName());
    assertEquals(categorySaved.getSlug(), CategoryCreator.getSlug(categoryToBeSaved.getName()));

  }

  @Test
  @DisplayName("Update should update and saves a category")
  public void should_UpdateAndSavesCategory_WhenSuccess() {
    Category categoryToBeUpdate = CategoryCreator.createValidUpdatedCategory();

    Category categoryUpdated = categoryService
            .update(categoryToBeUpdate.getId(), CategoryPutRequestBodyCreator.createCategoryPutRequestBody());

    assertNotNull(categoryUpdated);
    assertEquals(categoryUpdated.getId(), categoryToBeUpdate.getId());
  }

  @Test
  @DisplayName("Delete should delete a category")
  public void should_DeleteCategory_WhenSuccess() {
    Category categoryToBeDeleted = CategoryCreator.createValidCategory();
    categoryService.delete(categoryToBeDeleted.getId());

    verify(categoryRepository, times(1)).delete(ArgumentMatchers.any(Category.class));
  }

}