import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class RandomPixelPicker implements IPixelPicker {

    private HashSet<Point> populated;

    private int x;
    private int y;
    private Point start;


    public RandomPixelPicker(int x, int y, Point start) {
        this.populated = new HashSet<Point>();
        this.x = x;
        this.y = y;
        this.start = start;
    }



    @Override
    public Point getNextPixel() {
        if(this.populated.isEmpty()) {
            this.populated.add(start);
            return this.start;
        }
        Point point;
        while((point = getRandomAdjacentPoint(getRandomPopulatedPoint())) == null) {
            this.populated.add(point);
            return point;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return populated.size() < x*y;
    }

    private Point getRandomPopulatedPoint() {
        int pos = (int)Math.floor(Math.random()* populated.size());
        int count = 0;
        for(Point p : populated) {
            if(pos == count) {
                return p;
            }
            count++;
        }
        throw new RuntimeException("Point not found");
    }

    private Point getRandomAdjacentPoint(Point point) {
        List<Point> points = getAdjacentNewPoints(point);
        if(points.isEmpty()) {
            return null;
        }
        int randomNumber = (int)Math.floor(Math.random() * points.size());
        return points.get(randomNumber);
    }

    private List<Point> getAdjacentNewPoints(Point point) {
        List<Point> points = new ArrayList<Point>();
        for(int i = point.x-1; i <= point.x+1; i++) {
            for(int j = point.y-1; j <= point.y+1; j++) {
                if(i >= 0 && i < this.x && j >= 0 && j < this.y) {
                    // its in range
                    Point adjPoint = new Point(i, j);
                    if(!populated.contains(adjPoint)) {
                        points.add(adjPoint);
                    }
                }
            }
        }
        System.out.println(points);
        return points;
    }
}
