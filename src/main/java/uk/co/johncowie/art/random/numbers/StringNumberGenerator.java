package uk.co.johncowie.art.random.numbers;

import java.util.Random;

public class StringNumberGenerator implements NumberGenerator {

    Random random;

    public StringNumberGenerator(String string) {
        random = new Random(string.hashCode());
    }

    public double getRandom(int min, int max) {
        int range = max-min;
        return (random.nextDouble()*range)+min;
    }

}
