package uk.co.johncowie.art.random.pickers;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SquarePixelPicker implements PixelPicker {

    private Point start;
    private int width;
    private int height;

    public SquarePixelPicker(Point start) {
        this.start = start;
    }

    public List<Point> generateOrder(int x, int y) {
        this.width = x;
        this.height = y;
        List<Point> order = new ArrayList<Point>();
        order.add(start);
        for(int i = 1; i < width; i++) {
            loadTopEdge(i, order);
            loadRightEdge(i, order);
            loadBottomEdge(i, order);
            loadLeftEdge(i, order);
        }
        return order;
    }

    private void loadTopEdge(int dist, List<Point> order) {
        for(int x = start.x-dist; x < start.x+dist; x++) {
            addToOrder(new Point(x, start.y-dist), order);
        }
    }

    private void loadBottomEdge(int dist, List<Point> order) {
        for(int x = start.x+dist; x > start.x-dist; x--) {
            addToOrder(new Point(x, start.y + dist), order);
        }
    }

    private void loadLeftEdge(int dist, List<Point> order) {
        for(int y = start.y+dist; y > start.y-dist; y--) {
            addToOrder(new Point(start.x - dist, y), order);
        }
    }
    private void loadRightEdge(int dist, List<Point> order) {
        for(int y = start.y-dist; y < start.y+dist; y++) {
            addToOrder(new Point(start.x + dist, y), order);
        }
    }

    private void addToOrder(Point p, List<Point> order) {
        if(p.x >= 0 && p.x < width && p.y >= 0 && p.y < height) {
            order.add(p);
        }
    }
}
