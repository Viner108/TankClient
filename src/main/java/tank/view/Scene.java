package tank.view;


import tank.model.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment;

public class Scene extends JFrame {
    String msg = "";
    Button yes, no, maybe;

    public Scene() {
        setLayout(new FlowLayout());
        setTitle("Танки");
        setSize(250, 150);
        yes = new Button("Yes");
        no = new Button("No");
        maybe = new Button("Undecided ");
        add(yes);
        add(no);
        add(maybe);
        yes.addActionListener((ae) -> {
            msg = "You pressed" + ae.getActionCommand();
            repaint();
        });
        no.addActionListener((ae) -> {
            msg = "You pressed" + ae.getActionCommand();
            repaint();
        });
        maybe.addActionListener((ae) -> {
            msg = "You pressed" + ae.getActionCommand();
            repaint();
        });
        getContentPane().setBackground(new Color(34, 139, 34));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }


    @Override
    public void paint(Graphics g) {
        g.drawString(msg, 20, 100);
    }
}
