package org.silverbulleters.bsl.platform.context.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum TemplatePlatformTypeIdentifier {
  UNKNOWN("");

  @Getter
  private final String id;

  public static PlatformTypeIdentifier valueById(String id) {
    return Arrays.stream(PlatformTypeIdentifier.values())
      .filter(value -> value.getId().equals(id))
      .findAny()
      .orElse(PlatformTypeIdentifier.UNKNOWN);
  }

}
