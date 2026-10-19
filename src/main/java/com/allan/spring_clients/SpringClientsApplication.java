package com.allan.spring_clients;

import com.allan.spring_clients.album.AlbumClient;
import com.allan.spring_clients.album.AsyncAlbumClient;
import com.allan.spring_clients.album.CompletableFutureAlbumClient;
import com.allan.spring_clients.post.PostClient;
import com.allan.spring_clients.todo.TodoClient;
import com.allan.spring_clients.user.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 *     <td><b>HTTP Interfaces (Sync)</b></td>
 *     <td>{@link com.allan.spring_clients.album.AlbumClient}</td>
 *     <td>{@code @HttpExchange}, {@code @GetExchange}, {@link ImportHttpServices}</td>
 *     <td>Declarative Synchronous Proxy</td>
 *   </tr>
 *   <tr>
 *     <td><b>HTTP Interfaces (Async Reactive)</b></td>
 *     <td>{@link com.allan.spring_clients.album.AsyncAlbumClient}</td>
 *     <td>{@code @HttpExchange}, {@code @GetExchange}, {@link reactor.core.publisher.Flux}</td>
 *     <td>Declarative Reactive Proxy (Flux / Mono)</td>
 *   </tr>
 *   <tr>
 *     <td><b>HTTP Interfaces (Async Future)</b></td>
 *     <td>{@link com.allan.spring_clients.album.CompletableFutureAlbumClient}</td>
 *     <td>{@code @HttpExchange}, {@code @GetExchange}, {@link java.util.concurrent.CompletableFuture}</td>
 *     <td>Declarative Promise Proxy (CompletableFuture)</td>
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
 * @see com.allan.spring_clients.album.AsyncAlbumClient
 * @see com.allan.spring_clients.album.CompletableFutureAlbumClient
 * @see com.allan.spring_clients.post.PostClient
 * @see com.allan.spring_clients.todo.TodoClient
 * @see com.allan.spring_clients.user.UserClient
 * @see com.allan.spring_clients.config.ClientConfig
 */
@SpringBootApplication
@ImportHttpServices(types = AlbumClient.class)
public class SpringClientsApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringClientsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringClientsApplication.class, args);
	}

    /**
     * Startup runner demonstrating HTTP clients across different paradigms:
     * <ul>
     *   <li><b>AlbumClient:</b> HTTP Service Interfaces (Declarative Proxy - Synchronous)</li>
     *   <li><b>AsyncAlbumClient:</b> HTTP Service Interfaces (Declarative Proxy - Reactive Asynchronous)</li>
     *   <li><b>CompletableFutureAlbumClient:</b> HTTP Service Interfaces (Declarative Proxy - Standard Java CompletableFuture)</li>
     *   <li><b>PostClient:</b> RestClient (Modern Fluent Synchronous)</li>
     *   <li><b>TodoClient:</b> RestTemplate (Classic Synchronous Template)</li>
     *   <li><b>UserClient:</b> WebClient (Reactive Asynchronous Stream)</li>
     * </ul>
     *
     * @param albumClient injected declarative synchronous {@link AlbumClient}
     * @param asyncAlbumClient injected declarative reactive {@link AsyncAlbumClient}
     * @param completableFutureAlbumClient injected declarative asynchronous {@link CompletableFutureAlbumClient}
     * @param postClient injected modern fluent {@link PostClient}
     * @param todoClient injected classic {@link TodoClient}
     * @param userClient injected reactive {@link UserClient}
     * @return a {@link CommandLineRunner} executing client demos on application startup
     */
    @Bean
    CommandLineRunner commandLineRunner(
            AlbumClient albumClient,
            AsyncAlbumClient asyncAlbumClient,
            CompletableFutureAlbumClient completableFutureAlbumClient,
            PostClient postClient,
            TodoClient todoClient,
            UserClient userClient
    ) {
        return args -> {
            log.info("========================================================================");
            log.info("           SPRING CLIENTS DEMO SHOWCASE STARTUP RUNNER                  ");
            log.info("========================================================================");

            // 1. HTTP Service Interfaces Showcase - Synchronous (AlbumClient)
            log.info("------------------------------------------------------------------------");
            log.info("1. HTTP SERVICE INTERFACES DEMO (AlbumClient - Declarative @HttpExchange [Sync])");
            log.info("------------------------------------------------------------------------");
            albumClient.findAll().stream().limit(3).forEach(album -> log.info("Album: {}", album));

            // 2. HTTP Service Interfaces Showcase - Asynchronous Reactive (AsyncAlbumClient)
            log.info("------------------------------------------------------------------------");
            log.info("2. ASYNC HTTP INTERFACES DEMO (AsyncAlbumClient - Declarative Reactive Flux)");
            log.info("------------------------------------------------------------------------");
            asyncAlbumClient.findAll().take(3).doOnNext(album -> log.info("Async Album: {}", album)).blockLast();

            // 3. HTTP Service Interfaces Showcase - Asynchronous CompletableFuture (CompletableFutureAlbumClient)
            log.info("------------------------------------------------------------------------");
            log.info("3. ASYNC HTTP INTERFACES DEMO (CompletableFutureAlbumClient - Java CompletableFuture)");
            log.info("------------------------------------------------------------------------");
            completableFutureAlbumClient.findAll()
                    .thenAccept(albums -> albums.stream().limit(3).forEach(album -> log.info("Future Album: {}", album)))
                    .join();

            // 4. RestClient Showcase (PostClient)
            log.info("------------------------------------------------------------------------");
            log.info("4. RESTCLIENT DEMO (PostClient - Modern Fluent Synchronous)");
            log.info("------------------------------------------------------------------------");
            postClient.findAll().stream().limit(3).forEach(post -> log.info("Post: {}", post));

            // 5. RestTemplate Showcase (TodoClient)
            log.info("------------------------------------------------------------------------");
            log.info("5. RESTTEMPLATE DEMO (TodoClient - Classic Synchronous Template)");
            log.info("------------------------------------------------------------------------");
            todoClient.findAll().stream().limit(3).forEach(todo -> log.info("Todo: {}", todo));

            // 6. WebClient Showcase (UserClient)
            log.info("------------------------------------------------------------------------");
            log.info("6. WEBCLIENT DEMO (UserClient - Reactive Asynchronous Stream)");
            log.info("------------------------------------------------------------------------");
            userClient.findAll().take(3).doOnNext(user -> log.info("User: {}", user)).blockLast();

            log.info("========================================================================");
            log.info("           SPRING CLIENTS DEMO SHOWCASE COMPLETED                       ");
            log.info("========================================================================");
        };
    }

}
