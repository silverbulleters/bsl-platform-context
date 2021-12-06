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
package org.silverbulleters.bsl.platform.context;

import org.junit.jupiter.api.Test;
import org.silverbulleters.bsl.platform.context.internal.PlatformContextStorage;
import org.silverbulleters.bsl.platform.context.internal.util.ReadDataCollector;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;
import org.silverbulleters.bsl.platform.context.types.ContextTypeKind;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeIdentifier;
import org.silverbulleters.bsl.platform.context.types.PrimitiveType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class BSLPlatformContextTest {

  @Test
  void testReadingDataFromFile() {
    checkBSLContext(PlatformEdition.VERSION_8_3_10);
    checkBSLContext(PlatformEdition.VERSION_8_2_19);
    checkBSLContext(List.of(PlatformEdition.VERSION_8_2_19, PlatformEdition.VERSION_8_3_10));
  }

  @Test
  void testReadingGlobalMethodsFromFile() {
    var context = new BSLPlatformContext(List.of(PlatformEdition.VERSION_8_3_10));
    var globalMethods = context.getGlobalMethodsByPlatform(PlatformEdition.VERSION_8_3_10);
    assertThat(globalMethods).isNotEmpty();
  }

  @Test
  void testReadingMethodDataFromFile() {
    var context = new BSLPlatformContext(List.of(PlatformEdition.VERSION_8_3_10));
    var globalMethods = context.getGlobalMethodsByPlatform(PlatformEdition.VERSION_8_3_10);
    var strLenMethod = globalMethods.stream()
      .filter(method -> method.getName().getNameEn().equals("StrLen"))
      .findFirst().orElseThrow(IllegalArgumentException::new);

    assertThat(strLenMethod.getName().getNameEn()).isEqualTo("StrLen");
    assertThat(strLenMethod.getName().getNameRu()).isEqualTo("СтрДлина");
    assertThat(strLenMethod.isFunction()).isTrue();
  }

  @Test
  void testReadingObjectMethodsFromFile() {
    var context = new BSLPlatformContext(List.of(PlatformEdition.VERSION_8_3_10));
    var objectMethods = context.getTypeMethodsByPlatform(PlatformEdition.VERSION_8_3_10,
      PlatformTypeIdentifier.DOCUMENT_OBJECT);
    assertThat(objectMethods).isNotEmpty();
  }

  @Test
  void testAvailabilityOfDataForEachVersion() {
    Arrays.stream(PlatformEdition.values()).forEach(this::checkDataVersion);
  }

  @Test
  void testContextTypeKind() {
    var context = new BSLPlatformContext(List.of(PlatformEdition.VERSION_8_3_10));

    var contextType = context.getTypeByName(PlatformEdition.VERSION_8_3_10, "Запрос");
    assertThat(contextType.getKind()).isEqualTo(ContextTypeKind.TYPE);

    contextType = context.getTypeByName(PlatformEdition.VERSION_8_3_10, "WebЦвета");
    assertThat(contextType.getKind()).isEqualTo(ContextTypeKind.ENUM);
  }

  @Test
  void testTypeValue() {
    var edition = PlatformEdition.VERSION_8_3_10;
    var context = new BSLPlatformContext(List.of(edition));
    var enumWithoutValues = context.getTypesByPlatform(edition).stream()
      .filter(contextType -> contextType.getKind() == ContextTypeKind.ENUM)
      .filter(contextType -> contextType.getValues().isEmpty())
      .collect(Collectors.toList());
    assertThat(enumWithoutValues).isEmpty();

    var contextType = context.getTypeByName(edition, "WebЦвета");
    assertThat(contextType).isNotEqualTo(PrimitiveType.UNKNOWN_TYPE);
    assertThat(contextType.getValues()).isNotEmpty()
      .anyMatch(typeValue -> typeValue.getName().getNameRu().equals("Серебряный")
        && typeValue.getName().getNameEn().equals("Silver"));
  }

  @Test
  void testGlobalMethods() {
    var context = new BSLPlatformContext(List.of(PlatformEdition.VERSION_8_3_10));
    var editions = context.getPlatformEditionsByGlobalMethodName("стрнайти");
    assertThat(editions).isNotEmpty();
  }

  private void checkDataVersion(PlatformEdition platformEdition) {
    var contextStorage = new PlatformContextStorage();
    var data = ReadDataCollector.readToPlatformContext(platformEdition, contextStorage.getTypeRefs());
    assertThat(data).withFailMessage("Problem with version %s", platformEdition.getVersion())
      .isPresent();
  }

  private void checkBSLContext(PlatformEdition edition) {
    checkBSLContext(List.of(edition));
  }

  private void checkBSLContext(List<PlatformEdition> editions) {
    var context = new BSLPlatformContext(editions);
    editions.forEach(platformEdition -> {
      var events = context.getEventsByPlatform(platformEdition);
      assertThat(events).isNotEmpty();
      var types = context.getTypesByPlatform(platformEdition);
      assertThat(types).isNotEmpty();
    });
  }

}
