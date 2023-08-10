package dev.matheusvictor.blogsenna.infra.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfigurations {

  private final SecurityFilter securityFilter;

  private final String[] AUTH_SWAGGER_WHITELIST = {
          "/swagger-ui/**",
          "/swagger-ui",
          "/swagger-resources/**",
          "/webjars/**",
          "/v3/api-docs/**",
          "/swagger-ui.html"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                    .requestMatchers(HttpMethod.GET, "/posts/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                    .requestMatchers(AUTH_SWAGGER_WHITELIST).permitAll()
                    .requestMatchers(HttpMethod.POST, "/posts").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/posts/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/posts/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/categories").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
          AuthenticationConfiguration authenticationConfiguration
  ) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
