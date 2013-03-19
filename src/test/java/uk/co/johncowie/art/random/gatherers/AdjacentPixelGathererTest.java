package uk.co.johncowie.art.random.gatherers;

import org.junit.Test;

import java.awt.*;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class AdjacentPixelGathererTest {

    private PixelGatherer g = new AdjacentPixelGatherer(1);

    @Test
    public void testGatherer() {
        checkPixels(g.getAdjacentPixels(3, 3, new Point(0, 0)),
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 0),
                new Point(1, 1)
        );
        checkPixels(g.getAdjacentPixels(3, 3, new Point(1, 1)),
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 2),
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2),
                new Point(2, 0),
                new Point(2, 1),
                new Point(2, 2));
        checkPixels(g.getAdjacentPixels(3, 3, new Point(2, 2)),
                new Point(2, 2),
                new Point(2, 1),
                new Point(1, 2),
                new Point(1, 1));
    }

    private void checkPixels(Collection<Point> points, Point... pixels) {
        assertEquals(pixels.length, points.size());
        for(Point point : pixels) {
            assertTrue(points.contains(point));
        }
    }

}
