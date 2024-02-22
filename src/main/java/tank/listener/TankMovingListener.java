package tank.listener;

import tank.event.TankStoppedEvent;

import java.util.EventListener;

public interface TankMovingListener extends EventListener {
    void tankStopped(TankStoppedEvent event);
}
