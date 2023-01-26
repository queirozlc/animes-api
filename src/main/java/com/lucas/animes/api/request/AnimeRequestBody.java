package com.lucas.animes.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimeRequestBody {
	private UUID id;
	@NotBlank(message = "Anime name cannot be empty.")
	private String name;
}
