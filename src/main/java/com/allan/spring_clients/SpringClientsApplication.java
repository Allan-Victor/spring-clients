package com.allan.spring_clients;

import com.allan.spring_clients.user.UserClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(basePackages = "com.allan") // step 2 in using httpexchange
public class SpringClientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClientsApplication.class, args);
	}

    @Bean
    CommandLineRunner commandLineRunner(UserClient userClient){
        return args -> {
            userClient.findAll().subscribe(System.out::println);
        };
    }

}
