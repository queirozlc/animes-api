package com.lucas.animes.api.controller;

import com.lucas.animes.api.request.AnimeRequestBody;
import com.lucas.animes.entity.Anime;
import com.lucas.animes.exception.BadRequestException;
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
	public ResponseEntity<?> findById(@PathVariable UUID id) {
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (BadRequestException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid AnimeRequestBody animeRequestBody) {
		try {
			return new ResponseEntity<>(service.save(animeRequestBody), HttpStatus.CREATED);
		}catch (BadRequestException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		try {
			service.delete(id);
			return ResponseEntity.ok("Anime deleted with success.");
		}catch (BadRequestException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid AnimeRequestBody animeRequestBody) {
		try {
			return new ResponseEntity<>(service.update(animeRequestBody), HttpStatus.OK);
		}catch (BadRequestException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
