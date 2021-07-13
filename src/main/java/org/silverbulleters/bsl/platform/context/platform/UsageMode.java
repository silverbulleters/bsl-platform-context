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

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Режим использования свойства типа
 */
@RequiredArgsConstructor
public enum UsageMode {
  /**
   * Только чтение
   */
  READ_ONLY("ReadOnly"),
  /**
   * Только запись
   */
  WRITE_ONLY("WriteOnly"),
  /**
   * Чтение и запись
   */
  READ_AND_WRITE("ReadAndWrite");

  @Accessors(fluent = true)
  @Getter
  private final String value;

  public static UsageMode valueByName(String name) {
    for (var value : UsageMode.values()) {
      if (value.value().equals(name)) {
        return value;
      }
    }
    throw new IllegalArgumentException();
  }
}
