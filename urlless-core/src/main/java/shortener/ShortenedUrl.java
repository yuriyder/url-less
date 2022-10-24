package shortener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ShortenedUrl {
    private final String id;
    private final String url;
}
