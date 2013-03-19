package uk.co.johncowie.art.random;

import uk.co.johncowie.art.random.gatherers.AdjacentPixelGatherer;
import uk.co.johncowie.art.random.averagers.Averager;
import uk.co.johncowie.art.random.deviators.Deviator;
import uk.co.johncowie.art.random.pickers.PixelPicker;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RandomImage extends BufferedImage {

    private boolean[][] coloured;

    private Deviator deviator;
    private Averager averager;
    private Color seedColour;

    public RandomImage(int x, int y, Deviator deviator, Color seedColour, PixelPicker pixelPicker) {
        super(x, y, BufferedImage.TYPE_INT_RGB);
        this.deviator = deviator;
        this.averager = new Averager(new AdjacentPixelGatherer(1));
        this.seedColour = seedColour;
        coloured = new boolean[x][y];
        for(Point point : pixelPicker.generateOrder(x, y)) {
            colourPixel(point);
        }
    }

    public boolean isColoured(int x, int y) {
        return coloured[x][y];
    }

    public void colourPixel(Point p) {
        int x = p.x;
        int y = p.y;
        if(!coloured[x][y]) {
            Color averageColor = averager.getAverageColor(this, x, y);
            if(averageColor == null) {
                this.setRGB(x, y, seedColour.getRGB());
            } else {
                this.setRGB(x, y, deviator.deviateColor(averageColor).getRGB());
            }
        }
        coloured[x][y] = true;
    }


}
