package interactor;

import gateway.UrlGateway;
import shortener.ShortenedUrl;
import usecase.ShortenerUseCase;

import java.util.Optional;

public class ShortenerInteractor implements ShortenerUseCase {

    private UrlGateway urlGateway;

    public ShortenerInteractor(UrlGateway urlGateway){
        this.urlGateway = urlGateway;
    }

    @Override
    public Optional<ShortenedUrl> getById(String id) {
        return urlGateway.getById(id);
    }
}
