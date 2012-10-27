package uk.co.johncowie.art.random;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageDisplayer extends JFrame {

    JPanel canvas;

    public ImageDisplayer() {
        this.setSize(600, 600);
        this.canvas = new JPanel();
        this.add(canvas);
        this.setVisible(true);
    }

    public void displayImage(BufferedImage image) {
        this.canvas.add(new JLabel(new ImageIcon(image)));
        this.validate();
    }

    public static void main(String[] args) {
        ImageDisplayer displayer = new ImageDisplayer();
        try {
            displayer.displayImage(new RandomImage(400, 400));
        } catch(Exception e) {
            e.printStackTrace();
            displayer.setVisible(false);
            System.exit(1);
        }
    }


}
