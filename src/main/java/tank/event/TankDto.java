package tank.event;

import java.awt.*;
import java.io.Serializable;

public class TankDto implements Serializable {
    private static final long serialVersionUID = 8038539938717817115L;
    int id;
    private int speed = 5;
    public int alpha = 0;
    int deltaX = 0;
    int deltaY = 0;
    int deltaAlpha = 0;
    int speedAlpha = 2;

    public TankDto(int id) {
        this.id = id;
    }

    private int X = 0;
    private int Y = 0;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public int getDeltaAlpha() {
        return deltaAlpha;
    }

    public void setDeltaAlpha(int deltaAlpha) {
        this.deltaAlpha = deltaAlpha;
    }

    public int getSpeedAlpha() {
        return speedAlpha;
    }

    public void setSpeedAlpha(int speedAlpha) {
        this.speedAlpha = speedAlpha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public String toString() {
        return "TankDto{" +
               "id=" + id +
               ", X=" + X +
               ", Y=" + Y +
               '}';
    }
}
