package tank.view;


import javax.swing.*;
import java.awt.*;

public class Scene extends JFrame {

    public Scene(){
        setTitle("Мое окно");


        setSize(500, 400);

        JButton button = new JButton("Нажми меня");


        add(button);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setVisible(true);
    }
}
