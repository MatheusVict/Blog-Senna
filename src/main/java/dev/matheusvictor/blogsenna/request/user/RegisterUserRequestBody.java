package dev.matheusvictor.blogsenna.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserRequestBody {

  @NotBlank(message = "Name cannot be blank")
  @Schema(description = "This is the user's name", example = "Matheus Victor")
  String name;

  @NotBlank(message = "Email cannot be blank")
  @Schema(description = "This is the user's email", example = "user@email.com")
  String email;

  @NotBlank(message = "Password cannot be blank")
  @Schema(description = "This is the user's password", example = "123456")
  String password;
}
