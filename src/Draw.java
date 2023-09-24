import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Draw extends JPanel {

//    public static MathHelper mathHelper;
    public static void draw(Graphics g, Point2D[] points) {
        Graphics2D g2d = (Graphics2D) g;

        if (points.length != 3) {
            throw new IllegalArgumentException("Exactly 3 points are required.");
        }

        Point2D p0 = points[0];
        Point2D p1 = points[1];
        Point2D p2 = points[2];

        g2d.setColor(Color.BLACK);
        drawLine(g2d, p0, p1);
        drawLine(g2d, p1, p2);


        Point2D line1MidPoint = MathHelper.getMidPoint(p0, p1);
        Point2D line2MidPoint = MathHelper.getMidPoint(p1, p2);
        Point2D center = MathHelper.getIntersection(points);

        g2d.setColor(Color.BLUE);
        for (Point2D point : points) {
            drawSolidCircle(g2d, point, 5);
        }

        g2d.setColor(Color.RED);
        drawArc(g2d,line1MidPoint, line2MidPoint, center);
    }

    private static void drawLine(Graphics2D g2d, Point2D start, Point2D end) {
        g2d.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }

    private static void drawSolidCircle(Graphics2D g2d, Point2D center, double radius) {
        int x = (int) (center.getX() - radius);
        int y = (int) (center.getY() - radius);
        Ellipse2D circle = new Ellipse2D.Double(x, y, 2 * radius, 2 * radius);
        g2d.fill(circle);
    }

    private static void drawArc(Graphics2D g2d,Point2D midPoint1, Point2D midPoint2, Point2D center){
        double startAngle = -1 * (Math.toDegrees(Math.atan2(midPoint1.getY() - center.getY(), midPoint1.getX() - center.getX())));

        double arcAngle = Math.toDegrees(Math.atan2(midPoint1.getY() - center.getY(), midPoint1.getX() - center.getX()) -
                Math.atan2(midPoint2.getY() - center.getY(), midPoint2.getX() - center.getX()));

        double arcRadius = MathHelper.getAverageRadius(center, midPoint1, midPoint2);
        double arcWidth = 2 * arcRadius;

        if (arcAngle > 180){
            arcAngle =  -1* (360 - arcAngle);
        }


        System.out.println("arcAngle:"+arcAngle + "   StartAngle:"+startAngle);

        g2d.drawArc((int) (center.getX() - arcRadius), (int) (center.getY() - arcRadius), (int) (arcWidth), (int) (2 * arcRadius), (int) startAngle, (int) (arcAngle));
    }
}
