package uk.co.johncowie.art.random.pickers;

import uk.co.johncowie.art.random.numbers.NumberGenerator;

import java.awt.*;

public class PixelPickerFactory {

    public static final int LINEAR = 1;
    public static final int SQUARE = 2;
    public static final int CLUSTER = 3;

    public static PixelPicker getPixelPicker(int id, Point startPoint, String seed) {
        switch(id) {
            case LINEAR :
                return new LinearPixelPicker();
            case SQUARE :
                return new SquarePixelPicker(startPoint);
            case CLUSTER :
                return new RandomPixelPicker(startPoint, seed);
        }
        return null;
    }
}
