package org.silverbulleters.bsl.platform.context.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.silverbulleters.bsl.platform.context.platform.Event;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;

import java.util.List;

@Value
public class EventData {
    PlatformEdition platformVersion;
    List<Event> events;

    public EventData(@JsonProperty("platform_version") PlatformEdition platformVersion,
                     @JsonProperty("events") List<Event> events) {
        this.platformVersion = platformVersion;
        this.events = events;
    }
}
