package org.silverbulleters.bsl.platform.context.platform;

import lombok.Value;
import org.silverbulleters.bsl.platform.context.types.ObjectDescription;
import org.silverbulleters.bsl.platform.context.types.ObjectName;

import java.util.List;

@Value
public class MethodParameter {
  ObjectName name;
  ObjectDescription description;
  boolean required;
  // Типы должны содержать как примитивы, так и обычные типы(ex. справочник, элементHTML)
  List<ContextType> types;
}
