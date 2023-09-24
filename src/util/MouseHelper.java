package util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import window.Panel;

public class MouseHelper extends MouseAdapter {
    private final Point2D[] points;
    private Point2D draggedPoint = null;
    private int draggedPointIndex = -1;
    private final Panel panel;

    private final double LINE_LENGTH = 223.61;


    public MouseHelper(Point2D[] points, Panel panel) {
        this.points = points;
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

//        System.out.println(x+","+y);

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

            if (draggedPointIndex == 0 | draggedPointIndex == 2){
                moveLineEnd(draggedPointIndex, x, y);
            }

            if (draggedPointIndex == 1) {
                // Calculate the movement of point1
                double deltaX = x - points[1].getX();
                double deltaY = y - points[1].getY();

                // Update the positions of all points to move them together
                points[0] = new Point2D.Double(points[0].getX() + deltaX, points[0].getY() + deltaY);
                points[1] = new Point2D.Double(x, y);
                points[2] = new Point2D.Double(points[2].getX() + deltaX, points[2].getY() + deltaY);
            }

            panel.repaint(); // Repaint the panel
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        draggedPoint = null;
        draggedPointIndex = -1;
    }

    //Calculate the new point position while keeping fixed line length
    public void moveLineEnd(int pointIndex, double x , double y) {
        double deltaX2 = x - points[1].getX();
        double deltaY2 = y - points[1].getY();
        double currentDistance2 = Math.sqrt(deltaX2 * deltaX2 + deltaY2 * deltaY2);
        double scaleFactor2 = LINE_LENGTH / currentDistance2;
        points[pointIndex] = new Point2D.Double(points[1].getX() + deltaX2 * scaleFactor2, points[1].getY() + deltaY2 * scaleFactor2);
    }
}
