package interactor;

import fake.IdGeneratorFake;
import gateway.UrlGateway;
import gateway.UrlGatewayFake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shortener.ShortenedUrl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShortenerInteractorTest {

    private ShortenerInteractor sut;
    private UrlGateway urlGateway;
    private IdGeneratorFake idGenerator;

    @BeforeEach
    void setUp() {
        idGenerator = new IdGeneratorFake();
        urlGateway = new UrlGatewayFake();
        sut = new ShortenerInteractor(urlGateway, idGenerator);
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

    @Test
    public void shouldReturnCreatedUrlTest() {
        idGenerator.add("abcDEc");
        ShortenedUrl result = sut.create("http://test.com");

        assertEquals("abcDEc", result.getId());
        assertEquals("http://test.com", result.getUrl());
    }

    @Test
    public void shouldCreateDifferentShortenedUrlsTest() {
        idGenerator.add("abcdef", "abcdef", "abcdef", "ghikjl");

        ShortenedUrl result1 = sut.create("http://duplication.test");
        ShortenedUrl result2 = sut.create("http://duplication.test");

        assertNotEquals(result1.getId(), result2.getId());
        assertEquals("ghikjl", result2.getId());
        assertEquals(1, idGenerator.getCollisions().size());
        assertTrue(idGenerator.getCollisions().contains("abcdef"));
    }
}
