package dev.matheusvictor.blogsenna.request.user;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestBody {
  @NotBlank(message = "The user email cannot be blank")
  @Schema(description = "This is the user's email", example = "user@email.com")
  private String email;

  @NotBlank(message = "The user password cannot be blank")
  @Schema(description = "This is the user's password", example = "123456")
  private String password;

  public LoginRequestBody(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
