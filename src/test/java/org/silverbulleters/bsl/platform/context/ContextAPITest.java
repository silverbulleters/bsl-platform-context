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
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;
import org.silverbulleters.bsl.platform.context.types.PrimitiveType;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ContextAPITest {
  private static final PlatformEdition PLATFORM_EDITION = PlatformEdition.VERSION_8_3_10;

  @Test
  void testGlobalProperties() {
    var context = new BSLPlatformContext(List.of(PLATFORM_EDITION));
    var properties = context.getGlobalPropertiesByPlatform(PLATFORM_EDITION);

    assertThat(properties).hasSize(65)
      .anyMatch(property -> property.getName().getNameRu().equals("Справочники"));
  }

  @Test
  void testGetTypeByName() {
    var context = new BSLPlatformContext(List.of(PLATFORM_EDITION));
    var names = new Resource("Запрос", "Query");
    checkGetTypeByName(context, PLATFORM_EDITION, names);

    var type = context.getTypeByName(PLATFORM_EDITION, "ЧтотоДругое");
    assertThat(type).isEqualTo(PrimitiveType.UNKNOWN_TYPE);
  }

  private static void checkGetTypeByName(BSLPlatformContext context, PlatformEdition edition, Resource names) {
    var type = context.getTypeByName(edition, names.getNameRu());
    assertThat(type).isNotEqualTo(PrimitiveType.UNKNOWN_TYPE);
    assertThat(type.getName()).isEqualTo(names);

    type = context.getTypeByName(edition, names.getNameEn());
    assertThat(type).isNotEqualTo(PrimitiveType.UNKNOWN_TYPE);
    assertThat(type.getName()).isEqualTo(names);
  }

}
