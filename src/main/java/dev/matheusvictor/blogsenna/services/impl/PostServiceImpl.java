package dev.matheusvictor.blogsenna.services.impl;

import dev.matheusvictor.blogsenna.domain.post.Post;
import dev.matheusvictor.blogsenna.exception.NotFoundException;
import dev.matheusvictor.blogsenna.mapper.PostMapper;
import dev.matheusvictor.blogsenna.repository.PostRepository;
import dev.matheusvictor.blogsenna.request.post.PostPutRequestBody;
import dev.matheusvictor.blogsenna.request.post.PostRequestBody;
import dev.matheusvictor.blogsenna.services.CategoryService;
import dev.matheusvictor.blogsenna.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  private final CategoryService categoryService;

  @Override
  public Post create(PostRequestBody post) {
    Post postToCreate = mapPostBodyToPost(post);
    postToCreate.setCategory(categoryService.findById(post.getCategoryId()));
    return postRepository.save(postToCreate);
  }

  @Override
  public List<Post> findAll() {
    return postRepository.findAll();
  }

  @Override
  public Page<Post> findAllPageable(Pageable pageable) {
    return postRepository.findAll(pageable);
  }

  @Override
  public Post findBySlug(String slug) {
    return postRepository.findBySlug(slug)
            .orElseThrow(() -> new NotFoundException("Post with slug '\" + slug + \"' not found"));
  }

  @Override
  public Post findById(Long id) {
    return postRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Post not found"));
  }

  @Override
  public Post update(Long id, PostPutRequestBody postPutRequestBody) {
    Post postToUpdate = findById(id);
    Post post = mapPutBodyToPost(postPutRequestBody, postToUpdate.getCategory().getId());
    post.setId(postToUpdate.getId());
    return postRepository.save(post);
  }

  @Override
  public void delete(Long id) {
    Post post = findById(id);
    postRepository.delete(post);
  }


  private Post mapPostBodyToPost(PostRequestBody postRequestBody) {
    Post post = PostMapper.INSTANCE.toPost(postRequestBody);
    post.setSlug(getUniqueSlug(post.getTitle()));
    return post;
  }

  private Post mapPutBodyToPost(PostPutRequestBody postPutRequestBody, Long categoryId) {
    Post post = PostMapper.INSTANCE.toPost(postPutRequestBody);
    post.setSlug(getUniqueSlug(post.getTitle()));
    post.getCategory().setId(categoryId);
    return post;
  }

  private String getUniqueSlug(String title) {
    String baseSlug = getSlug(title);
    String uniqueSlug = baseSlug;
    int suffix = 1;

    while (isSlugAlreadyInUse(uniqueSlug)) {
      uniqueSlug = baseSlug + "-" + suffix++;
    }

    return uniqueSlug;
  }

  private String getSlug(String name) {
    return name.toLowerCase().replace(" ", "-");
  }

  private Boolean isSlugAlreadyInUse(String slug) {
    return postRepository.existsBySlug(slug);
  }
}
