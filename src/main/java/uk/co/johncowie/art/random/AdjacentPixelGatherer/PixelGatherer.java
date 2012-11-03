package uk.co.johncowie.art.random.AdjacentPixelGatherer;

import java.awt.*;
import java.util.*;

public interface PixelGatherer {

    public java.util.List<Point> getAdjacentPixels(int width, int height, Point p);

}
