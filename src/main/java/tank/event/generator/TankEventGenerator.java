package tank.event.generator;

import tank.event.TankStoppedEvent;
import tank.listener.TankMovingListener;

import javax.swing.event.EventListenerList;

public class TankEventGenerator {
    private EventListenerList listenerList = new EventListenerList();

    public void addTankMovingListener(TankMovingListener listener) {
        listenerList.add(TankMovingListener.class, listener);
    }

    public void removeTankMovingListener(TankMovingListener listener) {
        listenerList.remove(TankMovingListener.class, listener);
    }

    // Метод для уведомления слушателей о событии
    public void fireTankStoppedEvent(TankStoppedEvent tankStoppedEvent) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == TankMovingListener.class) {
                ((TankMovingListener)listeners[i+1]).tankStopped(tankStoppedEvent);
            }
        }
    }
}
