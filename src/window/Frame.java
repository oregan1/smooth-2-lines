package window;

import javax.swing.*;
import java.awt.geom.Point2D;

public class Frame {
    private final String title;
    private final Point2D[] points;

    public Frame(String title, Point2D[] points) {
        this.title = title;
        this.points = points;
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel(points);
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}

