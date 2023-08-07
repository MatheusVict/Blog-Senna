package dev.matheusvictor.blogsenna.request.user;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestBody {
  private String email;
  private String password;
}
