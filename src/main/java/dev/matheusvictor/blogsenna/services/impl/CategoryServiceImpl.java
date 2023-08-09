package dev.matheusvictor.blogsenna.services.impl;

import dev.matheusvictor.blogsenna.domain.category.Category;
import dev.matheusvictor.blogsenna.exception.BadRequestException;
import dev.matheusvictor.blogsenna.repository.CategoryRepository;
import dev.matheusvictor.blogsenna.request.category.CategoryPostRequestBody;
import dev.matheusvictor.blogsenna.request.category.CategoryPutRequestBody;
import dev.matheusvictor.blogsenna.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Category create(CategoryPostRequestBody categoryPostRequestBody) {
    Category category = mapPostBodyToCategory(categoryPostRequestBody);
    return categoryRepository.save(category);
  }

  @Override
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Page<Category> findaAllPageble(Pageable pageable) {
    return categoryRepository.findAll(pageable);
  }

  @Override
  public Category findBySlug(String slug) {
    return categoryRepository.findBySlug(slug)
            .orElseThrow(() -> new BadRequestException("Category not found"));
  }

  @Override
  public Category findById(Long id) {
    return categoryRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Category not found"));
  }

  @Override
  public Category update(Long id, CategoryPutRequestBody categoryPutRequestBody) {
    Category categoryToUpdate = findById(id);
    Category category = mapPutBodyToCategory(categoryPutRequestBody);
    category.setId(categoryToUpdate.getId());
    return categoryRepository.save(category);
  }

  @Override
  public void delete(Long id) {
    Category category = findById(id);
    categoryRepository.delete(category);
  }

  private String getSlug(String name) {
    return name.toLowerCase().replace(" ", "-");
  }

  private Category mapPostBodyToCategory(CategoryPostRequestBody categoryPostRequestBody) {
    Category category = new Category();
    category.setName(categoryPostRequestBody.getName());
    category.setDescription(categoryPostRequestBody.getDescription());
    category.setSlug(getSlug(categoryPostRequestBody.getName()));
    return category;
  }

  private Category mapPutBodyToCategory(CategoryPutRequestBody categoryPutRequestBody) {
    Category category = new Category();
    category.setName(categoryPutRequestBody.getName());
    category.setDescription(categoryPutRequestBody.getDescription());
    category.setSlug(getSlug(categoryPutRequestBody.getName()));
    return category;
  }
}
