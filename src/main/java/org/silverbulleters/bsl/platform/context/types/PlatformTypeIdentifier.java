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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Востребованные идентификаторы типов
 */
@AllArgsConstructor
public enum PlatformTypeIdentifier {
  GLOBAL_CONTEXT("GLOBAL_CONTEXT"),
  BOT_MODULE("BOT_MODULE"),
  INTEGRATION_SERVICE_MODULE("INTEGRATION_SERVICE_MODULE"),
  COMMAND_MODULE("COMMAND_MODULE"),
  COMMON_MODULE("COMMON_MODULE"),
  CATALOG_OBJECT("CATALOG_OBJECT"),
  CHART_OF_ACCOUNTS_OBJECT("CHART_OF_ACCOUNTS_OBJECT"),
  EXCHANGE_PLAN_OBJECT("EXCHANGE_PLAN_OBJECT"),
  CHART_OF_CHARACTERISTIC_TYPES_OBJECT("CHART_OF_CHARACTERISTIC_TYPES_OBJECT"),
  CHART_OF_CALCULATION_TYPES_OBJECT("CHART_OF_CALCULATION_TYPES_OBJECT"),
  REPORT_OBJECT("REPORT_OBJECT"),
  DATA_PROCESSOR_OBJECT("DATA_PROCESSOR_OBJECT"),
  TASK_OBJECT("TASK_OBJECT"),
  DOCUMENT_OBJECT("DOCUMENT_OBJECT"),
  EXTERNAL_DATA_SOURCE_TABLE_OBJECT("EXTERNAL_DATA_SOURCE_TABLE_OBJECT"),
  BUSINESS_PROCESS_OBJECT("BUSINESS_PROCESS_OBJECT"),
  CATALOG_MANAGER("CATALOG_MANAGER"),
  INFORMATION_REGISTER_MANAGER("INFORMATION_REGISTER_MANAGER"),
  CALCULATION_REGISTER_MANAGER("CALCULATION_REGISTER_MANAGER"),
  ACCUMULATION_REGISTER_MANAGER("ACCUMULATION_REGISTER_MANAGER"),
  ACCOUNTING_REGISTER_MANAGER("ACCOUNTING_REGISTER_MANAGER"),
  CHART_OF_ACCOUNTS_MANAGER("CHART_OF_ACCOUNTS_MANAGER"),
  EXCHANGE_PLAN_MANAGER("EXCHANGE_PLAN_MANAGER"),
  CHART_OF_CHARACTERISTIC_TYPES_MANAGER("CHART_OF_CHARACTERISTIC_TYPES_MANAGER"),
  CHART_OF_CALCULATION_TYPES_MANAGER("CHART_OF_CALCULATION_TYPES_MANAGER"),
  ENUM_MANAGER("ENUM_MANAGER"),
  REPORT_MANAGER("REPORT_MANAGER"),
  DATA_PROCESSOR_MANAGER("DATA_PROCESSOR_MANAGER"),
  CONSTANT_MANAGER("CONSTANT_MANAGER"),
  TASK_MANAGER("TASK_MANAGER"),
  DOCUMENT_JOURNAL_MANAGER("DOCUMENT_JOURNAL_MANAGER"),
  DOCUMENT_MANAGER("DOCUMENT_MANAGER"),
  EXTERNAL_DATA_SOURCE_TABLE_MANAGER("EXTERNAL_DATA_SOURCE_TABLE_MANAGER"),
  EXTERNAL_DATA_SOURCE_CUBE_DIMENSION_TABLE_MANAGER("EXTERNAL_DATA_SOURCE_CUBE_DIMENSION_TABLE_MANAGER"),
  EXTERNAL_DATA_SOURCE_CUBE_MANAGER("EXTERNAL_DATA_SOURCE_CUBE_MANAGER"),
  BUSINESS_PROCESS_MANAGER("BUSINESS_PROCESS_MANAGER"),
  INFORMATION_REGISTER_RECORD_SET("INFORMATION_REGISTER_RECORD_SET"),
  CALCULATION_REGISTER_RECORD_SET("CALCULATION_REGISTER_RECORD_SET"),
  ACCUMULATION_REGISTER_RECORD_SET("ACCUMULATION_REGISTER_RECORD_SET"),
  ACCOUNTING_REGISTER_RECORD_SET("ACCOUNTING_REGISTER_RECORD_SET"),
  SEQUENCE_RECORD_SET("SEQUENCE_RECORD_SET"),
  RECALCULATION_RECORD_SET("RECALCULATION_RECORD_SET"),
  EXTERNAL_DATA_SOURCE_TABLE_RECORD_SET("EXTERNAL_DATA_SOURCE_TABLE_RECORD_SET"),
  CONSTANT_VALUE_MANAGER("CONSTANT_VALUE_MANAGER"),
  HTTP_SERVICE_MODULE("HTTP_SERVICE_MODULE"),
  WEB_SERVICE_MODULE("WEB_SERVICE_MODULE"),
  UNKNOWN("");

  @Accessors(fluent = true)
  @Getter
  private final String value;

}
