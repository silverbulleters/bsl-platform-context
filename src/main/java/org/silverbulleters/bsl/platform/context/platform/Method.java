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

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.silverbulleters.bsl.platform.context.internal.BaseMethod;
import org.silverbulleters.bsl.platform.context.internal.data.DataFromCollector;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Определение метода типа
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Method extends BaseMethod {
  /**
   * Признак, что метод является функцией
   */
  boolean isFunction;
  /**
   * Список доступных контекстов выполнения
   */
  List<ExecutionContext> executionContexts;

  public static Method createMethodFromData(DataFromCollector.Method methodFromData) {
    var resource = new Resource(methodFromData.getNameRu(), methodFromData.getName());
    var executionContexts = methodFromData.getExecutionContexts().stream()
      .map(ExecutionContext::valueByName)
      .collect(Collectors.toUnmodifiableList());
    return new Method(resource, methodFromData.getIsFunction(), executionContexts);
  }

  public Method(Resource name, boolean isFunction, List<ExecutionContext> executionContexts) {
    super(name);
    this.isFunction = isFunction;
    this.executionContexts = executionContexts;
  }
}
