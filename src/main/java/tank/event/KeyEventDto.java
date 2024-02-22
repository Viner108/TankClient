package tank.event;

import java.awt.event.KeyEvent;

public class KeyEventDto {
    int keyCode;
    public static KeyEventDto fromKeyEvent(KeyEvent e){
        KeyEventDto dto = new KeyEventDto();
        dto.setKeyCode(e.getKeyCode());
        return dto;
    }

    public int getKeyCode () {
        return keyCode;
    }

    private void setKeyCode (int keyCode) {
        this.keyCode = keyCode;
    }
}
