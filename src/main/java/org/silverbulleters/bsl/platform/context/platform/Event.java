package org.silverbulleters.bsl.platform.context.platform;

import lombok.Getter;
import org.silverbulleters.bsl.platform.context.internal.BaseMethod;
import org.silverbulleters.bsl.platform.context.internal.GeneralMethodBuilder;
import org.silverbulleters.bsl.platform.context.types.ModuleType;
import org.silverbulleters.bsl.platform.context.types.ObjectType;

import java.util.Set;

@Getter
public class Event extends BaseMethod {
    private Set<ModuleType> moduleTypes;
    private Set<ObjectType> objectTypes;

    private Event() {
        // noop
    }

    public static EventBuilder builder() {
        return new EventBuilder();
    }

    public static class EventBuilder extends GeneralMethodBuilder<Event> {

        private EventBuilder() {
            method = new Event();
        }

        public void setModuleType(Set<ModuleType> types) {
            method.moduleTypes = types;
        }

        public void setObjectType(Set<ObjectType> types) {
            method.objectTypes = types;
        }

    }

}
