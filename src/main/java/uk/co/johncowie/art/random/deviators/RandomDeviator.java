package uk.co.johncowie.art.random.deviators;

import uk.co.johncowie.art.random.numbers.NumberGenerator;

import java.awt.*;

public class RandomDeviator implements Deviator {

    NumberGenerator generator;
    private int maxDeviation;
    private int lean;

    public RandomDeviator(NumberGenerator generator, int maxDeviation, int lean) {
        this.generator = generator;
        this.maxDeviation = maxDeviation;
        this.lean = lean;
    }

    @Override
    public Color deviateColor(Color color) {
        int red = deviateComponent(color.getRed());
        int green = deviateComponent(color.getGreen());
        int blue = deviateComponent(color.getBlue());
        return new Color(red, green, blue);
    }

    private int deviateComponent(int component) {
        return normalise(component + getDeviation());
    }

    private int getDeviation() {
        return generator.getRandomInt(-1*maxDeviation, maxDeviation+lean);
    }

    private int normalise(int number) {
        if(number > 255) {
            return  255 - (number % 255);
        }
        if(number < 0) {
            return -1*(number % 255);
        }
        return number;
    }
}
