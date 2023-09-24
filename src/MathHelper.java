import java.awt.geom.Point2D;

public class MathHelper {
    public Point2D getMidPoint;

//    public static MathHelper calculations(Point2D[] points) {
//        Point2D p0 = points[0];
//        Point2D p1 = points[1];
//        Point2D p2 = points[2];
//
//        // Calculate the midpoints of the line segments
//        Point2D midpoint1 = new Point2D.Double((p0.getX() + p1.getX()) / 2, (p0.getY() + p1.getY()) / 2);
//        Point2D midpoint2 = new Point2D.Double((p2.getX() + p1.getX()) / 2, (p2.getY() + p1.getY()) / 2);
//
//        // Calculate the slopes of the line segments
//        double slope1 = (p1.getX() - p0.getX() == 0) ? Double.POSITIVE_INFINITY : (p1.getY() - p0.getY()) / (p1.getX() - p0.getX());
//        double slope2 = (p1.getX() - p2.getX() == 0) ? Double.POSITIVE_INFINITY : (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
//
//        // Calculate the negative reciprocals of the slopes to get the slopes of perpendicular bisectors
//        double perpendicularSlope1 = (slope1 == 0) ? Double.POSITIVE_INFINITY : -1 / slope1;
//        double perpendicularSlope2 = (slope2 == 0) ? Double.POSITIVE_INFINITY : -1 / slope2;
//
//        // Calculate the equations of the perpendicular bisectors in point-slope form
//        double intercept1 = midpoint1.getY() - perpendicularSlope1 * midpoint1.getX();
//        double intercept2 = midpoint2.getY() - perpendicularSlope2 * midpoint2.getX();
//
//        // Calculate the x-coordinate of the intersection point
//        double intersectionX = (intercept2 - intercept1) / (perpendicularSlope1 - perpendicularSlope2);
//
//        // Calculate the y-coordinate of the intersection point
//        double intersectionY = perpendicularSlope1 * intersectionX + intercept1;
//
//        double distance1 = midpoint1.distance(intersectionX, intersectionY);
//        double distance2 = midpoint2.distance(intersectionX, intersectionY);
//        double averageDistance = (distance1 + distance2) / 2;
//
//        return new MathHelper(new Point2D.Double(intersectionX, intersectionY), averageDistance);
//    }

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
}
