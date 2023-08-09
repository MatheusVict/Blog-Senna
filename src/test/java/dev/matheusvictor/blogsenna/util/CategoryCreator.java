package dev.matheusvictor.blogsenna.util;

import dev.matheusvictor.blogsenna.domain.category.Category;

public class CategoryCreator {

  public static Category createCategoryToBeSaved() {
    return Category.builder()
            .name("Category 1")
            .slug("category-1")
            .description("Category 1 description")
            .build();
  }

  public static Category createValidCategory() {
    return Category.builder()
            .id(1L)
            .name("Category 1")
            .slug("category-1")
            .description("Category 1 description")
            .build();
  }

  public static Category createValidUpdatedCategory() {
    return Category.builder()
            .id(1L)
            .name("Category 1")
            .slug("category-1")
            .description("Category 1 description")
            .build();
  }

  public static String getSlug(String name) {
    return name.toLowerCase().replace(" ", "-");
  }

}
