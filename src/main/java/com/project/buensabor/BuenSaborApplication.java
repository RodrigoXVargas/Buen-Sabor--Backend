package com.project.buensabor;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BuenSaborApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().directory("D:\\Rodri Drive\\Facultad\\Facu Programacion\\2do año\\4to semestre\\Repositorio Buen Sabor\\Buen-Sabor\\").filename("entorno.env").load();


		SpringApplication.run(BuenSaborApplication.class, args
		);
	}


}
