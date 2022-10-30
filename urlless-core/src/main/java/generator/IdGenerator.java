package generator;

import java.security.NoSuchAlgorithmException;
import java.util.Set;

public interface IdGenerator {
    String generate(String url, Set<String> collisions);
}
