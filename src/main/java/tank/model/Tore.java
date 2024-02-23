package tank.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Tore {
    public String id;
    public float Y;
    public float X;
    public   float alpha = 0.0F;
    public   float deltaAlpha = 0.0F;
    public float speedAlpha = 9.2F;
    public static float TORRE_HEIGHT = Tank.TANK_HEIGHT/3;
    public static float TORRE_WIDTH = Tank.TANK_WIDTH/3;
    private BufferedImage imgActive;
    public Tore(float x, float y) {
        X = x;
        Y = y;

        URL imgURLActive = getClass().getResource("/torre1.png");
        try {
            imgActive = ImageIO.read(imgURLActive);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void move (float baseX, float baseY) {

        X = baseX;
        Y = baseY;
        alpha = alpha + deltaAlpha;
    }
    public void draw (Graphics g, float baseX, float baseY, float baseAlpha) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(alpha), (X + TORRE_HEIGHT/4), (Y + TORRE_WIDTH/2));
        g.drawImage(imgActive, (int) (X ), (int) (Y ), (int) TORRE_HEIGHT, (int) TORRE_WIDTH, null);
        g2d.rotate(Math.toRadians(-(alpha)), (X + TORRE_HEIGHT/4), (Y + TORRE_WIDTH/2));
    }
    public void turnContrClockArrowDirection (){
        deltaAlpha = - speedAlpha;
    }

    public void turnByClockArrowDirection (){
        deltaAlpha = speedAlpha;
    }

    public void zeroSpeedAlpha(){
        deltaAlpha = 0;
    }
}
