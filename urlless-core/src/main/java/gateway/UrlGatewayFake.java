package gateway;

import shortener.ShortenedUrl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UrlGatewayFake implements UrlGateway {

    Map<String, ShortenedUrl> urls = new HashMap<>();

    @Override
    public ShortenedUrl create(String url, String id) {
        ShortenedUrl result = new ShortenedUrl(id, url);
        urls.put(id, result);
        return result;
    }

    @Override
    public Optional<ShortenedUrl> getById(String id) {
        return Optional.ofNullable(urls.get(id));
    }
}
