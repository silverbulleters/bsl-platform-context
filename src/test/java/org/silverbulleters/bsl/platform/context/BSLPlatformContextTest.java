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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BSLPlatformContextTest {

  @Test
  void testReadingDataFromFile() {
    checkBSLContext(PlatformEdition.VERSION_8_3_10);
    checkBSLContext(PlatformEdition.VERSION_8_2_19);
    checkBSLContext(List.of(PlatformEdition.VERSION_8_2_19, PlatformEdition.VERSION_8_3_10));
  }

  @Test
  void testAvailabilityOfDataForEachVersion() {
    Arrays.stream(PlatformEdition.values()).forEach(this::checkDataVersion);
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
