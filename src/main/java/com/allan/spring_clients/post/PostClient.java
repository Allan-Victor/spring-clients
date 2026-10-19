package com.allan.spring_clients.post;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * <h1>Client Showcase: RestClient (Modern Synchronous Fluent Client)</h1>
 *
 * <p>
 * This class demonstrates the usage of <b>RestClient</b>, introduced in
 * <b>Spring Framework 6.1 / Spring Boot 3.2</b> as the modern, fluent synchronous HTTP client
 * designed to supersede the legacy {@link org.springframework.web.client.RestTemplate}.
 * </p>
 *
 * <h2>📋 Client Overview</h2>
 * <ul>
 *   <li><b>Client Type:</b> Modern Synchronous Fluent HTTP Client</li>
 *   <li><b>Style:</b> Fluent, builder-style functional API (similar syntax to WebClient, but blocking/synchronous)</li>
 *   <li><b>Execution Model:</b> Synchronous / Blocking (imperative execution model, fits traditional Spring MVC / Servlet applications)</li>
 *   <li><b>Spring Version:</b> Introduced in Spring Framework 6.1 / Spring Boot 3.2+</li>
 * </ul>
 *
 * <h2>❓ The 5 Ws &amp; How (Why, When, Where, Who, How)</h2>
 * <dl>
 *   <dt><b>WHY (Purpose &amp; Motivation):</b></dt>
 *   <dd>
 *     {@link org.springframework.web.client.RestTemplate} has dozens of overloaded methods and lacks a fluent, readable API.
 *     While {@link org.springframework.web.reactive.function.client.WebClient} provides a modern fluent API, it requires reactive
 *     dependencies (Reactor / Netty) and reactive programming concepts (Mono/Flux). {@link RestClient} combines the best of both worlds:
 *     the modern, expressive fluent builder API of WebClient with synchronous, blocking execution on top of HTTP message converters.
 *   </dd>
 *
 *   <dt><b>WHEN (Decision Criteria):</b></dt>
 *   <dd>
 *     Use {@link RestClient} for all modern, non-reactive (Spring MVC, Servlet-based) applications when you need fine-grained control
 *     over HTTP request headers, query params, error status handlers, interceptors, or body extraction, and you prefer synchronous execution.
 *   </dd>
 *
 *   <dt><b>WHERE (Location in Codebase):</b></dt>
 *   <dd>
 *     Implemented as a Spring bean component (e.g., this file {@code PostClient.java}) that injects a preconfigured
 *     {@link RestClient} bean defined in {@code ClientConfig.java}.
 *   </dd>
 *
 *   <dt><b>WHO (Target Audience &amp; Role):</b></dt>
 *   <dd>
 *     Any Spring developer building standard REST clients or backend service integrations in Spring Boot 3.2+ / 4.x applications
 *     who wants modern fluent code without adding reactive overhead.
 *   </dd>
 *
 *   <dt><b>HOW (Mechanism &amp; Implementation):</b></dt>
 *   <dd>
 *     1. Configure a {@link RestClient} bean (e.g., using {@code RestClient.builder().baseUrl(...).build()}).<br>
 *     2. Inject {@link RestClient} into this service component via constructor injection.<br>
 *     3. Initiate an HTTP request via {@code restClient.get()}, {@code restClient.post()}, etc.<br>
 *     4. Chain URI paths, headers, accept types, or request body.<br>
 *     5. Call {@code .retrieve()} to trigger the exchange and specify the response body mapping using {@code .body(...)} or {@link ParameterizedTypeReference}.
 *   </dd>
 * </dl>
 *
 * <h2>⚖️ Pros &amp; Cons</h2>
 * <ul>
 *   <li><b>Pros:</b> Intuitive fluent API, strong generic type-safety with {@link ParameterizedTypeReference}, flexible status handlers ({@code onStatus}), built on modern Spring 6 infrastructure, no reactive learning curve.</li>
 *   <li><b>Cons:</b> Blocking I/O model (not suitable for high-concurrency event loops / reactive WebFlux stacks).</li>
 * </ul>
 *
 * @see org.springframework.web.client.RestClient
 * @see org.springframework.core.ParameterizedTypeReference
 * @see com.allan.spring_clients.config.ClientConfig
 */
@Component
public class PostClient {

    private final RestClient restClient;

    /**
     * Constructs a {@code PostClient} with the configured {@link RestClient} bean.
     *
     * @param restClient the pre-configured {@link RestClient} instance pointing to the target base URL
     */
    public PostClient(RestClient restClient) {
        this.restClient = restClient;
    }

    /**
     * Fetches all posts synchronously from the {@code /posts} endpoint.
     *
     * <p>
     * <b>HTTP Method:</b> {@code GET}<br>
     * <b>Target URI:</b> {@code /posts} (resolved against base URL {@code https://jsonplaceholder.typicode.com})<br>
     * <b>Response Extraction:</b> Uses {@link ParameterizedTypeReference} to preserve generic type information ({@code List<Post>}) at runtime.
     * </p>
     *
     * @return a {@link List} of {@link Post} objects received from the remote API
     */
    public List<Post> findAll() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
