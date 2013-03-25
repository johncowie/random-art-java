package uk.co.johncowie.art.random.numbers;

import java.util.Random;

public class StringNumberGenerator implements NumberGenerator {

    private Random random;

    public StringNumberGenerator(String string) {
        random = new Random(string.hashCode());
    }

    public double getRandom(double min, double max) {
        double range = max-min;
        return (random.nextDouble()*range)+min;
    }

    public int getRandomInt(int min, int max) {
        return (int)Math.floor(getRandom((double)min, (double)max+1));
    }

}
