package org.silverbulleters.bsl.platform.context.platform;

import lombok.Value;

@Value
public class ContextType {
    String name;
    String id;
    boolean isPrimitive;

    // TODO добавить поддержку свойств (ex. ИмяЭлемента, ТипУзла, и т.д.)

    // TODO дать доступ к характеристикам типа (ex. события, свойства методы):
    //    public List<Event> getEvents() {
    //        return events.filter(parent == ourParent);
    //    }
}
