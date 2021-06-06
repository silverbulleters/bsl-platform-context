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
package org.silverbulleters.bsl.platform.context.internal.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.silverbulleters.bsl.platform.context.internal.PlatformContext;
import org.silverbulleters.bsl.platform.context.internal.PlatformContextStorage;
import org.silverbulleters.bsl.platform.context.internal.data.DataFromCollector;
import org.silverbulleters.bsl.platform.context.internal.data.DataIdentifierCollector;
import org.silverbulleters.bsl.platform.context.platform.ContextType;
import org.silverbulleters.bsl.platform.context.platform.Event;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeIdentifier;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeReference;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Служебный класс для чтения внешних данных о контексте платформы
 */
@UtilityClass
@Slf4j
public class ReadDataCollector {

  /**
   * Прочитать внешние данные о контексте в объект
   *
   * @param edition  - версия платформы
   * @param typeRefs - соответствие идентификаторов и ссылок на типы
   *                 из {@link PlatformContextStorage#getTypesByPlatform}
   * @return
   */
  public Optional<PlatformContext> readToPlatformContext(PlatformEdition edition, Map<String, PlatformTypeReference> typeRefs) {
    var dataStreamOptional = getDataInputStream(edition.getVersion());
    if (dataStreamOptional.isEmpty()) {
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

    final var unknownType = typeRefs.get(PlatformTypeIdentifier.UNKNOWN.id());

    data.getTypes().forEach(typeFromData -> {
      var reference = typeRefs.getOrDefault(typeFromData.getId(), unknownType);
      var resource = new Resource(typeFromData.getNameRu(), typeFromData.getName());
      var type = ContextType.builder()
        .reference(reference)
        .name(resource)
        .isPrimitive(false)
        // methods
        .build();
      types.add(type);
    });

    data.getEvents().forEach(eventFromData -> {
      var resource = new Resource(eventFromData.getNameRu(), eventFromData.getName());
      var typesByEvent = eventFromData.getTypes().stream()
        .map(identifier -> typeRefs.getOrDefault(identifier, unknownType))
        .collect(Collectors.toSet());
      events.add(new Event(resource, typesByEvent));
    });

    var platformContext = PlatformContext.builder()
      .types(Collections.unmodifiableSet(types))
      .events(events)
      .build();

    return Optional.of(platformContext);
  }

  /**
   * Прочитать данные об идентификаторах типов в соответствие идентификаторов и ссылок на типы
   *
   * @return
   */
  @SneakyThrows
  public Map<String, PlatformTypeReference> readPlatformTypeReference() {
    var stream = ReadDataCollector.class.getResourceAsStream("/identifiers.json");
    var mapper = ObjectMapperFactory.getObjectMapper();
    var data = mapper.readValue(stream, DataIdentifierCollector.class);
    return data.getIdentifiers().stream()
      .map(value -> new PlatformTypeReference(value.getId()))
      .collect(Collectors.toMap(PlatformTypeReference::getValue, ref -> ref));
  }

  private Optional<InputStream> getDataInputStream(String version) {
    var path = String.format("/data/%s.json", version);
    return Optional.ofNullable(ReadDataCollector.class.getResourceAsStream(path));
  }

  private DataFromCollector readDataFromPath(InputStream stream) throws IOException {
    var mapper = ObjectMapperFactory.getObjectMapper();
    return mapper.readValue(stream, DataFromCollector.class);
  }

}
