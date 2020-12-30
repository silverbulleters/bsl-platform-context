package org.silverbulleters.bsl.platform.context.platform;

import lombok.Value;
import org.silverbulleters.bsl.platform.context.types.ObjectDescription;
import org.silverbulleters.bsl.platform.context.types.ObjectName;

import java.util.List;

@Value
public class Signature {
  ObjectName name;
  ObjectDescription description;
  String constructor;
  List<MethodParameter> parameters;
}
