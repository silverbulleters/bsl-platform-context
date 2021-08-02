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
package org.silverbulleters.bsl.platform.context.platform;

import lombok.Builder;
import lombok.Value;
import org.silverbulleters.bsl.platform.context.types.ContextTypeKind;
import org.silverbulleters.bsl.platform.context.types.PlatformTypeReference;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.util.Collections;
import java.util.List;

/**
 * Определение типа платформы
 */
@Value
@Builder
public class ContextType {
  /**
   * Имя на двух языках
   */
  Resource name;
  /**
   * Вид типа
   */
  @Builder.Default
  ContextTypeKind kind = ContextTypeKind.TYPE;
  /**
   * Ссылка на тип
   */
  PlatformTypeReference reference;
  /**
   * Признак примитива
   */
  boolean isPrimitive; // FIXME: переименовать
  /**
   * Методы типа
   */
  @Builder.Default
  List<Method> methods = Collections.emptyList();
  /**
   * Свойства типа
   */
  @Builder.Default
  List<Property> properties = Collections.emptyList();
  /**
   * Исключен из глобального контекста
   */
  @Builder.Default
  boolean excludeFromGlobalContext = false;
}
