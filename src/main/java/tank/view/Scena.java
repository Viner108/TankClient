package tank.view;

import tank.connection.OutputConnection;
import tank.event.KeyEventDto;
import tank.event.TankDto;
import tank.model.Tank;
import tank.server.ServerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class Scena extends JPanel implements KeyListener {
    Tank tank;
    private TankDto tankDto=new TankDto();
    public ServerThread tankThread;
    public OutputConnection outputConnection;

    public Scena(Tank tank) {
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();
        grabFocus();
        this.tank = tank;
        addKeyListener(this);
        this.setFocusable(true);
        grabFocus();
        setBackground(new Color(34, 139, 34));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tank.draw(g);
        tankDto.draw(g);
        repaint();
    }

    public void updateTankMapWithDto(Tank tank) {
        tank.paintCharges();
    }

    public TankDto getTankDto() {
        return tankDto;
    }

    public void setTankDto(TankDto tankDto) {
        this.tankDto = tankDto;
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        tankThread.keyPressed(KeyEventDto.fromKeyEvent(e));
        outputConnection.keyPressed(KeyEventDto.fromKeyEvent(e,true));
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        tankThread.keyReleased(KeyEventDto.fromKeyEvent(e));
        outputConnection.keyReleased(KeyEventDto.fromKeyEvent(e,false));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
