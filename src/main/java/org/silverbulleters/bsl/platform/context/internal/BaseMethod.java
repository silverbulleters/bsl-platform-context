package org.silverbulleters.bsl.platform.context.internal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.silverbulleters.bsl.platform.context.types.ContextItem;
import org.silverbulleters.bsl.platform.context.types.Resource;

@Getter
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
public abstract class BaseMethod implements ContextItem {
  protected final Resource name;
  // description
  // signatures
  // returnTypes
}
