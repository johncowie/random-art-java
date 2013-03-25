package uk.co.johncowie.art.random.numbers;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StringNumberGeneratorTest {

    @Test
    public void test() {
        NumberGenerator s = new StringNumberGenerator("blah");
        int min = -5;
        int max = +5;
        int size = 10000;
        int tolerance = 30;
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for(int i = min; i <= max; i++) {
            counts.put(i, 0);
        }
        for(int i = 0; i < size; i++) {
            int n = s.getRandomInt(min, max);
            counts.put(n, counts.get(n) + 1);
        }
        System.out.println(counts);

    }

}
