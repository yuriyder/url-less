package interactor;

import exceptions.FailedToCreateUrlException;
import exceptions.UrlAlreadyExistsException;
import gateway.UrlGateway;
import generator.IdGenerator;
import shortener.ShortenedUrl;
import usecase.ShortenerUseCase;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ShortenerInteractor implements ShortenerUseCase {

    private UrlGateway urlGateway;
    public IdGenerator idGenerator;

    public ShortenerInteractor(UrlGateway urlGateway, IdGenerator idGenerator) {
        this.urlGateway = urlGateway;
        this.idGenerator = idGenerator;
    }

    @Override
    public Optional<ShortenedUrl> getById(String id) {
        return urlGateway.getById(id);
    }

    @Override
    public ShortenedUrl create(String url) {

        boolean collision = false;
        Set<String> collisions = new HashSet<>();
        do {
            try {
                String id = idGenerator.generate(url, collisions);
                collision = false;
                return urlGateway.create(url, id);
            } catch (UrlAlreadyExistsException ex) {
                collision = true;
                collisions.add(ex.getUrl());
            }
        } while (collision);

        throw new FailedToCreateUrlException();
    }
}
