package dev.matheusvictor.blogsenna.request.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryPutRequestBody {
  @NotBlank(message = "The category name cannot be blank")
  private String name;

  @NotBlank(message = "The category description cannot be blank")
  private String description;
}
