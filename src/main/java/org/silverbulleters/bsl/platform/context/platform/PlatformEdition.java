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

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Поддерживаемые версии платформы
 */
@AllArgsConstructor
public enum PlatformEdition {
  VERSION_8_2_19("8_2_19"),
  VERSION_8_3_3("8_3_3"),
  VERSION_8_3_4("8_3_4"),
  VERSION_8_3_5("8_3_5"),
  VERSION_8_3_6("8_3_6"),
  VERSION_8_3_7("8_3_7"),
  VERSION_8_3_8("8_3_8"),
  VERSION_8_3_9("8_3_9"),
  VERSION_8_3_10("8_3_10"),
  VERSION_8_3_11("8_3_11"),
  VERSION_8_3_12("8_3_12"),
  VERSION_8_3_13("8_3_13"),
  VERSION_8_3_14("8_3_14"),
  VERSION_8_3_15("8_3_15"),
  VERSION_8_3_16("8_3_16"),
  VERSION_8_3_17("8_3_17"),
  VERSION_8_3_18("8_3_18"),
  VERSION_8_3_19("8_3_19"),
  VERSION_8_3_20("8_3_20");

  @Getter
  private final String version;
  
  public static PlatformEdition latest() {
    return VERSION_8_3_20;
  }
}
