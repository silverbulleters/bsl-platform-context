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

import org.silverbulleters.bsl.platform.context.util.ContextInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BSLPlatformContext {
  private PlatformContextStorage storage;
  private List<PlatformEdition> editions = new ArrayList<>();

  public BSLPlatformContext() {
    storage = new PlatformContextStorage();
    editions.add(PlatformEdition.VERSION_8_3_10);
  }

  public void initialize() {
    ContextInitializer.initializeContext(storage, editions);
  }

  public List<PlatformType> platformTypeVersionsByEdition(PlatformEdition edition) {
    var context = storage.getContextByEditions().get(edition);
    return context.getTypes();
  }

  public Optional<PlatformType> getTypeByName(String name) {
    var context = storage.getContextByEditions().get(editions.get(0));
    return context.getTypes().stream()
      .filter(typeVersion -> typeVersion.getName().equalsIgnoreCase(name))
      .findAny();
  }

//  public List<MethodDefinition> methodDefinitionsByPlatformTypeVersion(PlatformTypeVersion typeVersion) {
//    var context = storage.getContextByEditions().get(typeVersion.getPlatformEdition());
//
//  }

}
