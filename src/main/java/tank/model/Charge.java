package tank.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.UUID;

public class Charge {
    public String id;
    public float Y;
    public float X;
    public float alpha = 0.0F;
    public boolean alive = true;
    float deltaX = 0.0F;
    float deltaY = 0.0F;
    float speed = 20.0F;
    private BufferedImage imgActive;
    public static float CHARGE_HEIGHT = 50F;
    public static float CHARGE_WIDTH = 20F;

    public Charge( float x, float y, float alpha) {
        this.id = UUID.randomUUID().toString();
        Y = y;
        X = x;
        this.alpha = alpha;
        URL imgURLActive = getClass().getResource("/charge1.png");
        try {
            imgActive = ImageIO.read(imgURLActive);
        } catch (IOException e) {
            e.printStackTrace();
        }
        deltaX = (float) (Math.cos(Math.toRadians(alpha))*speed);
        deltaY = (float) (Math.sin(Math.toRadians(alpha))*speed);
    }
    public void draw (Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(alpha),  (int) X, (int) Y);
        g.drawImage(imgActive, (int) X, (int) Y, (int) CHARGE_HEIGHT, (int) CHARGE_WIDTH, null);
        g2d.rotate(Math.toRadians(-alpha),  (int) X, (int) Y);
    }
    public void move (JFrame frame) {
        if ((X >= frame.getWidth()) || (X < 0) || (Y >= frame.getHeight()) || (Y < 0) ){
            alive = false;
        }

        X = X + deltaX;
        Y = Y + deltaY;
    }
}
