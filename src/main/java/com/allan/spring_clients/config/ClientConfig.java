package com.allan.spring_clients.config;

import com.allan.spring_clients.album.AlbumClient;
import com.allan.spring_clients.album.AsyncAlbumClient;
import com.allan.spring_clients.album.CompletableFutureAlbumClient;
import com.allan.spring_clients.sse.SseClient;
import java.util.concurrent.CompletableFuture;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * <h1>Central Spring Client Configuration</h1>
 *
 * <p>
 * This configuration class defines the Spring beans and setup mechanisms for the different HTTP client
 * technologies used in this project.
 * </p>
 *
 * <h2>📋 Summary of Clients Configured:</h2>
 * <table border="1">
 *   <tr>
 *     <th>Client</th>
 *     <th>Demonstration File</th>
 *     <th>Configuration Method</th>
 *     <th>Type</th>
 *   </tr>
 *   <tr>
 *     <td><b>WebClient</b></td>
 *     <td>{@link com.allan.spring_clients.user.UserClient}</td>
 *     <td>{@link #webClient(WebClient.Builder)}</td>
 *     <td>Reactive (Non-blocking)</td>
 *   </tr>
 *   <tr>
 *     <td><b>RestClient</b></td>
 *     <td>{@link com.allan.spring_clients.post.PostClient}</td>
 *     <td>{@link #restClient(RestClient.Builder)}</td>
 *     <td>Modern Synchronous (Fluent)</td>
 *   </tr>
 *   <tr>
 *     <td><b>HTTP Interfaces (Sync)</b></td>
 *     <td>{@link com.allan.spring_clients.album.AlbumClient}</td>
 *     <td>Auto-registered via {@code @ImportHttpServices} on {@link com.allan.spring_clients.SpringClientsApplication}</td>
 *     <td>Declarative Synchronous Proxy</td>
 *   </tr>
 *   <tr>
 *     <td><b>HTTP Interfaces (Async Reactive)</b></td>
 *     <td>{@link com.allan.spring_clients.album.AsyncAlbumClient}</td>
 *     <td>{@link #asyncAlbumClient(WebClient)} (backed by {@link WebClientAdapter})</td>
 *     <td>Declarative Reactive Proxy (Flux / Mono)</td>
 *   </tr>
 *   <tr>
 *     <td><b>HTTP Interfaces (Async CompletableFuture)</b></td>
 *     <td>{@link com.allan.spring_clients.album.CompletableFutureAlbumClient}</td>
 *     <td>{@link #completableFutureAlbumClient(WebClient)} (backed by {@link WebClientAdapter})</td>
 *     <td>Declarative Java Promise Proxy (CompletableFuture)</td>
 *   </tr>
 *   <tr>
 *     <td><b>Server-Sent Events (SSE) Client</b></td>
 *     <td>{@link com.allan.spring_clients.sse.SseClient}</td>
 *     <td>Annotated with {@link org.springframework.stereotype.Component}, autowires {@link WebClient}</td>
 *     <td>Streaming Real-Time Client (text/event-stream)</td>
 *   </tr>
 *   <tr>
 *     <td><b>RestTemplate</b></td>
 *     <td>{@link com.allan.spring_clients.todo.TodoClient}</td>
 *     <td>Built via {@code RestTemplateBuilder} in client constructor</td>
 *     <td>Legacy Synchronous (Template)</td>
 *   </tr>
 * </table>
 *
 * <h2>❓ The 5 Ws &amp; How (Why, When, Where, Who, How)</h2>
 * <dl>
 *   <dt><b>WHY:</b></dt>
 *   <dd>
 *     Centralizes base URLs, HTTP connection timeouts, custom headers, and client builder customization.
 *     It separates HTTP client infrastructure configuration from business logic components.
 *   </dd>
 *   <dt><b>WHEN:</b></dt>
 *   <dd>
 *     Whenever your application interacts with external HTTP REST APIs and requires standardized base URLs,
 *     connection pools, message converters, or security tokens.
 *   </dd>
 *   <dt><b>WHERE:</b></dt>
 *   <dd>
 *     Located in the {@code com.allan.spring_clients.config} package.
 *   </dd>
 *   <dt><b>WHO:</b></dt>
 *   <dd>
 *     Spring developers structuring modular, clean configuration for microservices and API clients.
 *   </dd>
 *   <dt><b>HOW:</b></dt>
 *   <dd>
 *     Defines {@link Bean} factory methods for {@link WebClient.Builder}, {@link WebClient}, and {@link RestClient}.
 *   </dd>
 * </dl>
 *
 * @see org.springframework.web.reactive.function.client.WebClient
 * @see org.springframework.web.client.RestClient
 * @see org.springframework.web.service.invoker.HttpServiceProxyFactory
 */
@Configuration
public class ClientConfig {

    /**
     * Provides a prototype/singleton {@link WebClient.Builder} bean.
     *
     * <p>
     * <b>Note:</b> When both {@code spring-boot-starter-webmvc} and {@code spring-boot-starter-webflux}
     * are present on the classpath, Spring Boot's autoconfiguration may prioritize MVC.
     * Declaring this builder bean explicitly guarantees reliable dependency injection.
     * </p>
     *
     * @return a new instance of {@link WebClient.Builder}
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    /**
     * Builds and exposes a configured {@link WebClient} bean pointing to the JSONPlaceholder base URL.
     *
     * <p>
     * Injected into {@link com.allan.spring_clients.user.UserClient} for reactive non-blocking API interactions.
     * </p>
     *
     * @param builder the autowired {@link WebClient.Builder}
     * @return an immutable {@link WebClient} configured with {@code baseUrl("https://jsonplaceholder.typicode.com")}
     */
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    /**
     * Builds and exposes a configured {@link RestClient} bean pointing to the JSONPlaceholder base URL.
     *
     * <p>
     * Injected into {@link com.allan.spring_clients.post.PostClient} for modern fluent synchronous API interactions.
     * </p>
     *
     * @param builder the autowired {@link RestClient.Builder} provided by Spring Boot
     * @return an immutable {@link RestClient} configured with {@code baseUrl("https://jsonplaceholder.typicode.com")}
     */
    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    /**
     * Builds and exposes an asynchronous / reactive HTTP Service Interface proxy bean for {@link AsyncAlbumClient}.
     *
     * <p>
     * Backed by {@link WebClientAdapter} to support non-blocking reactive return types ({@link reactor.core.publisher.Flux}
     * and {@link reactor.core.publisher.Mono}) generated by Spring's {@link HttpServiceProxyFactory}.
     * </p>
     *
     * @param webClient the configured {@link WebClient} bean pointing to the JSONPlaceholder base URL
     * @return a dynamically generated client proxy implementing {@link AsyncAlbumClient}
     */
    @Bean
    public AsyncAlbumClient asyncAlbumClient(WebClient webClient) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();
        return factory.createClient(AsyncAlbumClient.class);
    }

    /**
     * Builds and exposes an asynchronous {@link CompletableFuture}-based HTTP Service Interface proxy bean
     * for {@link CompletableFutureAlbumClient}.
     *
     * <p>
     * Backed by {@link WebClientAdapter} to support non-blocking asynchronous execution returning standard Java
     * {@link java.util.concurrent.CompletableFuture} promises generated by Spring's {@link HttpServiceProxyFactory}.
     * </p>
     *
     * @param webClient the configured {@link WebClient} bean pointing to the JSONPlaceholder base URL
     * @return a dynamically generated client proxy implementing {@link CompletableFutureAlbumClient}
     */
    @Bean
    public CompletableFutureAlbumClient completableFutureAlbumClient(WebClient webClient) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();
        return factory.createClient(CompletableFutureAlbumClient.class);
    }

    /*
     * ---------------------------------------------------------------------------------------------
     * Alternative / Pre-Spring Boot 4 Manual HTTP Interface Factory Configuration:
     * ---------------------------------------------------------------------------------------------
     * Prior to @ImportHttpServices (or when fine-grained custom proxy creation is needed),
     * HTTP interface clients like AlbumClient are registered manually using HttpServiceProxyFactory:
     *
     * @Bean
     * public AlbumClient albumClient() {
     *     RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
     *     HttpServiceProxyFactory factory = HttpServiceProxyFactory
     *             .builderFor(RestClientAdapter.create(restClient))
     *             .build();
     *     return factory.createClient(AlbumClient.class);
     * }
     * ---------------------------------------------------------------------------------------------
     */
}
