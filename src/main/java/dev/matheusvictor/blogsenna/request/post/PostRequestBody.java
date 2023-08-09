package dev.matheusvictor.blogsenna.request.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRequestBody {
  @NotBlank(message = "The post title cannot be blank")
  @Schema(description = "This is the post's title", example = "My first post")
  private String title;

  @NotBlank(message = "The post description cannot be blank")
  @Schema(description = "This is the post's description", example = "This is my first post")
  private String description;

  @NotBlank(message = "The post content cannot be blank")
  @Schema(description = "This is the post's content", example = "This is my first post content")
  private String content;

  @NotNull(message = "The post category id cannot be blank")
  @Schema(description = "This is the post's category id", example = "1")
  private Long categoryId;

  @NotNull(message = "The post user id cannot be blank")
  @Schema(description = "This is the post's user id", example = "1")
  private Long userId;
}
