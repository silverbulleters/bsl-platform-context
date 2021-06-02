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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BSLPlatformContextTest {

  @Test
  void testReadingEventsFromFile() {
    final var edition = PlatformEdition.VERSION_8_3_10;
    var platform = List.of(edition);
    var context = new BSLPlatformContext(platform);

    var events = context.getEventsByPlatform(edition);
    assertThat(events).isNotEmpty();

    var types = context.getTypesByPlatform(edition);
    assertThat(types).isNotEmpty();
  }

}
