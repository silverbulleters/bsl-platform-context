package org.silverbulleters.bsl.platform.context;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
abstract class BaseMethod {
  private String name;
  private List<Signature> signatures;
}
