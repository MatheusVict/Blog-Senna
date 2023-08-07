package dev.matheusvictor.blogsenna.request.user;

import dev.matheusvictor.blogsenna.domain.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPutRequestBody {
  @NotBlank(message = "Name cannot be blank")
  private String name;

  @Email(message = "Email must be valid")
  private String email;

  @NotBlank(message = "Password cannot be blank")
  private String password;

  private UserRole role;
}
