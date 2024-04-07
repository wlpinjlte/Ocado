import org.example.BasketSplitter;
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

import static junit.framework.Assert.assertEquals;

public class BasketSplitterTest {
    @Test
    public void basket1Test() throws URISyntaxException, IOException, ParseException {
        //given
        BasketSplitter basketSplitter=new BasketSplitter(Paths.get(Main.class.getClassLoader().getResource("config.json").toURI()).toAbsolutePath().toString());
        String[] items1={"Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht"};
        //when
        Map<String, List<String>> solution = basketSplitter.split(Arrays.stream(items1).toList());
        Map<String, List<String>> expectedMap = new HashMap<>();
        expectedMap.put("Courier", Arrays.asList("Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Cookies - Englishbay Wht"));
        expectedMap.put("Express Collection", Arrays.asList("Fond - Chocolate"));
        //then
        assertEquals(expectedMap, solution);
    }

    @Test
    public void basket2Test() throws IOException, ParseException, URISyntaxException {
        //given
        BasketSplitter basketSplitter=new BasketSplitter(Paths.get(Main.class.getClassLoader().getResource("config.json").toURI()).toAbsolutePath().toString());
        String[] items2={"Fond - Chocolate", "Chocolate - Unsweetened", "Nut - Almond, Blanched, Whole", "Haggis", "Mushroom - Porcini Frozen", "Cake - Miini Cheesecake Cherry", "Sauce - Mint", "Longan", "Bag Clear 10 Lb", "Nantucket - Pomegranate Pear", "Puree - Strawberry", "Numi - Assorted Teas", "Apples - Spartan", "Garlic - Peeled", "Cabbage - Nappa", "Bagel - Whole White Sesame", "Tea - Apple Green Tea"};
        //when
        Map<String, List<String>> solution = basketSplitter.split(Arrays.stream(items2).toList());
        System.out.println(solution);
        Map<String, List<String>> expectedMap = new HashMap<>();
        expectedMap.put("Same day delivery", Arrays.asList("Sauce - Mint", "Numi - Assorted Teas", "Garlic - Peeled"));
        expectedMap.put("Courier", Arrays.asList("Cake - Miini Cheesecake Cherry"));
        expectedMap.put("Express Collection", Arrays.asList("Fond - Chocolate", "Chocolate - Unsweetened", "Nut - Almond, Blanched, Whole", "Haggis", "Mushroom - Porcini Frozen", "Longan", "Bag Clear 10 Lb", "Nantucket - Pomegranate Pear", "Puree - Strawberry", "Apples - Spartan", "Cabbage - Nappa", "Bagel - Whole White Sesame", "Tea - Apple Green Tea"));
        //then
        assertEquals(expectedMap, solution);
    }
}
