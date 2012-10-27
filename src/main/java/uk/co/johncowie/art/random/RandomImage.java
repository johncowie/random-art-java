package uk.co.johncowie.art.random;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class RandomImage extends BufferedImage {

    private boolean[][] coloured;

    private int maxDeviation;
    private IPixelPicker pixelPicker;

    public RandomImage(int x, int y, int maxDeviation) {
        super(x, y, BufferedImage.TYPE_INT_RGB);
        this.maxDeviation = maxDeviation;
        int startX = (int)Math.floor(Math.random()*x);
        int startY = (int)Math.floor(Math.random()*y);
        this.pixelPicker = new RandomPixelPicker(x, y, new Point(startX, startY));
        coloured = new boolean[x][y];
        int count = 0;
        while(this.pixelPicker.hasNext()) {
            Point p = this.pixelPicker.getNextPixel();
            colourPixel(p.x, p.y);
            count++;
            if(count % 1000 == 0) {
                System.out.println(count);
            }
        }

    }

    public void colourPixel(int x, int y) {
        if(!coloured[x][y]) {
            List<Integer> adjacentPixels = getAdjacentPixelColours(x, y);
            if(adjacentPixels.isEmpty()) {
                Color color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
                this.setRGB(x, y, color.getRGB());
            } else {
                int averageColour = averageColour(adjacentPixels);
                this.setRGB(x, y, deviateColour(averageColour));
            }
        }
        coloured[x][y] = true;
    }

    private List<Integer> getAdjacentPixelColours(int x, int y) {
        List<Integer> colours = new ArrayList<Integer>();
        for(int i = x-1; i <= x+1; i++) {
            for(int j = y-1; j <= y+1; j++) {
                try {
                    if(coloured[i][j]) {
                        colours.add(this.getRGB(i, j));
                    }
                } catch(ArrayIndexOutOfBoundsException e) {

                }
            }
        }
        return colours;
    }

    private int deviateColour(int colour) {
        Color color = new Color(colour);
        int red = deviateComponent(color.getRed());
        int green = deviateComponent(color.getGreen());
        int blue = deviateComponent(color.getBlue());
        // System.out.println("RGBdev: " + red + "/" + green + "/" + blue);
        return new Color(red, green, blue).getRGB();
    }

    private int deviateComponent(int component) {
        double rand = Math.random();
        int deviation = (int)(Math.ceil((rand - 0.5) * maxDeviation));
        return normalise(component + deviation);
    }

    private int normalise(int number) {
        if(number > 255) {
            int val =  255 - (number % 255);
            // System.out.println(number + ": "  + val);
            return val;
        }
        if(number < 0) {
            return number * -1;
        }
        return number;
    }

    private int averageColour(List<Integer> rgbInts) {
        double redTotal = 0.0;
        double greenTotal = 0.0;
        double blueTotal = 0.0;
        for(Integer rgbInt : rgbInts) {
            Color c = new Color(rgbInt);
            redTotal += c.getRed();
            greenTotal += c.getGreen();
            blueTotal += c.getBlue();
        }
        int red = (int)redTotal/rgbInts.size();
        int green = (int)greenTotal/rgbInts.size();
        int blue = (int)blueTotal/rgbInts.size();
        return new Color(red, green, blue).getRGB();
    }


}
