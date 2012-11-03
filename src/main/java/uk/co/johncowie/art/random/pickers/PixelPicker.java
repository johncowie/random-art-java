package uk.co.johncowie.art.random.pickers;


import java.awt.*;
import java.util.List;

public interface PixelPicker {

    public List<Point> generateOrder(int x, int y);

}
