package uk.co.johncowie.art.random.gatherers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdjacentPixelGatherer implements PixelGatherer{

    private int d;

    public AdjacentPixelGatherer(int d) {
        this.d = d;
    }

    public List<Point> getAdjacentPixels(int width, int height, Point p) {
        List<Point> points = new ArrayList<Point>();
        for(int i =p.x-d; i <= p.x+d; i++) {
            for(int j = p.y-d; j <= p.y+d; j++) {
                if(i >= 0 && i < width && j >= 0 && j < height
                        // && !(i == p.x && j == p.y)
                        ) {
                    points.add(new Point(i, j));
                }
            }
        }
        return points;
    }

}
