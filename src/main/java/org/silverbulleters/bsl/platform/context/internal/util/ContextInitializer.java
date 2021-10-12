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

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.silverbulleters.bsl.platform.context.internal.PlatformContextStorage;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;

import java.util.List;

@UtilityClass
@Slf4j
public class ContextInitializer {

  public void initializeContext(PlatformContextStorage storage, List<PlatformEdition> editions) {
    for (PlatformEdition edition : editions) {
      loadContextByEdition(storage, edition);
    }
  }

  public void loadGlobalMethods(PlatformContextStorage storage) {
    storage.getGlobalMethodsWithPlatformEditions().putAll(ReadDataCollector.readToGlobalMethods());
  }

  private void loadContextByEdition(PlatformContextStorage storage, PlatformEdition edition) {
    var platformContext = ReadDataCollector.readToPlatformContext(edition, storage.getTypeRefs());
    if (platformContext.isEmpty()) {
      return;
    }
    storage.getContextByEditions().put(edition, platformContext.get());
  }

}
