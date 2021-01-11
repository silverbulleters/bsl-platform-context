package org.silverbulleters.bsl.platform.context.platform;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.silverbulleters.bsl.platform.context.internal.BaseMethod;

import java.util.Collections;
import java.util.List;

@Data
public class PlatformContext {

    @NotNull
    private List<Event> events = Collections.emptyList();
    @NotNull
    private List<BaseMethod> globalMethods = Collections.emptyList();
    @NotNull
    private List<BaseMethod> typeMethods = Collections.emptyList();
}
