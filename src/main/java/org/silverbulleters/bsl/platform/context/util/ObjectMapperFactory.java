package org.silverbulleters.bsl.platform.context.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectMapperFactory {
  @Getter(lazy = true)
  private final ObjectMapper objectMapper = createObjectMapper();

  private ObjectMapper createObjectMapper() {
    var mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper;
  }

}
