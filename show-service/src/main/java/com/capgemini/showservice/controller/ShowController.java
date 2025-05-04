package com.capgemini.showservice.controller;

import com.capgemini.showservice.dto.ShowRequest;
import com.capgemini.showservice.dto.ShowResponse;
import com.capgemini.showservice.service.IShowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final IShowService showService;

    @PostMapping
    // @PreAuthorize("hasRole('OWNER') or hasRole('ADMIN')")
    public ResponseEntity<ShowResponse> createShow(@Valid @RequestBody ShowRequest request) {
        ShowResponse response = showService.createShow(request);
        return ResponseEntity.status(201).body(response); // 201 Created
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowResponse>> getByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(showService.getShowsByMovieId(movieId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(showService.getShowById(id));
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('OWNER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
