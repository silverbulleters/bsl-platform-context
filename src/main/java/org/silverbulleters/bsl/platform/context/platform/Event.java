package org.silverbulleters.bsl.platform.context.platform;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.silverbulleters.bsl.platform.context.internal.BaseMethod;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeIdentifier;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Value
public class Event extends BaseMethod {
  Set<PlatformTypeIdentifier> types;

  public Event(Resource name, Set<PlatformTypeIdentifier> types) {
    super(name);
    this.types = types;
  }
}
