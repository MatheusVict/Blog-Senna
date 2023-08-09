package dev.matheusvictor.blogsenna.util;

import dev.matheusvictor.blogsenna.request.category.CategoryPostRequestBody;

public class CategoryPostRequestBodyCreator {

  public static CategoryPostRequestBody createCategoryPostRequestBody() {
    return CategoryPostRequestBody.builder()
            .name(CategoryCreator.createValidCategory().getName())
            .description(CategoryCreator.createValidCategory().getDescription())
            .build();
  }
}
