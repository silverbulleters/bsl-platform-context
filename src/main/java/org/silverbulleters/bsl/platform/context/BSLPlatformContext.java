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

import org.silverbulleters.bsl.platform.context.internal.PlatformContextStorage;
import org.silverbulleters.bsl.platform.context.platform.ContextType;
import org.silverbulleters.bsl.platform.context.platform.Event;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;
import org.silverbulleters.bsl.platform.context.util.ContextInitializer;

import java.util.List;
import java.util.Set;

public class BSLPlatformContext {
  private PlatformContextStorage storage;

  public BSLPlatformContext(List<PlatformEdition> platformEditions) {
    initialize(platformEditions);
  }

  public Set<Event> getEventsByPlatform(PlatformEdition edition) {
    return storage.getEventsByPlatform(edition);
  }

  public Set<ContextType> getTypesByPlatform(PlatformEdition edition) {
    return storage.getTypesByPlatform(edition);
  }

  private void initialize(List<PlatformEdition> platformEditions) {
    storage = new PlatformContextStorage();
    ContextInitializer.initializeContext(storage, platformEditions);
  }

}
