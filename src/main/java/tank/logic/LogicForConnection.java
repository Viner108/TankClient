package tank.logic;

import tank.dto.TankDto;
import tank.model.Tank;
import tank.model.Tore;

import java.util.HashMap;
import java.util.Map;

public class LogicForConnection {

    public Map<Integer, Tank>  creatAndPutTanks(HashMap<Integer, TankDto> tankDto, Map<Integer, Tank> tankMap) {
        for (TankDto dto : tankDto.values()) {
            Tank tank =new Tank(dto.getId(),dto.getX(),dto.getY());
            tank.setAlpha(dto.getAlpha());
            tank.setDeltaAlpha(dto.getDeltaAlpha());
            tank.setSpeedAlpha(dto.getSpeedAlpha());
            tank.isFocusable=dto.isFocusable;
            Tore tore = new Tore(dto.getTore().X,dto.getTore().Y);
            tore.setAlpha(dto.getTore().alpha);
            tank.setTore(tore);
            tankMap.put(dto.getId(),tank);
        }
        return tankMap;
    }
}
