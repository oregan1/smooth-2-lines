import window.Frame;

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

            Frame frame = new Frame("smooth-apex-with-arc", points);
            frame.createAndShowGUI();
        });
    }
}
