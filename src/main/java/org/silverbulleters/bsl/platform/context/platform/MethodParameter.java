package org.silverbulleters.bsl.platform.context.platform;

import lombok.Value;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.util.List;

@Value
public class MethodParameter {
  Resource name;
  Resource description;
  boolean required;
  // Типы должны содержать как примитивы, так и обычные типы(ex. справочник, элементHTML)
  List<ContextType> types;
}
