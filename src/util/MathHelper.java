package util;

import java.awt.geom.Point2D;

public class MathHelper {
    public static Point2D getMidPoint(Point2D point1, Point2D point2){
        return new Point2D.Double((point1.getX() + point2.getX()) / 2, (point1.getY() + point2.getY()) / 2);
    }

    public static double getSlope(Point2D point1, Point2D point2) {
        return (point1.getX() - point2.getX() == 0) ? Double.POSITIVE_INFINITY : (point1.getY() - point2.getY()) / (point1.getX() - point2.getX());
    }

    public static double getSlopeOfBisector(double lineSlope) {
        return ((lineSlope == 0) ? Double.POSITIVE_INFINITY : -1 / lineSlope);
    }

    public static double getLineEquation(Point2D point, double slope) {
        return point.getY() - slope * point.getX();
    }

    public static Point2D getIntersection(Point2D[] points) {
        Point2D p0 = points[0];
        Point2D p1 = points[1];
        Point2D p2 = points[2];

        Point2D midpoint1 = getMidPoint(p0, p1);
        Point2D midpoint2 = getMidPoint(p2, p1);

        double perpendicularBisectorSlope1 = getSlopeOfBisector(getSlope(p1, p0));
        double perpendicularBisectorSlope2 = getSlopeOfBisector(getSlope(p1, p2));

        double perpendicularBisector1 = getLineEquation(midpoint1, perpendicularBisectorSlope1);
        double perpendicularBisector2 = getLineEquation(midpoint2, perpendicularBisectorSlope2);

        double intersectionX = (perpendicularBisector2 - perpendicularBisector1) / (perpendicularBisectorSlope1 - perpendicularBisectorSlope2);
        double intersectionY = perpendicularBisectorSlope1 * intersectionX + perpendicularBisector1;

        return new Point2D.Double(intersectionX, intersectionY);
    }

    public static double getAverageRadius(Point2D commonPoint, Point2D point1, Point2D point2) {
        double distance1 = point1.distance(commonPoint);
        double distance2 = point2.distance(commonPoint);

        return (distance1 + distance2) / 2;
    }

    public static double getStartAngle(Point2D midPoint1, Point2D center) {
        return -1 * (Math.toDegrees(Math.atan2(midPoint1.getY() - center.getY(), midPoint1.getX() - center.getX())));

    }

    public static double getArcAngle(Point2D midPoint1, Point2D midPoint2, Point2D center) {
        return Math.toDegrees(Math.atan2(midPoint1.getY() - center.getY(), midPoint1.getX() - center.getX()) -
                Math.atan2(midPoint2.getY() - center.getY(), midPoint2.getX() - center.getX()));
    }
}
