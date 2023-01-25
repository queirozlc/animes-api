package com.lucas.animes.util.mapper;

import com.lucas.animes.api.request.AnimeRequestBody;
import com.lucas.animes.entity.Anime;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnimeMapper {

	AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

	Anime toAnime(AnimeRequestBody animeRequestBody);
}
