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

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * Вспомогательный класс для загрузки данных о типах из внешнего файла json
 */
@Data
public class DataFromCollector {
  /**
   * Версия платформы. Например, `8_3_10`
   */
  private String platformVersion = "";
  /**
   * Список типов платформы
   */
  private List<Type> types = Collections.emptyList();
  /**
   * Список событий типов платформы
   */
  private List<Event> events = Collections.emptyList();

  /**
   * Представление типа
   */
  @Data
  public static class Type {
    /**
     * Идентификатор типа
     */
    private String id = "";
    /**
     * Имя типа на русском
     */
    private String name = "";
    /**
     * Имя типа на английском
     */
    private String nameRu = "";

    /**
     * Вид типа
     */
    private String kind = "";

    /**
     * Методы типа платформы
     */
    private List<Method> methods = Collections.emptyList();

    /**
     * Свойства типа платформы
     */
    private List<Property> properties = Collections.emptyList();

    /**
     * Значения типа платформы
     */
    private List<TypeValue> values = Collections.emptyList();

    /**
     * Тип исключен из глобального контекста
     */
    private boolean excludeFromGlobalContext = false;
  }

  /**
   * Представление события
   */
  @Data
  public static class Event {
    /**
     * Имя события на русском
     */
    private String name = "";
    /**
     * Имя события на английском
     */
    private String nameRu = "";
    /**
     * Список типов платформы, у которых есть текущее событие
     */
    private List<String> types = Collections.emptyList();
  }

  /**
   * Представление метода
   */
  @Data
  public static class Method {

    /**
     * Имя метода на английском
     */
    private String name = "";

    /**
     * Имя метода на русском
     */
    private String nameRu = "";

    /**
     * Признак, является ли метод функцией
     */
    private Boolean isFunction = Boolean.FALSE;

    /**
     * Контексты выполнения
     */
    private List<String> executionContexts = Collections.emptyList();

  }

  /**
   * Представление свойства типа
   */
  @Data
  public static class Property {

    /**
     * Имя свойства на английском
     */
    private String name = "";

    /**
     * Имя свойства на русском
     */
    private String nameRu = "";

    /**
     * Режим использования (строкой)
     */
    private String usage = "";
  }

  /**
   * Представление значения типа
   */
  @Data
  public static class TypeValue {
    /**
     * Имя свойства на английском
     */
    private String name = "";

    /**
     * Имя свойства на русском
     */
    private String nameRu = "";
  }
}

