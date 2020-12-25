package org.silverbulleters.bsl.platform.context;

import lombok.Value;

import java.util.List;

@Value
public class Signature {
  String name;
  String constructor;
  List<MethodParameter> parameters;
  String description;
}
