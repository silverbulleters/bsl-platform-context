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

import org.jetbrains.annotations.NotNull;
import org.silverbulleters.bsl.platform.context.internal.PlatformContextStorage;
import org.silverbulleters.bsl.platform.context.internal.util.ContextInitializer;
import org.silverbulleters.bsl.platform.context.platform.ContextType;
import org.silverbulleters.bsl.platform.context.platform.Event;
import org.silverbulleters.bsl.platform.context.platform.Method;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeIdentifier;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeReference;

import java.util.List;
import java.util.stream.Collectors;

/**
 * API над контекстом платформы
 */
public class BSLPlatformContext {
  /**
   * Хранилище контекста платформы
   */
  private final PlatformContextStorage storage = new PlatformContextStorage();

  public BSLPlatformContext(@NotNull List<PlatformEdition> platformEditions) {
    initialize(platformEditions);
  }

  /**
   * Получить события типов по версии платформы
   *
   * @param edition - версия платформы
   * @return набор событий типов
   */
  @NotNull
  public List<Event> getEventsByPlatform(@NotNull PlatformEdition edition) {
    return storage.getEventsByPlatform(edition);
  }

  /**
   * @param edition - версия платформы
   * @return список методов глобального контекста, доступных в переданной версии платформы
   */
  @NotNull
  public List<Method> getGlobalMethodsByPlatform(@NotNull PlatformEdition edition) {
    var globalContextReference = new PlatformTypeReference(PlatformTypeIdentifier.GLOBAL_CONTEXT.value());

    return storage.getTypesByPlatform(edition).stream()
        .filter(type -> type.getReference().equals(globalContextReference))
        .flatMap(type -> type.getMethods().stream())
        .collect(Collectors.toList());
  }

  /**
   * @param edition - - версия платформы
   * @param typeIdentifier - идентификатор типа, методы которого необходимо получить
   * @return список методов, доступных для переданного типа
   */
  @NotNull
  public List<Method> getTypeMethodsByPlatform(@NotNull PlatformEdition edition, PlatformTypeIdentifier typeIdentifier) {
    var typeReference = new PlatformTypeReference(typeIdentifier.value());

    return storage.getTypesByPlatform(edition).stream()
        .filter(type -> type.getReference().equals(typeReference))
        .flatMap(type -> type.getMethods().stream())
        .collect(Collectors.toList());
  }

  /**
   * Получить список типов по версии платформы
   *
   * @param edition - версия платформы
   * @return набор типов
   */
  @NotNull
  public List<ContextType> getTypesByPlatform(@NotNull PlatformEdition edition) {
    return storage.getTypesByPlatform(edition);
  }

  private void initialize(List<PlatformEdition> platformEditions) {
    ContextInitializer.initializeContext(storage, platformEditions);
  }

}
