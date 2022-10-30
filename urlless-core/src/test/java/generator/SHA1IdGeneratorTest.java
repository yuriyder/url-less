package generator;

import gateway.UrlGatewayFake;
import interactor.ShortenerInteractor;
import org.junit.jupiter.api.Test;
import shortener.ShortenedUrl;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SHA1IdGeneratorTest {

    private static final String ID_REGEX = "^[a-zA-Z0-9-_]{6}$";

    @Test
      public void shouldResolveCollisionsTest() {
        UrlGatewayFake urlGatewayFake = new UrlGatewayFake();
        SHA1IdGenerator idGenerator = new SHA1IdGenerator();
        ShortenerInteractor interactor = new ShortenerInteractor(urlGatewayFake, idGenerator);

        ShortenedUrl result1 = interactor.create("http://some.url");
        ShortenedUrl result2 = interactor.create("http://some.url");

        assertNotEquals(result1.getId(), result2.getId());
        assertTrue(result1.getId().matches(ID_REGEX), () -> idMessage(result1.getId()));
        assertTrue(result2.getId().matches(ID_REGEX), () -> idMessage(result2.getId()));
    }

    private String idMessage(String id) {
        return id + " does not match regex " + ID_REGEX;
    }
}
