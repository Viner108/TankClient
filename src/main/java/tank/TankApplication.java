package tank;


import tank.model.Tank;
import tank.view.DrawingTank;
import tank.view.Scena;
import tank.view.Scene;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment;

public class TankApplication {

    public static void main(String[] args) {
        Scene scene =new Scene();
//        scene.add(new DrawingTank(new Tank("1")));
        scene.add(new Scena(scene));
        scene.setVisible(true);




    }


}
