package exceptions;

import lombok.Getter;

@Getter
public class UrlAlreadyExistsException extends RuntimeException {
    private String url;

    public UrlAlreadyExistsException(String url) {
        super("URL " + url + " already exists");
        this.url = url;
    }
}
