package org.silverbulleters.bsl.platform.context.platform;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.util.List;

@Data
@NoArgsConstructor
public class Signature {
  Resource name;
  Resource description;
  String constructor;
  List<MethodParameter> parameters;
}
