package tank.event;

import java.io.Serializable;

public class TankDto implements Serializable {
    private static final long serialVersionUID = 8038539938717817115L;
    int X=0;
    int Y=0;
    public void move(){
        X=X+1;
        Y=Y+1;
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
