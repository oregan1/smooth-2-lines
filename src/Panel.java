import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Panel extends JPanel {
    private final Point2D[] points;

    public Panel(Point2D[] points) {
        this.points = points;
        MouseHelper mouseHelper = new MouseHelper(points, this);
        addMouseListener(mouseHelper);
        addMouseMotionListener(mouseHelper);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw.draw(g, points);
    }
}