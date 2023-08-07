package dev.matheusvictor.blogsenna.request.user;

import dev.matheusvictor.blogsenna.domain.user.UserRole;
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
  private String name;

  @Email(message = "Email must be valid")
  private String email;

  @NotBlank(message = "Password cannot be blank")
  private String password;

  @NotNull(message = "Role cannot be blank")
  private UserRole role;
}
