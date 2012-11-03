package uk.co.johncowie.art.random.AdjacentPixelGatherer;

import uk.co.johncowie.art.random.RandomImage;

import java.awt.*;
import java.util.*;
import java.util.List;

public class AdjacentPixelGatherer implements PixelGatherer{

    private int numberOfPixelsOut;

    public AdjacentPixelGatherer(int numberOfPixelsOut) {
        this.numberOfPixelsOut = numberOfPixelsOut;
    }

    public List<Point> getAdjacentPixels(int width, int height, Point p) {
        List<Point> points = new ArrayList<Point>();
        for(int i =p.x-this.numberOfPixelsOut; i <= p.x+this.numberOfPixelsOut; i++) {
            for(int j = p.y-this.numberOfPixelsOut; j <= p.y+this.numberOfPixelsOut; j++) {
                if(i >= 0 && i < width && j >= 0 && j < height) {
                    points.add(new Point(i, j));
                }
            }
        }
        return points;
    }

}
