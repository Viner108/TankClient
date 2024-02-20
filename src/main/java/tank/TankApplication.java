package tank;


import tank.model.Tank;
import tank.view.DrawingTank;
import tank.view.Scene;

import javax.swing.*;
import java.awt.*;

public class TankApplication {

    public static void main(String[] args) {
        Scene scene =new Scene();
        scene.add(new DrawingTank(new Tank("1")));
        scene.setVisible(true);

    }


}
