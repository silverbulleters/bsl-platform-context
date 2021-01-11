package org.silverbulleters.bsl.platform.context.platform;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ContextType {
    String name;
    String id;
    boolean isPrimitive;

    @JsonCreator
    public ContextType(@JsonProperty("name") String name,
                       @JsonProperty("id") String id,
                       @JsonProperty("is_primitive") boolean isPrimitive) {
        this.name = name;
        this.id = id;
        this.isPrimitive = isPrimitive;
    }

    // TODO добавить поддержку свойств (ex. ИмяЭлемента, ТипУзла, и т.д.)

    // TODO дать доступ к характеристикам типа (ex. события, свойства методы):
    //    public List<Event> getEvents() {
    //        return events.filter(parent == ourParent);
    //    }
}
