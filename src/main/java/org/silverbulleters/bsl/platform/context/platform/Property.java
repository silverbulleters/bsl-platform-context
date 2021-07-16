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
import org.silverbulleters.bsl.platform.context.internal.data.DataFromCollector;
import org.silverbulleters.bsl.platform.context.types.ContextItem;
import org.silverbulleters.bsl.platform.context.types.Resource;

@Value
@EqualsAndHashCode
@ToString
public class Property implements ContextItem {
  Resource name;
  UsageMode usageMode;

  public static Property createPropertyFromData(DataFromCollector.Property propertyFromData) {
    var resource = new Resource(propertyFromData.getNameRu(), propertyFromData.getName());
    return new Property(resource, UsageMode.valueByName(propertyFromData.getUsage()));
  }
}
