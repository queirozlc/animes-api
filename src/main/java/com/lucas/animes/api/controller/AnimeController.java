package com.lucas.animes.api.controller;

import com.lucas.animes.api.request.AnimeRequestBody;
import com.lucas.animes.entity.Anime;
import com.lucas.animes.service.AnimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("animes")
@RequiredArgsConstructor
public class AnimeController {

	private final AnimeService service;

	@GetMapping
	public ResponseEntity<List<Anime>> listAll() {
		return ResponseEntity.ok(service.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Anime> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("/find")
	public ResponseEntity<Anime> findByName(@RequestParam String name) {
		return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Anime> save(@RequestBody @Valid AnimeRequestBody animeRequestBody) {
		return new ResponseEntity<>(service.save(animeRequestBody), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.ok("Anime deleted with success.");
	}

	@PutMapping
	public ResponseEntity<Anime> update(@RequestBody @Valid AnimeRequestBody animeRequestBody) {
		return new ResponseEntity<>(service.update(animeRequestBody), HttpStatus.OK);
	}
}
