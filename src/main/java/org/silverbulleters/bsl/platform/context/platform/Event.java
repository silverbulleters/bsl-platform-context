package org.silverbulleters.bsl.platform.context.platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.silverbulleters.bsl.platform.context.internal.BaseMethod;
import org.silverbulleters.bsl.platform.context.internal.GeneralMethodBuilder;
import org.silverbulleters.bsl.platform.context.types.ModuleType;
import org.silverbulleters.bsl.platform.context.types.ObjectType;

import java.util.Set;

@Getter
@ToString
public class Event extends BaseMethod {
    @JsonProperty("module_types")
    private Set<ModuleType> moduleTypes;
    @JsonProperty("object_types")
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
