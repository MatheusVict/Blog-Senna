package dev.matheusvictor.blogsenna.util;

import dev.matheusvictor.blogsenna.request.category.CategoryPutRequestBody;

public class CategoryPutRequestBodyCreator {
  public static CategoryPutRequestBody createCategoryPutRequestBody() {
    return CategoryPutRequestBody.builder()
            .name(CategoryCreator.createValidCategory().getName())
            .description(CategoryCreator.createValidCategory().getDescription())
            .build();

  }
}
