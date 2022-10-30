package gateway;

import exceptions.UrlAlreadyExistsException;
import shortener.ShortenedUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UrlGatewayFake implements UrlGateway {

    Map<String, ShortenedUrl> urls = new HashMap<>();

    @Override
    public ShortenedUrl create(String url, String id) {
        if (urls.containsKey(id)) {
            throw new UrlAlreadyExistsException(id);
        }
        ShortenedUrl result = new ShortenedUrl(id, url);
        urls.put(id, result);
        return result;
    }

    @Override
    public Optional<ShortenedUrl> getById(String id) {
        return Optional.ofNullable(urls.get(id));
    }

    @Override
    public List<ShortenedUrl> getAll() {
        return new ArrayList<>(urls.values());
    }
}
