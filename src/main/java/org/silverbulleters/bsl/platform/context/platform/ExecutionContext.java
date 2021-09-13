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
 * Контекст доступности "выполнения" для типов, методов, свойств и т.п.
 * Например {@code Мобильное приложение (клиент)} или {@code Толстый клиент}
 */
@RequiredArgsConstructor
public enum ExecutionContext {
  /**
   * Веб-клиент
   */
  WEB_CLIENT("WebClient"),
  /**
   * Внешнее соединение
   */
  EXTERNAL_CONNECTION("ExternalConnection"),
  /**
   * Интеграция
   */
  INTEGRATION("Integration"),
  /**
   * Мобильное приложение (клиент)
   */
  MOBILE_APPLICATION_CLIENT("MobileApplicationClient"),
  /**
   * Мобильное приложение (сервер)
   */
  MOBILE_APPLICATION_SERVER("MobileApplicationServer"),
  /**
   * Мобильный автономный сервер
   */
  MOBILE_STANDALONE_SERVER("MobileStandaloneServer"),
  /**
   * Мобильный клиент
   */
  MOBILE_CLIENT("MobileClient"),
  /**
   * Сервер
   */
  SERVER("Server"),
  /**
   * Толстый клиент
   */
  THICK_CLIENT("ThickClient"),
  /**
   * Тонкий клиент
   */
  THIN_CLIENT("ThinClient");

  @Accessors(fluent = true)
  @Getter
  private final String value;

  /**
   * Получение элемента {@code ExecutionContext} по его текстовому представлению.
   * Имя регистрозависимое
   *
   * @param name имя контекста выполнения в PascalCase. Например {@code ThickClient}
   * @return элемент {@code ExecutionContext} в случае успешного поиска. В противном случае выбрасывается {@code IllegalArgumentException}
   * @throws IllegalArgumentException если по переданному текстовому имени не найдено ни одного элемента
   */
  public static ExecutionContext valueByName(String name) {
    for (var value : ExecutionContext.values()) {
      if (value.value().equals(name)) {
        return value;
      }
    }
    throw new IllegalArgumentException();
  }
}
