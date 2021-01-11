package org.silverbulleters.bsl.platform.context.internal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.silverbulleters.bsl.platform.context.platform.ContextType;
import org.silverbulleters.bsl.platform.context.platform.Signature;
import org.silverbulleters.bsl.platform.context.types.ContextItem;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.util.List;

@Getter
@Setter(AccessLevel.PACKAGE)
public abstract class BaseMethod implements ContextItem {
  protected Resource name;
  protected Resource description;
  protected ContextType parent;
  protected List<Signature> signatures;
  protected List<ContextType> returnTypes;
}
