package org.silverbulleters.bsl.platform.context.internal.loader;

import lombok.Data;

import java.util.List;

@Data
public class DataFromCollector {
  private String platformVersion;
  private List<Type> types;
  private List<Event> events;

  @Data
  public static class Type {
    private String id;
    private String name;
    private String nameRu;
  }

  @Data
  public static class Event {
    private String name;
    private String nameRu;
    private List<String> types;
  }

}

