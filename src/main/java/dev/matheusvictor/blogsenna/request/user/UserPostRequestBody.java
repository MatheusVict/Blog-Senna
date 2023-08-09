package dev.matheusvictor.blogsenna.request.user;

import dev.matheusvictor.blogsenna.domain.user.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPostRequestBody {

  @NotBlank(message = "Name cannot be blank")
  @Schema(description = "This is the user's name", example = "Matheus Victor")
  private String name;

  @Email(message = "Email must be valid")
  @Schema(description = "This is the user's email", example = "user@email.com", required = true)
  private String email;

  @NotBlank(message = "Password cannot be blank")
  @Schema(description = "This is the user's password", example = "123456")
  private String password;

  @NotNull(message = "Role cannot be blank")
  @Schema(description = "This is the user's role", example = "ADMIN")
  private UserRole role;
}
