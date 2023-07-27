package curso.api.rest.curso_spring_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableJpaRepositories(basePackages = "curso.api.rest.repository")
@ComponentScan(basePackages = {"curso.*"})  // procura o controller , repository ... dentro de curso.* , nao o Padrao apenas dentro de (curso_spring_rest_api)
@EntityScan(basePackages = {"curso.api.rest.model"}) // Cria as Tabelas Automaticamente
@EnableTransactionManagement
@EnableWebMvc
@RestController
@SpringBootApplication
public class CursoSpringRestApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(CursoSpringRestApiApplication.class, args);
	}

}
