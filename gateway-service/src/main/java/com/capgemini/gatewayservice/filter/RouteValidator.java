package com.capgemini.gatewayservice.filter;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component

public class RouteValidator {


    public static final List<String> openApiEndpoints = List.of(
            "/auth/v1/register",
            "/auth/v1/login",
            "/auth/v1/refreshToken",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}

