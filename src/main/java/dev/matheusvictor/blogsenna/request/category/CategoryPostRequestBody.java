package dev.matheusvictor.blogsenna.request.category;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryPostRequestBody {
  private String name;

  private String description;
}
