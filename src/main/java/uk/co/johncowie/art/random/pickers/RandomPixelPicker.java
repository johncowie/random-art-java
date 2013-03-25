package uk.co.johncowie.art.random.pickers;

import uk.co.johncowie.art.random.RandomSet;
import uk.co.johncowie.art.random.gatherers.AdjacentPixelGatherer;
import uk.co.johncowie.art.random.numbers.NumberGenerator;
import uk.co.johncowie.art.random.numbers.StringNumberGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RandomPixelPicker implements PixelPicker {

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
    public List<Point> generateOrder(int width, int height) {
        RandomSet<Point> adjacentPoints = new RandomSet<Point>(this.seed);
        adjacentPoints.add(this.start);
        HashSet<Point> addedPoints = new HashSet<Point>();
        List<Point> points = new ArrayList<Point>();
        for(int i = 0; i < width*height; i++){
            Point nextPoint = adjacentPoints.removeRandom();
            addedPoints.add(nextPoint);
            points.add(nextPoint);
            for(Point point : this.gatherer.getAdjacentPixels(width, height, nextPoint)) {
                if(!addedPoints.contains(point)) {
                    adjacentPoints.add(point);
                }
            }
        }
        return points;
    }
}
