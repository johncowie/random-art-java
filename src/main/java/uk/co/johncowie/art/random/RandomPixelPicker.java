package uk.co.johncowie.art.random;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class RandomPixelPicker implements IPixelPicker {

    private HashSet<Point> adjacentPoints;
    private HashSet<Point> populatedPoints;

    private int x;
    private int y;
    private Point start;


    public RandomPixelPicker(int x, int y, Point start) {
        this.adjacentPoints = new HashSet<Point>();
        this.populatedPoints = new HashSet<Point>();
        this.x = x;
        this.y = y;
        this.start = start;
    }

    @Override
    public Point getNextPixel() {
        if(populatedPoints.size() == 0) {
            updateLists(start);
            return start;
        } else {
            List<Point> points = new ArrayList<Point>(adjacentPoints);
            Collections.shuffle(points);
            Point p = points.get(0);
            updateLists(p);
            return p;
        }
    }

    void updateLists(Point point) {
        loadAdjacentPoints(point);
        populatedPoints.add(point);
        adjacentPoints.remove(point);
    }

    @Override
    public boolean hasNext() {
        return populatedPoints.size() < x*y;
    }

    private void loadAdjacentPoints(Point point) {
        for(int i = point.x-1; i <= point.x+1; i++) {
            for(int j = point.y-1; j <= point.y+1; j++) {
                if(i >= 0 && i < this.x && j >= 0 && j < this.y) {
                    // its in range
                    Point adjPoint = new Point(i, j);
                    if(!populatedPoints.contains(adjPoint)) {
                        adjacentPoints.add(adjPoint);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        RandomPixelPicker p = new RandomPixelPicker(10, 10, new Point(0, 0));
        int count = 0;
        while(p.hasNext()) {
            Point point = p.getNextPixel();
            System.out.println(count + "::" + point.x +":" +point.y);
            count++;
        }
    }


}
