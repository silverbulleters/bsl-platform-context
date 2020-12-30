package org.silverbulleters.bsl.platform.context.platform;

import lombok.Data;
import org.silverbulleters.bsl.platform.context.internal.BaseMethod;

import java.util.List;

@Data
public class PlatformContext {
    private List<Event> events;
    private List<BaseMethod> globalMethods;
    private List<BaseMethod> typeMethods;
}
