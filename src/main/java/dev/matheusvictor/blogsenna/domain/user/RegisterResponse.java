package dev.matheusvictor.blogsenna.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
public record RegisterResponse(String name, String email, UserRole role) {
}
