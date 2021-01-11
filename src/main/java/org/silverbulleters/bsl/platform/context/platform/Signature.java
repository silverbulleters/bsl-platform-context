package org.silverbulleters.bsl.platform.context.platform;

import lombok.Value;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.util.List;

@Value
public class Signature {
  Resource name;
  Resource description;
  String constructor;
  List<MethodParameter> parameters;
}
