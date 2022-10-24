package com.example.urllessweb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shortener.ShortenedUrl;
import usecase.ShortenerUseCase;

import java.util.function.Function;
import java.util.function.Supplier;

@RestController
@RequestMapping("")
public class ShortenerController {

    private final ShortenerUseCase shortener;

    public ShortenerController(ShortenerUseCase shortenerUseCase) {
        shortener = shortenerUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable(value = "id") String id) {
        return shortener.getById(id)
                .map(redirect())
                .orElseGet(notFound());
    }

    private Supplier<ResponseEntity<Object>> notFound() {
        return () -> ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("URL does not exist");
    }

    private Function<ShortenedUrl, ResponseEntity<Object>> redirect() {
        return s -> ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header("Location", s.getUrl())
                .build();
    }
}
