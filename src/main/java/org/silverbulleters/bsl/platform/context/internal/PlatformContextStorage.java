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
package org.silverbulleters.bsl.platform.context.internal;

import lombok.Getter;
import org.silverbulleters.bsl.platform.context.platform.Event;
import org.silverbulleters.bsl.platform.context.platform.PlatformContext;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PlatformContextStorage {
  @Getter
  private final Map<PlatformEdition, PlatformContext> contextByEditions = new EnumMap<>(PlatformEdition.class);

  public List<Event> getEventsByPlatform(PlatformEdition platformEdition) {

    if (platformEdition == null) {
      return Collections.emptyList();
    }

    var platformContext = contextByEditions.get(platformEdition);
    return platformContext == null ? Collections.emptyList() : platformContext.getEvents();
  }
}
