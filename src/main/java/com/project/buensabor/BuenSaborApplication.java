package com.project.buensabor;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;


@SpringBootApplication
public class BuenSaborApplication {

	public static void main(String[] args) {

		SpringApplication.run(BuenSaborApplication.class, args
		);
	}

	/*@Bean
	public WebMvcConfigurer corsConfigurer () {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("/**")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};

	}*/

}
