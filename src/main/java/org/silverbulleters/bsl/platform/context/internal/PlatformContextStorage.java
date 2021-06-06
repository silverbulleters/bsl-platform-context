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
import org.jetbrains.annotations.NotNull;
import org.silverbulleters.bsl.platform.context.internal.util.ReadDataCollector;
import org.silverbulleters.bsl.platform.context.platform.ContextType;
import org.silverbulleters.bsl.platform.context.platform.Event;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeReference;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Внутреннее API над контекстами платформы
 */
public class PlatformContextStorage {
  /**
   * Контексты по версиям платформы
   */
  @Getter
  private final Map<PlatformEdition, PlatformContext> contextByEditions = new EnumMap<>(PlatformEdition.class);
  /**
   * Идентификаторы по ссылкам на типы
   */
  @Getter
  private final Map<String, PlatformTypeReference> typeRefs;

  public PlatformContextStorage() {
    typeRefs = ReadDataCollector.readPlatformTypeReference();
  }

  /**
   * Получить набор событий по версии платформы
   *
   * @param platformEdition - версия платформы
   * @return
   */
  public Set<Event> getEventsByPlatform(@NotNull PlatformEdition platformEdition) {
    var platformContext = Optional.ofNullable(contextByEditions.get(platformEdition));
    if (platformContext.isEmpty()) {
      return Collections.emptySet();
    }
    return platformContext.get().getEvents();
  }

  /**
   * Получить набор типов по версии платформы
   *
   * @param platformEdition - версия платформы
   * @return
   */
  public Set<ContextType> getTypesByPlatform(@NotNull PlatformEdition platformEdition) {
    var platformContext = Optional.ofNullable(contextByEditions.get(platformEdition));
    if (platformContext.isEmpty()) {
      return Collections.emptySet();
    }
    return platformContext.get().getTypes();
  }

}
