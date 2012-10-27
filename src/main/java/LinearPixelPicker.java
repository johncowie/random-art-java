import java.awt.*;

public class LinearPixelPicker implements IPixelPicker {


    int x = 0;
    int y = 0;
    int width;
    int height;

    public LinearPixelPicker(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Point getNextPixel() {
        x++;
        if(x >= width) {
            x = 0;
            y++;
        }
        return new Point(x, y);
    }

    @Override
    public boolean hasNext() {
        return !(x == width-1 && y == height-1);
    }
}
