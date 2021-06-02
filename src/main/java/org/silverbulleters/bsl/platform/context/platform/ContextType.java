package org.silverbulleters.bsl.platform.context.platform;

import lombok.Builder;
import lombok.Value;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeIdentifier;
import org.silverbulleters.bsl.platform.context.types.Resource;

@Value
@Builder
public class ContextType {
  Resource name;
  PlatformTypeIdentifier id;
  boolean isPrimitive;
  // свойства
  // методы
}
