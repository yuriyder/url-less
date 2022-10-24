package interactor;

import gateway.UrlGateway;
import gateway.UrlGatewayFake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shortener.ShortenedUrl;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortenerInteractorTest {

    private ShortenerInteractor sut;
    private UrlGateway urlGateway;

    @BeforeEach
    void setUp() {
        urlGateway = new UrlGatewayFake();
        sut = new ShortenerInteractor(urlGateway);
    }

    @Test
    public void shouldReturnEmptyOnNonExistingUrlTest() {
        Optional<ShortenedUrl> result = sut.getById("NON-EXISTING-URL");

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void shouldReturnResultOnExistingUrlTest() {
        urlGateway.create("http://unit.test", "12vfvf");

        ShortenedUrl result = sut.getById("12vfvf").get();

        assertEquals("12vfvf", result.getId());
        assertEquals("http://unit.test", result.getUrl());
    }
}
