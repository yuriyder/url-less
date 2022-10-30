package usecase;

import shortener.ShortenedUrl;

import java.util.Optional;

public interface ShortenerUseCase {
    Optional<ShortenedUrl> getById(String id);

    ShortenedUrl create(String url);
}
