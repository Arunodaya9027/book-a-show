package com.capgemini.showservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "movie-service",
        url = "${movie-service.url}",
        fallback = MovieClientFallback.class
)
public interface MovieClient {

    @GetMapping("/api/movies/{id}/name")
    String getMovieName(@PathVariable("id") Long id);
}
