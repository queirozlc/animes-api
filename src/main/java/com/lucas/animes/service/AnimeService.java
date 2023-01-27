package com.lucas.animes.service;

import com.lucas.animes.api.request.AnimeRequestBody;
import com.lucas.animes.entity.Anime;
import com.lucas.animes.exception.BadRequestException;
import com.lucas.animes.repository.AnimeRepository;
import com.lucas.animes.util.mapper.AnimeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimeService {

	private final AnimeRepository repository;

	@Transactional(readOnly = true)
	public Page<Anime> listAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Anime findById(UUID id) {
		return repository
				.findById(id)
				.orElseThrow(() -> new BadRequestException("Anime not found."));
	}

	@Transactional(readOnly = true)
	public Anime findByName(String name) {
		return repository.findByName(name).orElseThrow(() -> new BadRequestException("Anime not found."));
	}

	@Transactional
	public Anime save(@Valid AnimeRequestBody animeRequestBody) {
		return repository.save(AnimeMapper.INSTANCE.toAnime(animeRequestBody));
	}

	@Transactional
	public void delete(UUID id) {
		Anime animeFound = repository
				.findById(id)
				.orElseThrow(() -> new BadRequestException("Anime not found."));
		repository.delete(animeFound);
	}

	@Transactional
	public Anime update(@Valid AnimeRequestBody animeRequestBody) {
		BadRequestException.requireNonNull(animeRequestBody.getId(), "Anime id cannot be empty." );
		Anime animeFound = repository
				.findById(animeRequestBody.getId())
				.orElseThrow(() -> new RuntimeException("Anime not found."));
		Anime anime = AnimeMapper.INSTANCE.toAnime(animeRequestBody);
		anime.setId(animeFound.getId());
		return repository.save(anime);
	}
}
