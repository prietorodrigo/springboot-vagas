package web2.atividadefinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "web2.atividadefinal")
public class AtividadefinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtividadefinalApplication.class, args);
	}

}
