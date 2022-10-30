package fake;

import generator.IdGenerator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Getter
public class IdGeneratorFake implements IdGenerator {

    List<String> ids = new ArrayList<>();
    int current = 0;
    Set<String> collisions;

    @Override
    public String generate(String url, Set<String> collisions) {
        String result = ids.get(current);
        current = (current + 1) % ids.size();
        this.collisions = collisions;
        return result;
    }

    public void add(String... id) {
        ids.addAll(Arrays.asList(id));
    }
}
