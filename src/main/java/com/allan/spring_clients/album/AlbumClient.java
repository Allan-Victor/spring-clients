package com.allan.spring_clients.album;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

/**
 * <h1>Client Showcase: HTTP Service Interfaces (Declarative HTTP Client)</h1>
 *
 * <p>
 * This interface demonstrates Spring's <b>HTTP Interfaces</b> (declarative HTTP client) feature,
 * introduced in <b>Spring Framework 6.0 / Spring Boot 3.0</b> and significantly enhanced in
 * <b>Spring Framework 6.1+ / Spring Boot 4.x</b> (with {@code @ImportHttpServices}).
 * </p>
 *
 * <h2>📋 Client Overview</h2>
 * <ul>
 *   <li><b>Client Type:</b> Declarative HTTP Service Interface (Proxy-based)</li>
 *   <li><b>Style:</b> Annotation-driven, interface-only, declarative method signatures</li>
 *   <li><b>Execution Model:</b> Synchronous or Asynchronous (determined by return type and underlying adapter, e.g., {@code RestClientAdapter} or {@code WebClientAdapter})</li>
 *   <li><b>Spring Version:</b> Introduced in Spring 6.0 / Boot 3.0; Auto-registration via {@code @ImportHttpServices} in Spring 6.2 / Boot 4.0</li>
 * </ul>
 *
 * <h2>❓ The 5 Ws &amp; How (Why, When, Where, Who, How)</h2>
 * <dl>
 *   <dt><b>WHY (Purpose &amp; Motivation):</b></dt>
 *   <dd>
 *     Eliminates repetitive boilerplate request plumbing (such as building URIs, setting headers,
 *     handling serialization/deserialization, and mapping HTTP methods). Like Spring Data Repositories
 *     or Feign Clients, you write only an annotated interface and Spring dynamically creates the runtime proxy.
 *   </dd>
 *
 *   <dt><b>WHEN (Decision Criteria):</b></dt>
 *   <dd>
 *     Use when you want clean, type-safe, declarative contract definitions for external REST APIs,
 *     microservices, or 3rd-party services without writing manual execution logic in class bodies.
 *     It is strongly recommended for modern Spring applications over legacy RestTemplate or manual client wrappers.
 *   </dd>
 *
 *   <dt><b>WHERE (Location in Codebase):</b></dt>
 *   <dd>
 *     Declared as a Java interface (e.g., this file {@code AlbumClient.java}) annotated with {@link HttpExchange},
 *     and registered either via {@code @ImportHttpServices(basePackages = "...")} on the main application class
 *     or manually via {@code HttpServiceProxyFactory} in {@code ClientConfig.java}.
 *   </dd>
 *
 *   <dt><b>WHO (Target Audience &amp; Role):</b></dt>
 *   <dd>
 *     Ideal for microservice developers, API integration engineers, and teams migrating away from OpenFeign
 *     or RestTemplate to native, first-class Spring-supported declarative clients.
 *   </dd>
 *
 *   <dt><b>HOW (Mechanism &amp; Implementation):</b></dt>
 *   <dd>
 *     1. Annotate the interface with {@link HttpExchange} (specifying base URL, headers, or content types).<br>
 *     2. Define method signatures with exchange annotations such as {@link GetExchange}, {@code @PostExchange},
 *        {@code @PutExchange}, {@code @DeleteExchange}, and {@code @PatchExchange}.<br>
 *     3. Spring generates a dynamic proxy backed by either {@code RestClientAdapter} (blocking) or {@code WebClientAdapter} (reactive).<br>
 *     4. Inject {@code AlbumClient} into any Spring component via constructor injection.
 *   </dd>
 * </dl>
 *
 * <h2>⚖️ Pros &amp; Cons</h2>
 * <ul>
 *   <li><b>Pros:</b> Near-zero boilerplate, contract-first design, highly readable, works seamlessly with both RestClient and WebClient adapters.</li>
 *   <li><b>Cons:</b> Less flexible for dynamic per-request interceptors or deeply customized request pipelines compared to raw RestClient/WebClient fluent builders.</li>
 * </ul>
 *
 * @see org.springframework.web.service.annotation.HttpExchange
 * @see org.springframework.web.service.annotation.GetExchange
 * @see org.springframework.web.service.invoker.HttpServiceProxyFactory
 * @see org.springframework.web.service.registry.ImportHttpServices
 * @see com.allan.spring_clients.config.ClientConfig
 */
@HttpExchange(url = "https://jsonplaceholder.typicode.com")
public interface AlbumClient {

    /**
     * Retrieves all albums from the {@code /albums} endpoint.
     *
     * <p>
     * <b>HTTP Method:</b> {@code GET}<br>
     * <b>Target URL:</b> {@code https://jsonplaceholder.typicode.com/albums}<br>
     * <b>Return Type:</b> {@link List}&lt;{@link Album}&gt; - automatically deserialized by Spring's HttpMessageConverters.
     * </p>
     *
     * @return a list of all {@link Album} items retrieved from the remote API
     */
    @GetExchange("/albums")
    List<Album> findAll();
}
