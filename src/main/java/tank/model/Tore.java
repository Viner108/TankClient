package tank.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Tore {
    public List<Charge> chargeList = new ArrayList<>();
    public java.util.Map<String, Charge> chargeMap = new ConcurrentHashMap<>();
    public String id;
    public float Y;
    public float X;
    public   float alpha = 0.0F;
    public   float deltaAlpha = 0.0F;
    public float speedAlpha = 1.5F;
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
    public void move (float baseX, float baseY, JFrame frame) {
        X = baseX;
        Y = baseY;
        alpha = alpha + deltaAlpha;
        chargeList.removeIf(c->!c.alive);
        chargeList.forEach( c -> c.move(frame));
    }
    public void shoot(float baseAlpha){
        chargeList.add(new Charge(X, Y, alpha + baseAlpha));
    }
    public void draw (Graphics g, float baseX, float baseY, float baseAlpha) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(alpha), (X + TORRE_HEIGHT/4), (Y + TORRE_WIDTH/2));
        g.drawImage(imgActive, (int) (X ), (int) (Y ), (int) TORRE_HEIGHT, (int) TORRE_WIDTH, null);
        g2d.rotate(Math.toRadians(-(alpha)), (X + TORRE_HEIGHT/4), (Y + TORRE_WIDTH/2));
    }
    public void drawCharges(Graphics g){
        chargeMap.values().forEach(clientCharge -> {
            clientCharge.draw(g);
        });
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getDeltaAlpha() {
        return deltaAlpha;
    }

    public void setDeltaAlpha(float deltaAlpha) {
        this.deltaAlpha = deltaAlpha;
    }
}
