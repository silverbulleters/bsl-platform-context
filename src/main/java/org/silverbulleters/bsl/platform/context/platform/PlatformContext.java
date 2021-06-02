package org.silverbulleters.bsl.platform.context.platform;

import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Value
@Builder
public class PlatformContext {
  @NotNull
  Set<ContextType> types;
  @NotNull
  Set<Event> events;
}
