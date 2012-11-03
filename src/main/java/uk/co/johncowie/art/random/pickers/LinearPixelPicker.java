package uk.co.johncowie.art.random.pickers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LinearPixelPicker implements PixelPicker {


    public List<Point> generateOrder(int width, int height) {
        List<Point> order = new ArrayList<Point>();
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                order.add(new Point(i, j));
            }
        }
        return order;
    }

}
