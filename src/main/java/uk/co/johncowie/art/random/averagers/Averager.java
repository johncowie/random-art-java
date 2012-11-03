package uk.co.johncowie.art.random.averagers;

import uk.co.johncowie.art.random.AdjacentPixelGatherer.AdjacentPixelGatherer;
import uk.co.johncowie.art.random.AdjacentPixelGatherer.PixelGatherer;
import uk.co.johncowie.art.random.RandomImage;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Averager {

    private PixelGatherer gatherer;

    public Averager(PixelGatherer gatherer) {
        this.gatherer = gatherer;
    }

    public Color getAverageColor(RandomImage image, int x, int y) {
        return averageColour(getAdjacentPixelColours(image, new Point(x, y)));
    }

    private List<Color> getAdjacentPixelColours(RandomImage image, Point p) {
        List<Color> colours = new ArrayList<Color>();
        for(Point point : this.gatherer.getAdjacentPixels(image.getWidth(), image.getHeight(), p)) {
            if(image.isColoured(point.x, point.y)) {
                colours.add(new Color(image.getRGB(point.x, point.y)));
            }
        }
        return colours;
    }

    protected Color averageColour(List<Color> colors) {
        if(colors.isEmpty()) {
            return null;
        }
        double redTotal = 0.0;
        double greenTotal = 0.0;
        double blueTotal = 0.0;
        for(Color c : colors) {
            redTotal += c.getRed();
            greenTotal += c.getGreen();
            blueTotal += c.getBlue();
        }
        int red = (int)redTotal/colors.size();
        int green = (int)greenTotal/colors.size();
        int blue = (int)blueTotal/colors.size();
        return new Color(red, green, blue);
    }
}
