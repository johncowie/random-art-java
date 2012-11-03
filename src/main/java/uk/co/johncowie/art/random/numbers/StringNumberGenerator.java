package uk.co.johncowie.art.random.numbers;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
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

    public static void main(String[] args) {
        NumberGenerator n = new StringNumberGenerator("asdfas");
        for(int i = 0; i < 200; i++) {
            System.out.println(n.getRandom(5, 10));
        }
    }

}
