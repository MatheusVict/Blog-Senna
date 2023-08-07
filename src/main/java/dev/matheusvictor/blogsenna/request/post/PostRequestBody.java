package dev.matheusvictor.blogsenna.request.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRequestBody {
  private String title;
  private String description;
  private String content;
  private Long categoryId;
}
