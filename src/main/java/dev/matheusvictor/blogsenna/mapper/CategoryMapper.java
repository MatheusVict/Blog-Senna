package dev.matheusvictor.blogsenna.mapper;

import dev.matheusvictor.blogsenna.domain.category.Category;
import dev.matheusvictor.blogsenna.request.category.CategoryPostRequestBody;
import dev.matheusvictor.blogsenna.request.category.CategoryPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
  public static final CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  public abstract Category toCategory(CategoryPostRequestBody categoryPostRequestBody);

  public abstract Category toCategory(CategoryPutRequestBody categoryPutRequestBody);
}
