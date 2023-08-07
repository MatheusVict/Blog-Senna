package dev.matheusvictor.blogsenna.services;

import dev.matheusvictor.blogsenna.domain.post.Post;
import dev.matheusvictor.blogsenna.request.post.PostPutRequestBody;
import dev.matheusvictor.blogsenna.request.post.PostRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
  Post create(PostRequestBody post);

  List<Post> findAll();

  Page<Post> findAllPageable(Pageable pageable);

  Post findBySlug(String slug);

  Post findById(Long id);

  Post update(Long id, PostPutRequestBody postPutRequestBody);

  void delete(Long id);
}
