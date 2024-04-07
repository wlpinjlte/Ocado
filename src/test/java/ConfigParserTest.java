import org.example.ConfigParser;
import org.example.Main;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConfigParserTest {
    @Test
    public void testParseJson() throws IOException, ParseException, URISyntaxException {
        // Tworzenie instancji parsera
        ConfigParser parser = new ConfigParser(Paths.get(Main.class.getClassLoader().getResource("test_config.json").toURI()).toAbsolutePath().toString());

        // Przykładowe dane JSON


        // Oczekiwana mapa
        Map<String, List<String>> expectedMap = new HashMap<>();
        expectedMap.put("key1", Arrays.asList("a", "b", "c"));
        expectedMap.put("key2", Arrays.asList("x", "y", "z", "w"));
        expectedMap.put("key3", Arrays.asList("1", "2"));

        // Testowanie
        Map<String, List<String>> resultMap = parser.praseJson();
        assertEquals(expectedMap, resultMap);
    }
}
