package tank.model;

import tank.event.KeyEventDto;
import tank.view.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
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
    public float alpha = 0.0F;
    public static int BG_BORDER = 3;
    float deltaX = 0.0F;
    float speed = 9.45F;
    float deltaY = 0.0F;
    float deltaAlpha = 0.0F;
    float speedAlpha = 6.4F;
//    public float alpha = 0.0F;
//    public static int BG_BORDER = 3;

    public Tank(String id, float X, float Y) {
        this.X = X;
        this.Y = Y;
        URL imgURLActive = getClass().getResource("/tankActive.png");
        this.id = id;
        try {
            imageTank = ImageIO.read(imgURLActive);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(34, 139, 34));
        g2d.rotate(Math.toRadians(alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
        g.fillRect((int) X - BG_BORDER, (int) Y - BG_BORDER, (int) TANK_HEIGHT + BG_BORDER, (int) TANK_WIDTH + BG_BORDER);
        g2d.rotate(Math.toRadians(-alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));

        g2d.rotate(Math.toRadians(alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
        g.drawImage(imageTank, (int) X, (int) Y, (int) TANK_HEIGHT, (int) TANK_WIDTH, null);

    }

    public void moveX(JFrame frame) {
        if (frame.getWidth() <= X) {
            X = 1;
        }
        if (0 >= X) {
            X = frame.getWidth();
        }
        if(frame.getHeight() <= Y){
            Y = 1;
        }
        if (0 >= Y) {
            Y = frame.getHeight();
        }
        X = X + deltaX;
        Y = Y + deltaY;
        alpha = alpha + deltaAlpha;
    }

    public void keyEventPressed(KeyEventDto e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: {
                deltaAlpha = -speedAlpha;
                break;
            }
            case KeyEvent.VK_D: {
                deltaAlpha = speedAlpha;
                break;
            }
            case KeyEvent.VK_W: {
//                deltaX=+speed;
                deltaX = (float) Math.cos(Math.toRadians(alpha)) * speed;
                deltaY = (float) Math.sin(Math.toRadians(alpha)) * speed;
                break;
            }
            case KeyEvent.VK_S: {
//                deltaX=-speed;
                deltaX = -(float) Math.cos(Math.toRadians(alpha)) * speed;
                deltaY = -(float) Math.sin(Math.toRadians(alpha)) * speed;
                break;
            }
        }
    }

    public void keyEventReleased(KeyEventDto e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: {
                deltaAlpha = 0.0F;
                break;
            }
            case KeyEvent.VK_D: {
                deltaAlpha = 0.0F;
                break;
            }
            case KeyEvent.VK_W: {
                deltaX = 0;
                deltaY = 0;
                break;
            }
            case KeyEvent.VK_S: {
                deltaX = 0;
                deltaY = 0;
                break;
            }

        }
    }


}
