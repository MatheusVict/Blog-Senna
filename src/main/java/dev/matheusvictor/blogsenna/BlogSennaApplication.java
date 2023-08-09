package dev.matheusvictor.blogsenna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class BlogSennaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogSennaApplication.class, args);
	}

}
