package dev.matheusvictor.blogsenna.mapper;

import dev.matheusvictor.blogsenna.domain.category.Category;
import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.request.category.CategoryPostRequestBody;
import dev.matheusvictor.blogsenna.request.category.CategoryPutRequestBody;
import dev.matheusvictor.blogsenna.request.user.RegisterUserRequestBody;
import dev.matheusvictor.blogsenna.request.user.UserPostRequestBody;
import dev.matheusvictor.blogsenna.request.user.UserPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
  public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  public abstract User toCategory(UserPostRequestBody userPostRequestBody);

  public abstract User toCategory(UserPutRequestBody userPutRequestBody);

  public abstract UserPostRequestBody toUserPostRequestBody(RegisterUserRequestBody registerUserRequestBody);
}
