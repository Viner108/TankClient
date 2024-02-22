package tank.event;

import java.util.EventObject;

public class TankStoppedEvent extends EventObject {
    // Дополнительные поля с информацией о событии при необходимости
    // Например, информация о танке, который остановился.

    // Конструктор класса
    public TankStoppedEvent(Object source) {
        super(source);
        // Инициализация дополнительных полей здесь
    }
}
