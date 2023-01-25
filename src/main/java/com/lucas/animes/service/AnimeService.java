package com.lucas.animes.service;

import com.lucas.animes.api.request.AnimeRequestBody;
import com.lucas.animes.entity.Anime;
import com.lucas.animes.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimeService {

	private final AnimeRepository repository;

	public List<Anime> listAll() {
		return repository.findAll();
	}

	public Anime findById(UUID id) {
		return repository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found."));
	}

	public Anime save(AnimeRequestBody animeRequestBody) {
		return repository.save(Anime.builder()
				.name(animeRequestBody.getName())
				.build());
	}

	public void delete(UUID id) {
		Anime animeFound = repository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
		repository.delete(animeFound);
	}
}
