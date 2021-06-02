/*
 * This file is a part of BSL platform context.
 *
 * Copyright © 2020
 * Team SilverBulleters <team@silverbulleters.org> and contributors
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 *
 * BSL platform context is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * BSL platform context is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with BSL platform context.
 */
package org.silverbulleters.bsl.platform.context.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.silverbulleters.bsl.platform.context.internal.PlatformContextStorage;
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
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
@Slf4j
public class ContextInitializer {
  private static final URL PATH_TO_DATA_DIRECTORY = ContextInitializer.class.getClassLoader().getResource("data");

  // TODO подумать над параметром указание пути к внешним файлам
  private String resourceSrcDir = "";

  public static void setResourceSrcDir(String resourcePathDir) {
    ContextInitializer.resourceSrcDir = resourcePathDir;
  }

  public void initializeContext(PlatformContextStorage storage, List<PlatformEdition> editions) {
    for (PlatformEdition edition : editions) {
      loadContextByEdition(storage, edition);
    }
  }

  private void loadContextByEdition(PlatformContextStorage storage, PlatformEdition edition) {
    // TODO Читаем контекст из внешних файлов
    var pathToData = pathToData(edition.getVersion());
    if (pathToData.isEmpty()) {
      // TODO
      return;
    }

    DataFromCollector data;
    try {
      data = readDataFromPath(pathToData.get());
    } catch (IOException e) {
      e.printStackTrace();
      return;
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
    storage.getContextByEditions().put(edition, platformContext);
  }

  private DataFromCollector readDataFromPath(Path path) throws IOException {
    var mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    return mapper.readValue(path.toFile(), DataFromCollector.class);
  }


  @SneakyThrows
  private Optional<Path> pathToData(String version) {
    var dataDirectory = Optional.ofNullable(PATH_TO_DATA_DIRECTORY);
    if (dataDirectory.isEmpty()) {
      return Optional.empty();
    }
    var pathToDirectory = Path.of(dataDirectory.get().toURI());

    var path = Path.of(pathToDirectory.toString(), version + "_data.json");
    if (!path.toFile().exists()) {
      return Optional.empty();
    }

    return Optional.of(path);
  }

}
