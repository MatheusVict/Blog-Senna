package dev.matheusvictor.blogsenna.mapper;

import dev.matheusvictor.blogsenna.domain.post.Post;
import dev.matheusvictor.blogsenna.request.post.PostPutRequestBody;
import dev.matheusvictor.blogsenna.request.post.PostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

  public static final PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

  public abstract Post toPost(PostRequestBody categoryPostRequestBody);

  public abstract Post toPost(PostPutRequestBody categoryPutRequestBody);
}
