import javax.swing.*;
import java.awt.geom.Point2D;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            Point2D[] points = new Point2D[]{
                    new Point2D.Double(100, 100),
                    new Point2D.Double(300, 200),
                    new Point2D.Double(200, 400)
            };

            JFrame frame = new JFrame("Line Drawing Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Panel panel = new Panel(points);
            frame.add(panel);
            frame.setSize(600, 600);
            frame.setVisible(true);
        });
    }
}
