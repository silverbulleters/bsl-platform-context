package org.silverbulleters.bsl.platform.context.util;

import lombok.SneakyThrows;
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
import java.net.URL;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
@Slf4j
public class ReadDataCollector {
  private final URL URL_DATA_DIRECTORY = ReadDataCollector.class.getClassLoader().getResource("data");

  public Optional<PlatformContext> readToPlatformContext(PlatformEdition edition) {
    var pathToData = pathToData(edition.getVersion());
    if (pathToData.isEmpty()) {
      // TODO
      return Optional.empty();
    }

    DataFromCollector data;
    try {
      data = readDataFromPath(pathToData.get());
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

  public Optional<Path> pathToData(String version) {
    var pathToDataDirectory = getPathDataDirectory();
    if (pathToDataDirectory.isEmpty()) {
      return Optional.empty();
    }
    var path = Path.of(pathToDataDirectory.get().toString(), version + ".json");
    if (!path.toFile().exists()) {
      return Optional.empty();
    }

    return Optional.of(path);
  }

  private DataFromCollector readDataFromPath(Path path) throws IOException {
    var mapper = ObjectMapperFactory.getObjectMapper();
    return mapper.readValue(path.toFile(), DataFromCollector.class);
  }

  @SneakyThrows
  private Optional<Path> getPathDataDirectory() {
    if (URL_DATA_DIRECTORY == null) {
      return Optional.empty();
    }
    return Optional.of(Path.of(URL_DATA_DIRECTORY.toURI()));
  }

}
