package org.silverbulleters.bsl.platform.context.types;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TemplatePlatformTypeIdentifier {
  UNKNOWN("", "");

  private final String name;
  private final String nameRu;

  public String getName() {
    return name;
  }

  public String getNameRu() {
    return nameRu;
  }

}
