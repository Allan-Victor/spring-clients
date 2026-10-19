package com.allan.spring_clients;

import com.allan.spring_clients.album.AlbumClient;
import com.allan.spring_clients.user.UserClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.service.registry.ImportHttpServices;

/**
 * <h1>Spring Clients Showcase Application</h1>
 *
 * <p>
 * Main Spring Boot entry point demonstrating and bootstrapping multiple Spring HTTP Client approaches.
 * </p>
 *
 * <h2>🗺️ Client Navigation Guide:</h2>
 * <table border="1">
 *   <tr>
 *     <th>Spring HTTP Client</th>
 *     <th>Demonstration File</th>
 *     <th>Key Annotation / Class</th>
 *     <th>Execution Paradigm</th>
 *   </tr>
 *   <tr>
 *     <td><b>HTTP Interfaces</b></td>
 *     <td>{@link com.allan.spring_clients.album.AlbumClient}</td>
 *     <td>{@code @HttpExchange}, {@code @GetExchange}, {@link ImportHttpServices}</td>
 *     <td>Declarative Proxy (Sync or Async)</td>
 *   </tr>
 *   <tr>
 *     <td><b>RestClient</b></td>
 *     <td>{@link com.allan.spring_clients.post.PostClient}</td>
 *     <td>{@link org.springframework.web.client.RestClient}</td>
 *     <td>Modern Synchronous (Fluent)</td>
 *   </tr>
 *   <tr>
 *     <td><b>RestTemplate</b></td>
 *     <td>{@link com.allan.spring_clients.todo.TodoClient}</td>
 *     <td>{@link org.springframework.web.client.RestTemplate}</td>
 *     <td>Legacy Synchronous (Template)</td>
 *   </tr>
 *   <tr>
 *     <td><b>WebClient</b></td>
 *     <td>{@link com.allan.spring_clients.user.UserClient}</td>
 *     <td>{@link org.springframework.web.reactive.function.client.WebClient}</td>
 *     <td>Reactive Asynchronous (Non-blocking)</td>
 *   </tr>
 * </table>
 *
 * <h2>⚙️ Automatic HTTP Interface Registration:</h2>
 * <p>
 * The {@link ImportHttpServices} annotation scans the specified base package ({@code "com.allan"})
 * for interfaces annotated with {@link org.springframework.web.service.annotation.HttpExchange}
 * (such as {@link com.allan.spring_clients.album.AlbumClient}) and automatically registers dynamic HTTP client proxy beans
 * into the Spring application context, removing the need for manual {@code HttpServiceProxyFactory} bean definitions.
 * </p>
 *
 * @see com.allan.spring_clients.album.AlbumClient
 * @see com.allan.spring_clients.post.PostClient
 * @see com.allan.spring_clients.todo.TodoClient
 * @see com.allan.spring_clients.user.UserClient
 * @see com.allan.spring_clients.config.ClientConfig
 */
@SpringBootApplication
@ImportHttpServices(types = AlbumClient.class)
public class SpringClientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClientsApplication.class, args);
	}

    /**
     * Startup runner demonstrating the reactive {@link UserClient}.
     *
     * @param userClient the injected reactive {@link UserClient}
     * @return a {@link CommandLineRunner} that subscribes to the user stream on application startup
     */
    @Bean
    CommandLineRunner commandLineRunner(UserClient userClient) {
        return args -> {
            userClient.findAll().subscribe(System.out::println);
        };
    }

}
