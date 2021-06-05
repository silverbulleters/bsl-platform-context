package org.silverbulleters.bsl.platform.context.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.silverbulleters.bsl.platform.context.internal.loader.DataFromCollector;
import org.silverbulleters.bsl.platform.context.platform.ContextType;
import org.silverbulleters.bsl.platform.context.platform.Event;
import org.silverbulleters.bsl.platform.context.platform.PlatformContext;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeIdentifier;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
@Slf4j
public class ReadDataCollector {

  public Optional<PlatformContext> readToPlatformContext(PlatformEdition edition) {
    var dataStreamOptional = getDataInputStream(edition.getVersion());
    if (dataStreamOptional.isEmpty()) {
      // TODO
      return Optional.empty();
    }

    DataFromCollector data;
    try {
      data = readDataFromPath(dataStreamOptional.get());
    } catch (IOException e) {
      e.printStackTrace();
      return Optional.empty();
    }

    Set<ContextType> types = new HashSet<>(data.getTypes().size());
    Set<Event> events = new HashSet<>(data.getEvents().size());

    data.getTypes().forEach(typeFromData -> {
      var id = PlatformTypeIdentifier.valueById(typeFromData.getId());
      var resource = new Resource(typeFromData.getNameRu(), typeFromData.getName());

      var type = ContextType.builder()
        .id(id)
        .name(resource)
        .isPrimitive(false)
        // methods
        .build();

      types.add(type);
    });

    data.getEvents().forEach(eventFromData -> {
      var resource = new Resource(eventFromData.getNameRu(), eventFromData.getName());
      var typesByEvent = eventFromData.getTypes().stream()
        .map(PlatformTypeIdentifier::valueById)
        .collect(Collectors.toSet());
      events.add(new Event(resource, typesByEvent));
    });

    var platformContext = PlatformContext.builder()
      .types(Collections.unmodifiableSet(types))
      .events(events)
      .build();

    return Optional.of(platformContext);
  }

  public Optional<InputStream> getDataInputStream(String version) {
    var path = String.format("/data/%s.json", version);
    return Optional.ofNullable(ReadDataCollector.class.getResourceAsStream(path));
  }

  private DataFromCollector readDataFromPath(InputStream stream) throws IOException {
    var mapper = ObjectMapperFactory.getObjectMapper();
    return mapper.readValue(stream, DataFromCollector.class);
  }

}
