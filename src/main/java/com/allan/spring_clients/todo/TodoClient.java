package com.allan.spring_clients.todo;

import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * <h1>Client Showcase: RestTemplate (Legacy Synchronous Template Client)</h1>
 *
 * <p>
 * This class demonstrates the usage of <b>RestTemplate</b>, the classic and traditional synchronous
 * HTTP client in Spring Framework available since <b>Spring Framework 3.0</b>.
 * </p>
 *
 * <h2>📋 Client Overview</h2>
 * <ul>
 *   <li><b>Client Type:</b> Classic Synchronous Template-based HTTP Client</li>
 *   <li><b>Style:</b> Method-heavy template API (e.g., {@code getForObject}, {@code postForEntity}, {@code exchange})</li>
 *   <li><b>Execution Model:</b> Synchronous / Blocking</li>
 *   <li><b>Spring Version:</b> Introduced in Spring Framework 3.0. In Spring 5+ and Spring 6+, it is in <b>maintenance mode</b> (no new features added) in favor of {@link org.springframework.web.client.RestClient} and {@link org.springframework.web.reactive.function.client.WebClient}.</li>
 * </ul>
 *
 * <h2>❓ The 5 Ws &amp; How (Why, When, Where, Who, How)</h2>
 * <dl>
 *   <dt><b>WHY (Purpose &amp; Motivation):</b></dt>
 *   <dd>
 *     Historically provided a high-level abstraction over lower-level Java HTTP client libraries (such as {@code HttpURLConnection}, Apache HttpClient, OkHttp).
 *     It simplified REST service consumption with automatic Jackson JSON serialization and deserialization via {@code HttpMessageConverter}s.
 *   </dd>
 *
 *   <dt><b>WHEN (Decision Criteria):</b></dt>
 *   <dd>
 *     <b>When to use:</b> Maintenance of legacy Spring Boot (1.x, 2.x, or early 3.x) applications where refactoring to {@code RestClient} has not yet taken place.<br>
 *     <b>When NOT to use:</b> For new development in Spring Boot 3.2+ / 4.x. Always prefer {@link org.springframework.web.client.RestClient} (for synchronous calls) or HTTP Interfaces.
 *   </dd>
 *
 *   <dt><b>WHERE (Location in Codebase):</b></dt>
 *   <dd>
 *     Implemented as a Spring bean component (e.g., this file {@code TodoClient.java}) initialized via {@link RestTemplateBuilder}.
 *   </dd>
 *
 *   <dt><b>WHO (Target Audience &amp; Role):</b></dt>
 *   <dd>
 *     Developers maintaining legacy Spring codebases or those learning the evolution of Spring HTTP clients across Spring versions.
 *   </dd>
 *
 *   <dt><b>HOW (Mechanism &amp; Implementation):</b></dt>
 *   <dd>
 *     1. Obtain a {@link RestTemplate} instance directly or via {@link RestTemplateBuilder}.<br>
 *     2. Call methods like {@code getForObject()}, {@code getForEntity()}, or {@code exchange()}.<br>
 *     3. For generic collections (like {@code List<Todo>}), use the {@code exchange()} method with {@link ParameterizedTypeReference} to avoid type erasure issues.<br>
 *     4. Extract the response payload using {@code getBody()}.
 *   </dd>
 * </dl>
 *
 * <h2>⚖️ Pros &amp; Cons</h2>
 * <ul>
 *   <li><b>Pros:</b> Battle-tested, ubiquitous in existing enterprise Java code, huge ecosystem of custom interceptors and error handlers.</li>
 *   <li><b>Cons:</b> Maintenance mode (no new features), bloated API with numerous overloaded methods, lacks modern fluent/builder syntax, rigid error handling.</li>
 * </ul>
 *
 * @see org.springframework.web.client.RestTemplate
 * @see org.springframework.boot.restclient.RestTemplateBuilder
 * @see org.springframework.core.ParameterizedTypeReference
 * @see com.allan.spring_clients.post.PostClient
 */
@Component
public class TodoClient {

    private final RestTemplate restTemplate;

    /**
     * Constructs a {@code TodoClient} using Spring Boot's {@link RestTemplateBuilder}.
     *
     * @param builder the autowired {@link RestTemplateBuilder} provided by Spring Boot
     */
    public TodoClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * Fetches all todo items synchronously from the remote {@code /todos} endpoint.
     *
     * <p>
     * <b>HTTP Method:</b> {@code GET}<br>
     * <b>Target URL:</b> {@code https://jsonplaceholder.typicode.com/todos}<br>
     * <b>Method Used:</b> {@link RestTemplate#exchange(String, HttpMethod, org.springframework.http.HttpEntity, ParameterizedTypeReference, Object...)}
     * to preserve generic type {@code List<Todo>}.
     * </p>
     *
     * @return a {@link List} of {@link Todo} objects returned by the API
     */
    public List<Todo> findAll() {
        return restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/todos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Todo>>() {}
        ).getBody();
    }
}
