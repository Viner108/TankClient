package tank.view;


import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame  {
    public Frame(GraphicsConfiguration gc) {
        super(gc);
        frameInit();
        setTitle("Танки");
        getContentPane().setBackground(new Color(34, 139, 34));
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }



}
