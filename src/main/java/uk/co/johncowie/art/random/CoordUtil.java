package uk.co.johncowie.art.random;

import java.awt.*;

public class CoordUtil {

    public static Point toCoord(int width, int i) {
        return new Point(i / width, i % width);
    }

    public static int toIndex(int width, Point point) {
        return point.y * width + point.x;
    }

    public static void main(String[] args) {
        System.out.println(toIndex(4, toCoord(4, 0)));
    }

}
