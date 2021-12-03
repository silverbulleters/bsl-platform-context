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
package org.silverbulleters.bsl.platform.context.internal.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;

import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательный класс для чтение глобальных методов
 */
@Data
public class DataGlobalMethodsCollector {
  /**
   * Список глобальных методов
   */
  private List<GlobalMethodInfo> globalMethods = new ArrayList<>();

  /**
   * Описание глобального метода
   */
  @Data
  @EqualsAndHashCode(of = "name")
  public static class GlobalMethodInfo {
    /**
     * Имя метода на английском
     */
    @JsonAlias("Name")
    private String name = "";

    /**
     * Имя метода на русском
     */
    @JsonAlias("NameRu")
    private String nameRu = "";

    /**
     * Список версий платформы, в которых доступен глобальный метод
     */
    private List<PlatformEdition> platformVersions = new ArrayList<>();
  }
}
