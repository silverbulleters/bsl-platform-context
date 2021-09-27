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
import org.silverbulleters.bsl.platform.context.platform.Method;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;
import org.silverbulleters.bsl.platform.context.platform.Property;
import org.silverbulleters.bsl.platform.context.platform.TypeValue;
import org.silverbulleters.bsl.platform.context.types.ContextTypeKind;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeIdentifier;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeReference;
import org.silverbulleters.bsl.platform.context.types.PrimitiveType;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
      log.error("Cannot read data from file! " + e.getMessage());
      return Optional.empty();
    }


    final var unknownType = typeRefs.get(PlatformTypeIdentifier.UNKNOWN.value());
    var types = fillContextTypes(typeRefs, data, unknownType);
    var events = fillPlatformEvents(typeRefs, data, unknownType);

    var platformContext = PlatformContext.builder()
      .types(Collections.unmodifiableList(types))
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
    try (var stream = ReadDataCollector.class.getResourceAsStream("/identifiers.json")) {
      var mapper = ObjectMapperFactory.getObjectMapper();
      var data = mapper.readValue(stream, DataIdentifierCollector.class);
      var platformTypeReferences = data.getIdentifiers().stream()
        .map(value -> new PlatformTypeReference(value.getId()))
        .collect(Collectors.toMap(PlatformTypeReference::getValue, ref -> ref));

      platformTypeReferences.put(PlatformTypeIdentifier.UNKNOWN.value(), PrimitiveType.BOOLEAN_TYPE.getReference());
      platformTypeReferences.put(PlatformTypeIdentifier.UNDEFINED.value(), PrimitiveType.BOOLEAN_TYPE.getReference());
      platformTypeReferences.put(PlatformTypeIdentifier.STRING.value(), PrimitiveType.BOOLEAN_TYPE.getReference());
      platformTypeReferences.put(PlatformTypeIdentifier.NUMBER.value(), PrimitiveType.BOOLEAN_TYPE.getReference());
      platformTypeReferences.put(PlatformTypeIdentifier.DATE.value(), PrimitiveType.BOOLEAN_TYPE.getReference());
      platformTypeReferences.put(PlatformTypeIdentifier.BOOLEAN.value(), PrimitiveType.BOOLEAN_TYPE.getReference());
      platformTypeReferences.put(PlatformTypeIdentifier.TYPE.value(), PrimitiveType.BOOLEAN_TYPE.getReference());
      platformTypeReferences.put(PlatformTypeIdentifier.NULL.value(), PrimitiveType.BOOLEAN_TYPE.getReference());

      return platformTypeReferences;
    }
  }

  private List<Event> fillPlatformEvents(Map<String, PlatformTypeReference> typeRefs, DataFromCollector data,
                                         PlatformTypeReference unknownType) {

    List<Event> events = new ArrayList<>(data.getEvents().size());

    data.getEvents().forEach(eventFromData -> {
      var resource = new Resource(eventFromData.getNameRu(), eventFromData.getName());
      var typesByEvent = eventFromData.getTypes().stream()
        .map(identifier -> typeRefs.getOrDefault(identifier, unknownType))
        .collect(Collectors.toSet());
      events.add(new Event(resource, typesByEvent));
    });

    return events;
  }

  private List<ContextType> fillContextTypes(Map<String, PlatformTypeReference> typeRefs, DataFromCollector data,
                                             PlatformTypeReference unknownType) {

    List<ContextType> types = new ArrayList<>(data.getTypes().size());

    data.getTypes().forEach(typeFromData -> {
      var reference = typeRefs.getOrDefault(typeFromData.getId(), unknownType);
      var typeName = new Resource(typeFromData.getNameRu(), typeFromData.getName());
      var typeMethods = createMethodsFromData(typeFromData.getMethods());
      var typeProperties = createPropertiesFromData(typeFromData.getProperties());
      var typeValues = createTypeValuesFromData(typeFromData.getValues());
      var type = ContextType.builder()
        .reference(reference)
        .name(typeName)
        .kind(ContextTypeKind.valueByName(typeFromData.getKind()))
        .isPrimitive(false)
        .methods(typeMethods)
        .properties(typeProperties)
        .values(typeValues)
        .excludeFromGlobalContext(typeFromData.isExcludeFromGlobalContext())
        .build();
      types.add(type);
    });

    types.add(PrimitiveType.UNKNOWN_TYPE);
    types.add(PrimitiveType.NULL_TYPE);
    types.add(PrimitiveType.BOOLEAN_TYPE);
    types.add(PrimitiveType.DATE_TYPE);
    types.add(PrimitiveType.UNDEFINED_TYPE);
    types.add(PrimitiveType.STRING_TYPE);
    types.add(PrimitiveType.TYPE);
    types.add(PrimitiveType.NUMBER_TYPE);

    return types;
  }

  private static List<Method> createMethodsFromData(List<DataFromCollector.Method> methods) {
    return methods.stream().map(Method::createMethodFromData).collect(Collectors.toList());
  }

  private static List<Property> createPropertiesFromData(List<DataFromCollector.Property> properties) {
    return properties.stream().map(Property::createPropertyFromData).collect(Collectors.toList());
  }

  private static List<TypeValue> createTypeValuesFromData(List<DataFromCollector.TypeValue> values) {
    return values.stream().map(TypeValue::createFromData).collect(Collectors.toList());
  }

  private Optional<InputStream> getDataInputStream(String version) {
    var path = String.format("/data/%s.json", version);
    return Optional.ofNullable(ReadDataCollector.class.getResourceAsStream(path));
  }

  private DataFromCollector readDataFromPath(InputStream stream) throws IOException {
    var mapper = ObjectMapperFactory.getObjectMapper();
    return mapper.readValue(stream, DataFromCollector.class);
  }

  private static ContextType createContextTypeByIdentifier(PlatformTypeIdentifier identifier) {
    var name = identifier.value();
    return ContextType.builder()
      .name(new Resource(name, name))
      .reference(new PlatformTypeReference(name))
      .build();
  }


}
