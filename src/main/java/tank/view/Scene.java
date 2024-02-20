package tank.view;


import tank.model.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class Scene extends JFrame  {
    public Scene() {
        setTitle("Танки");
        getContentPane().setBackground(new Color(34, 139, 34));
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



}
