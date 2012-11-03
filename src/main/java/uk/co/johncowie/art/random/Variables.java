package uk.co.johncowie.art.random;

import org.springframework.beans.factory.annotation.Value;

public class Variables {

    private Integer width = 300;
    private Integer height = 300;
    private String seed = "Hello World";
    private Integer deviation = 20;
    private Integer picker = 1;
    private String seedColour = "0xFF0000";
    private int seedX;
    private int seedY;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public Integer getDeviation() {
        return deviation;
    }

    public void setDeviation(Integer deviation) {
        this.deviation = deviation;
    }

    public Integer getPicker() {
        return picker;
    }

    public void setPicker(Integer picker) {
        this.picker = picker;
    }

    public String getSeedColour() {
        return seedColour;
    }

    public void setSeedColour(String seedColour) {
        this.seedColour = seedColour;
    }

    public int getSeedX() {
        return seedX;
    }

    public void setSeedX(int seedX) {
        this.seedX = seedX;
    }

    public int getSeedY() {
        return seedY;
    }

    public void setSeedY(int seedY) {
        this.seedY = seedY;
    }
}
