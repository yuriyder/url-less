package gateway;

import shortener.ShortenedUrl;

import java.util.List;
import java.util.Optional;

public interface UrlGateway {
    ShortenedUrl create(String url, String id);

    Optional<ShortenedUrl> getById(String id);

    List<ShortenedUrl> getAll();
}
