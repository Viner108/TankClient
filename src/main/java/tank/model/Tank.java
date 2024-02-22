package tank.model;

import tank.view.Scene;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;



public class Tank {
    public String id;
    private BufferedImage imageTank;
    public static float TANK_HEIGHT = 109F;
    public static float TANK_WIDTH = 82F;
    public float Y;
    public float X;
//    public float alpha = 0.0F;
//    public static int BG_BORDER = 3;

    public Tank(String id, float X, float Y) {
        this.X=X;
        this.Y=Y;
        URL imgURLActive = getClass().getResource("/tankActive.png");
        this.id = id;
        try {
            imageTank = ImageIO.read(imgURLActive);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(0, 0, 0));
//        g2d.rotate(Math.toRadians(alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
//        g.fillRect((int) X - BG_BORDER, (int) Y - BG_BORDER, (int) TANK_HEIGHT + BG_BORDER, (int) TANK_WIDTH + BG_BORDER);
//        g2d.rotate(Math.toRadians(-alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
//
//        g2d.rotate(Math.toRadians(alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
        g.drawImage(imageTank, (int) X, (int) Y, (int) TANK_HEIGHT, (int) TANK_WIDTH, null);

    }
    public void moveX(Scene scene){
        if (scene.getWidth()<=X){
            X=0;
        }
        X=X+1;
    }


}
