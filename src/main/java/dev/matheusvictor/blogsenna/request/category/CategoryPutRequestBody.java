package dev.matheusvictor.blogsenna.request.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryPutRequestBody {
  @NotBlank(message = "The category name cannot be blank")
  @Schema(description = "This is the category's name", example = "My first category")
  private String name;

  @NotBlank(message = "The category description cannot be blank")
  @Schema(description = "This is the category's description", example = "This is my first category")
  private String description;
}
