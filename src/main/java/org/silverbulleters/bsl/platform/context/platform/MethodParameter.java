package org.silverbulleters.bsl.platform.context.platform;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  public MethodParameter(@JsonProperty("name") Resource name,
                         @JsonProperty("description") Resource description,
                         @JsonProperty("required") boolean required,
                         @JsonProperty("types") List<ContextType> types) {

    this.name = name;
    this.description = description;
    this.required = required;
    this.types = types;
  }
}
