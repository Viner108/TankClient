package tank.event;

import java.awt.*;
import java.io.Serializable;

public class TankDto implements Serializable {
    private static final long serialVersionUID = 8038539938717817115L;
    int id;

    public TankDto() {
        super();
        System.out.println("dsgg");
    }

    public TankDto(int id) {
        this.id = id;
        System.out.println("sdgdfgh");
    }

    private int X = 0;
    private int Y = 0;

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
               "X=" + X +
               ", Y=" + Y +
               '}';
    }
}
