# Spring Clients Showcase

A comprehensive and practical guide demonstrating different ways to perform HTTP calls in Spring applications. This project, **spring-clients**, compares and showcases the usage of:

* **RestTemplate** (deprecated)
* **WebClient** (reactive)**
* **RestClient** (introduced in Spring Framework 6 / Spring Boot 3)
* **HTTP Interfaces** (Spring Boot 4 / Spring Framework 6.1+)
* **HttpServiceProxyFactory / HttpExchange** enhancements

This repository is ideal for developers upgrading older Spring applications or learning modern, idiomatic HTTP client patterns.

---

## **📘 Project Overview**

For a comprehensive, step-by-step tutorial on how to configure, inject, and use every client in your services, see the **[📖 Step-by-Step Client Usage Guide](CLIENT_USAGE_GUIDE.md)**.

The goal of this project is to provide clear and practical examples of different Spring HTTP client approaches, including:

* How to configure each client
* How to build requests and parse responses
* How to handle headers, authentication, and error handling
* How to determine when to use each approach in real-world applications

Every client implementation calls the same sample API ([JSONPlaceholder](https://jsonplaceholder.typicode.com)), making comparisons consistent and easy to understand.

---

## **🗺️ Client-to-File Quick Reference**

Developers cloning this repository can directly find each client demonstration in the following files:

| Spring HTTP Client | File Location | Key Technologies / Annotations | Description & Paradigm |
| :--- | :--- | :--- | :--- |
| **HTTP Interfaces (Sync)** | [`src/main/java/com/allan/spring_clients/album/AlbumClient.java`](src/main/java/com/allan/spring_clients/album/AlbumClient.java) | `@HttpExchange`, `@GetExchange`, `@ImportHttpServices` | Declarative synchronous proxy client (Zero boilerplate, Spring 6+ / Boot 4) |
| **HTTP Interfaces (Async Reactive)** | [`src/main/java/com/allan/spring_clients/album/AsyncAlbumClient.java`](src/main/java/com/allan/spring_clients/album/AsyncAlbumClient.java) | `@HttpExchange`, `@GetExchange`, `WebClientAdapter`, `Flux`, `Mono` | Declarative reactive asynchronous proxy client (Non-blocking, Spring WebFlux) |
| **HTTP Interfaces (Async CompletableFuture)** | [`src/main/java/com/allan/spring_clients/album/CompletableFutureAlbumClient.java`](src/main/java/com/allan/spring_clients/album/CompletableFutureAlbumClient.java) | `@HttpExchange`, `@GetExchange`, `CompletableFuture` | Declarative Java Promise asynchronous proxy client (Standard Java Concurrency) |
| **Server-Sent Events (SSE) Client** | [`src/main/java/com/allan/spring_clients/sse/SseClient.java`](src/main/java/com/allan/spring_clients/sse/SseClient.java) | `WebClient`, `ServerSentEvent`, `text/event-stream` | Real-time unidirectional streaming client (How-To guide & SSE consumer) |
| **RestClient** | [`src/main/java/com/allan/spring_clients/post/PostClient.java`](src/main/java/com/allan/spring_clients/post/PostClient.java) | `RestClient`, `ParameterizedTypeReference` | Modern synchronous fluent client (Spring Framework 6.1 / Spring Boot 3.2+) |
| **RestTemplate** | [`src/main/java/com/allan/spring_clients/todo/TodoClient.java`](src/main/java/com/allan/spring_clients/todo/TodoClient.java) | `RestTemplate`, `RestTemplateBuilder`, `exchange()` | Classic synchronous template client (Spring 3.0+ legacy / maintenance mode) |
| **WebClient** | [`src/main/java/com/allan/spring_clients/user/UserClient.java`](src/main/java/com/allan/spring_clients/user/UserClient.java) | `WebClient`, `Mono`, `Flux` | Reactive asynchronous non-blocking client (Spring WebFlux / Project Reactor) |
| **Client Configuration** | [`src/main/java/com/allan/spring_clients/config/ClientConfig.java`](src/main/java/com/allan/spring_clients/config/ClientConfig.java) | `@Configuration`, `WebClient.Builder`, `RestClient.Builder` | Central bean definitions and adapter setups |

---




## **🚀 Features Demonstrated**

### **1. RestTemplate (Deprecated)**

* Simple synchronous HTTP calls
* Custom error handler
* Exchange API usage
* Pros/Cons and deprecation notes

### **2. WebClient (Reactive)**

* Asynchronous reactive pipelines
* Configured with connection pooling
* Mono/Flux usage
* Retry, timeout, and error handling strategies

### **3. RestClient (Modern Synchronous Client)**

* Simpler than WebClient
* Fully synchronous but built on WebClient infrastructure
* Fluent API and type-safe response handling
* Ideal replacement for RestTemplate in non-reactive apps

### **4. HTTP Interfaces (Spring Boot 4+ Feature)**

* Declarative HTTP clients using annotated Java Interfaces
* `@GetExchange`, `@PostExchange`, etc.
* Automatic proxy generation
* Clean, type-safe, minimal boilerplate
* Configurable execution models: Synchronous (`AlbumClient`), Reactive (`AsyncAlbumClient`), or Promise-based (`CompletableFutureAlbumClient`)

### **5. Server-Sent Events (SSE) Client & How-To Guide**

* Consuming real-time unidirectional streams (`text/event-stream`)
* Raw text streaming, JSON deserialization, and full `ServerSentEvent<T>` metadata (IDs, event types, retry intervals)
* Step-by-step How-To and producer controller demonstration (`SseDemoController`)

---

## **🧪 Example Scenarios Implemented**

Each HTTP client implementation includes examples such as:

* `GET /users`
* `POST /users`
* Sending query parameters
* Handling JSON and POJOs
* Global vs per-request headers
* Error handling demonstration with 4xx and 5xx responses

---

## **⚙️ Requirements**

* **Java 17+** (Java 21 recommended)
* **Spring Boot 3.3+** for RestClient
* **Spring Boot 4+** (or Spring Framework 6.1+) for HTTP Interfaces
* Maven or Gradle

---

## **📦 Running the Project**

### **Maven**

```bash
mvn spring-boot:run
```

### **Gradle**

```bash
gradle bootRun
```

You can also run the demo class directly from your IDE.

---

## **🆚 Comparison Table**

| Feature                    | RestTemplate | WebClient       | RestClient      | HTTP Interfaces                 |
| -------------------------- | ------------ | --------------- | --------------- | ------------------------------- |
| Sync/Async                 | Sync         | Async/Reactive  | Sync            | Sync/Async (depends on backend) |
| Boilerplate                | Medium       | High (reactive) | Low             | Very Low                        |
| Type Safety                | Medium       | High            | High            | Very High                       |
| Best Use Case              | Legacy apps  | Reactive apps   | Modern MVC apps | Declarative clients             |
| Recommended by Spring Team | ❌ Deprecated | ✔️ Yes          | ✔️ Yes          | ✔️ Strongly recommended         |

---

## **📚 Why These Features Matter**

Spring has modernized its HTTP client landscape. Developers upgrading from older Spring Boot versions must understand these changes:

* **RestTemplate is no longer evolving.**
* **RestClient improves synchronous development** with a clean, modern API.
* **WebClient remains the best for reactive systems.**
* **HTTP Interfaces are the future** for easy, declarative API clients.

This project helps you learn them quickly and correctly.

---

## **📈 Roadmap**

Future updates will include:

* OAuth2 client examples
* Multipart upload/download
* Retry patterns with Spring Retry
* Circuit breaker usage (Resilience4j)
* Benchmark comparisons

---

## **🤝 Contributing**

Contributions are welcome! Feel free to fork the project, create a branch, and submit a pull request.

---

## **📄 License**

This project is open-source and available under the MIT License.

---

## **✉️ Contact**

For questions or improvements:

* **Author:** Allan Victor Kibuuka
* GitHub: *add your GitHub link here*

---

Thank you for using this project! 🎉
