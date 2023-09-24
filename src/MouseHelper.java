import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class MouseHelper extends MouseAdapter {
    private final Point2D[] points;
    private Point2D draggedPoint = null;
    private int draggedPointIndex = -1;
    private final Panel panel; // Reference to the Panel class

    public MouseHelper(Point2D[] points, Panel panel) {
        this.points = points;
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        for (int i = 0; i < points.length; i++) {
            double pointX = points[i].getX();
            double pointY = points[i].getY();
            double distance = Math.sqrt((x - pointX) * (x - pointX) + (y - pointY) * (y - pointY));
            if (distance <= 5) {
                draggedPoint = points[i];
                draggedPointIndex = i;
                break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggedPoint != null) {
            double x = e.getX();
            double y = e.getY();

            // Calculate the new positions to maintain the distance of 223.61 between points[1] and points[0]
            if (draggedPointIndex == 0) {
                double deltaX1 = x - points[1].getX();
                double deltaY1 = y - points[1].getY();
                double currentDistance1 = Math.sqrt(deltaX1 * deltaX1 + deltaY1 * deltaY1);
                double scaleFactor1 = 223.61 / currentDistance1;
                points[0] = new Point2D.Double(points[1].getX() + deltaX1 * scaleFactor1, points[1].getY() + deltaY1 * scaleFactor1);
            }

            // Calculate the new positions to maintain the distance of 223.61 between points[1] and points[2]
            if (draggedPointIndex == 2) {
                double deltaX2 = x - points[1].getX();
                double deltaY2 = y - points[1].getY();
                double currentDistance2 = Math.sqrt(deltaX2 * deltaX2 + deltaY2 * deltaY2);
                double scaleFactor2 = 223.61 / currentDistance2;
                points[2] = new Point2D.Double(points[1].getX() + deltaX2 * scaleFactor2, points[1].getY() + deltaY2 * scaleFactor2);
            }

            panel.repaint(); // Repaint the panel
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        draggedPoint = null;
        draggedPointIndex = -1;
    }
}
