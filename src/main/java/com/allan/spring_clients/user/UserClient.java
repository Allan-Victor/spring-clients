package com.allan.spring_clients.user;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>Client Showcase: WebClient (Reactive Asynchronous Non-Blocking Client)</h1>
 *
 * <p>
 * This class demonstrates the usage of <b>WebClient</b>, the reactive, non-blocking HTTP client
 * introduced in <b>Spring Framework 5.0 / Spring WebFlux</b>.
 * </p>
 *
 * <h2>📋 Client Overview</h2>
 * <ul>
 *   <li><b>Client Type:</b> Reactive Asynchronous Non-blocking HTTP Client</li>
 *   <li><b>Style:</b> Fluent, functional reactive pipeline API using Project Reactor ({@link Mono} &amp; {@link Flux})</li>
 *   <li><b>Execution Model:</b> Asynchronous / Non-blocking (event-loop driven, ideal for Spring WebFlux and high-concurrency microservices)</li>
 *   <li><b>Spring Version:</b> Introduced in Spring Framework 5.0 / Spring Boot 2.0+ (via {@code spring-boot-starter-webflux})</li>
 * </ul>
 *
 * <h2>❓ The 5 Ws &amp; How (Why, When, Where, Who, How)</h2>
 * <dl>
 *   <dt><b>WHY (Purpose &amp; Motivation):</b></dt>
 *   <dd>
 *     Traditional synchronous clients (like {@code RestTemplate}) block a server thread for each I/O wait.
 *     {@link WebClient} utilizes non-blocking I/O (via Netty / HTTP Client engines) and Project Reactor to handle
 *     thousands of concurrent requests with minimal thread count and memory overhead.
 *   </dd>
 *
 *   <dt><b>WHEN (Decision Criteria):</b></dt>
 *   <dd>
 *     Use {@link WebClient} when building reactive applications (Spring WebFlux), high-throughput systems, streaming data endpoints (Server-Sent Events / SSE),
 *     or when needing rich reactive operators (e.g., {@code retryWhen}, {@code timeout}, {@code zip}, {@code flatMap}, backpressure).
 *   </dd>
 *
 *   <dt><b>WHERE (Location in Codebase):</b></dt>
 *   <dd>
 *     Implemented as a Spring bean component (e.g., this file {@code UserClient.java}) that injects a preconfigured
 *     {@link WebClient} bean defined in {@code ClientConfig.java}.
 *   </dd>
 *
 *   <dt><b>WHO (Target Audience &amp; Role):</b></dt>
 *   <dd>
 *     Reactive systems engineers, backend developers handling high concurrency / streaming, and teams needing reactive streaming integrations.
 *   </dd>
 *
 *   <dt><b>HOW (Mechanism &amp; Implementation):</b></dt>
 *   <dd>
 *     1. Build a {@link WebClient} instance using {@link WebClient#builder()} or {@code ClientConfig.java}.<br>
 *     2. Inject {@link WebClient} into this service component via constructor injection.<br>
 *     3. Initiate request with {@code webClient.get()}, {@code webClient.post()}, etc.<br>
 *     4. Provide URI path and query / path parameters.<br>
 *     5. Execute via {@code .retrieve()} and transform response into a reactive stream using {@code .bodyToMono(...)} (for 0..1 items) or {@code .bodyToFlux(...)} (for 0..N items).<br>
 *     6. Consumers subscribe to the stream (or return the publisher to WebFlux controller pipelines).
 *   </dd>
 * </dl>
 *
 * <h2>⚖️ Pros &amp; Cons</h2>
 * <ul>
 *   <li><b>Pros:</b> Fully non-blocking, exceptional concurrency scaling, supports streaming / SSE, rich reactive operator ecosystem (retry, timeout, fallback).</li>
 *   <li><b>Cons:</b> Requires reactive programming knowledge (Project Reactor / Reactive Streams), introduces reactive dependencies (Netty/WebFlux), blocking in non-reactive apps (via {@code .block()}) can cause thread starvation.</li>
 * </ul>
 *
 * @see org.springframework.web.reactive.function.client.WebClient
 * @see reactor.core.publisher.Flux
 * @see reactor.core.publisher.Mono
 * @see com.allan.spring_clients.config.ClientConfig
 */
@Component
public class UserClient {

    private final WebClient webClient;

    /**
     * Constructs a {@code UserClient} with the configured {@link WebClient} bean.
     *
     * @param webClient the pre-configured {@link WebClient} instance pointing to the target base URL
     */
    public UserClient(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Fetches all users asynchronously as a reactive stream ({@link Flux}).
     *
     * <p>
     * <b>HTTP Method:</b> {@code GET}<br>
     * <b>Target URI:</b> {@code /users} (resolved against base URL {@code https://jsonplaceholder.typicode.com})<br>
     * <b>Reactive Type:</b> {@link Flux}&lt;{@link User}&gt; emitting user objects as they are deserialized.
     * </p>
     *
     * @return a {@link Flux} emitting {@link User} instances
     */
    public Flux<User> findAll() {
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class);
    }

    /**
     * Fetches a single user by their ID asynchronously as a reactive {@link Mono}.
     *
     * <p>
     * <b>HTTP Method:</b> {@code GET}<br>
     * <b>Target URI:</b> {@code /users/{id}}<br>
     * <b>Reactive Type:</b> {@link Mono}&lt;{@link User}&gt; emitting 0 or 1 user.
     * </p>
     *
     * @param id the unique ID of the user to fetch
     * @return a {@link Mono} emitting the {@link User} if found, or empty/error otherwise
     */
    public Mono<User> findUser(Integer id) {
        return webClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }
}
