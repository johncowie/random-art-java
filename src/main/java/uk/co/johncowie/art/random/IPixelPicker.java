package uk.co.johncowie.art.random;

import java.awt.*;

public interface IPixelPicker {
    public Point getNextPixel();
    public boolean hasNext();
}
