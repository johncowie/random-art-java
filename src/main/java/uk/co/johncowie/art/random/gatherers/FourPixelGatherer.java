package uk.co.johncowie.art.random.gatherers;

import java.awt.*;
import java.util.*;
import java.util.List;

public class FourPixelGatherer implements PixelGatherer {

    @Override
    public List<Point> getAdjacentPixels(int width, int height, Point p) {
        List<Point> points = new ArrayList<Point>();
        if(isInRange(width, height, new Point(p.x-1, p.y))) {
            points.add(new Point(p.x-1, p.y));
        }
        if(isInRange(width, height, new Point(p.x, p.y-1))) {
            points.add(new Point(p.x, p.y-1));
        }
        if(isInRange(width, height, new Point(p.x+1, p.y))) {
            points.add(new Point(p.x+1, p.y));
        }
        if(isInRange(width, height, new Point(p.x, p.y+1))) {
            points.add(new Point(p.x, p.y+1));
        }
        return points;
    }

    private boolean isInRange(int width, int height, Point p) {
        return p.x >= 0 && p.x < width && p.y >= 0 && p.y < height;
    }
}
