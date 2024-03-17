package Tasks.Task1;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Pocket {
    private final int x;
    private final int y;
    private int radius;

    public Pocket(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fill(new Ellipse2D.Double(x, y, radius*2, radius*2));
    }


    public boolean isBallInPocket(int x, int y) {
        double collideRadius = this.radius*2;
        double distance = Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
        return distance <= collideRadius;
    }
}
