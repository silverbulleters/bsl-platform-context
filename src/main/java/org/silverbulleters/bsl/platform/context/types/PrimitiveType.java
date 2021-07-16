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
package org.silverbulleters.bsl.platform.context.types;

import org.silverbulleters.bsl.platform.context.platform.ContextType;

/**
 * Инициализированные примитивные типы платформы
 */
public class PrimitiveType {
  public static final ContextType UNKNOWN_TYPE;
  public static final ContextType NULL_TYPE;
  public static final ContextType BOOLEAN_TYPE;
  public static final ContextType DATE_TYPE;
  public static final ContextType UNDEFINED_TYPE;
  public static final ContextType STRING_TYPE;
  public static final ContextType TYPE;
  public static final ContextType NUMBER_TYPE;

  static {
    UNKNOWN_TYPE = createContextTypeByIdentifier(PlatformTypeIdentifier.UNKNOWN);
    NULL_TYPE = createContextTypeByIdentifier(PlatformTypeIdentifier.NULL);
    BOOLEAN_TYPE = createContextTypeByIdentifier(PlatformTypeIdentifier.BOOLEAN);
    DATE_TYPE = createContextTypeByIdentifier(PlatformTypeIdentifier.DATE);
    UNDEFINED_TYPE = createContextTypeByIdentifier(PlatformTypeIdentifier.NULL);
    STRING_TYPE = createContextTypeByIdentifier(PlatformTypeIdentifier.STRING);
    TYPE = createContextTypeByIdentifier(PlatformTypeIdentifier.TYPE);
    NUMBER_TYPE = createContextTypeByIdentifier(PlatformTypeIdentifier.NUMBER);
  }

  private PrimitiveType() {
    // none
  }

  private static ContextType createContextTypeByIdentifier(PlatformTypeIdentifier identifier) {
    var name = identifier.value();
    return ContextType.builder()
      .name(new Resource(name, name))
      .kind(ContextTypeKind.TYPE)
      .reference(new PlatformTypeReference(name))
      .build();
  }

}
