package org.silverbulleters.bsl.platform.context;

import lombok.Value;

import java.util.List;

@Value
public class MethodParameter {
  String name;
  boolean required;
  List<PlatformType> types;
  String description;
}
