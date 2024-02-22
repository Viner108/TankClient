package tank.model;

import tank.event.KeyEventDto;
import tank.view.Scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
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
    float deltaX = 0.0F;
    float speed = 9.45F;
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
            X=1;
        }

        X=X + 10;
    }
    public void keyEventPressed(KeyEventDto e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: {
               X=X+1;
                break;
            }
            case KeyEvent.VK_S: {
                X=X-1;
                break;
            }
        }
    }

    public void keyEventReleased (KeyEventDto e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: {
                deltaX =  speed;
                break;
            }
            case KeyEvent.VK_S: {
                deltaX =  speed;
                break;
            }

        }
    }


}
