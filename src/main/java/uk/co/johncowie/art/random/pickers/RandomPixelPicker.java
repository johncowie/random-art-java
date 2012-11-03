package uk.co.johncowie.art.random.pickers;

import uk.co.johncowie.art.random.AdjacentPixelGatherer.AdjacentPixelGatherer;
import uk.co.johncowie.art.random.numbers.NumberGenerator;
import uk.co.johncowie.art.random.numbers.StringNumberGenerator;

import java.awt.*;
import java.util.*;
import java.util.List;

public class RandomPixelPicker implements PixelPicker {

    private int x;
    private int y;
    private Point start;
    private String seed;
    private NumberGenerator generator;
    private AdjacentPixelGatherer gatherer;


    public RandomPixelPicker(Point start, String seed) {
        this.start = start;
        this.seed = seed;
        this.generator = new StringNumberGenerator(seed);
        this.gatherer = new AdjacentPixelGatherer(1);
    }

    @Override
    public List<Point> generateOrder(int x, int y) {
        this.x = x;
        this.y = y;
        HashSet<Point> adjacentPoints = new HashSet<Point>();
        HashSet<Point> addedPoints = new HashSet<Point>();
        List<Point> points = new ArrayList<Point>();
        for(int i = 0; i < x*y; i++){
            Point nextPoint = getRandomAdjacentPoint(adjacentPoints);
            if(nextPoint == null) {
                nextPoint = this.start;
            }
            adjacentPoints.remove(nextPoint);
            addedPoints.add(nextPoint);
            points.add(nextPoint);
            loadAdjacentPoints(nextPoint, adjacentPoints, addedPoints);
        }
        return points;
    }

    private Point getRandomAdjacentPoint(Set<Point> adjacentPoints) {
        int rand = (int)Math.floor(this.generator.getRandom(0, adjacentPoints.size()));
        int count = 0;
        for(Point point : adjacentPoints) {
            if(count == rand) {
                return point;
            }
            count++;
        }
        return null;
    }

    private void loadAdjacentPoints(Point p, Set<Point> adjacentPoints, Set<Point> addedPoints) {
        for(Point point : this.gatherer.getAdjacentPixels(x, y, p)) {
            if(!addedPoints.contains(point)) {
                adjacentPoints.add(point);
            }
        }
    }
}
