package uk.co.johncowie.art.random.gatherers;

import java.awt.*;

public interface PixelGatherer {

    public java.util.List<Point> getAdjacentPixels(int width, int height, Point p);

}
