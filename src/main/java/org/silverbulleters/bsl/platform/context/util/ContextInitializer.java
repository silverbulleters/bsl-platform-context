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
package org.silverbulleters.bsl.platform.context.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.silverbulleters.bsl.platform.context.internal.PlatformContextStorage;
import org.silverbulleters.bsl.platform.context.internal.EventData;
import org.silverbulleters.bsl.platform.context.platform.Event;
import org.silverbulleters.bsl.platform.context.platform.PlatformContext;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@UtilityClass
@Slf4j
public class ContextInitializer {

    // TODO подумать над параметром указание пути к внешним файлам
    private String resourcePathDir;

    public static void setResourcePath(String resourcePathDir) {
        ContextInitializer.resourcePathDir = resourcePathDir;
    }

    public void initializeContext(PlatformContextStorage storage, List<PlatformEdition> editions) {
        for (PlatformEdition edition : editions) {
            loadContextByEdition(storage, edition);
        }
    }

    private void loadContextByEdition(PlatformContextStorage storage, PlatformEdition edition) {
        // TODO Читаем контекст из внешних файлов
        var platformContext = new PlatformContext();
        platformContext.setEvents(readEventsFromFile(edition));
        var contextMap = storage.getContextByEditions();
        contextMap.put(edition, platformContext);
    }

    private List<Event> readEventsFromFile(PlatformEdition platformEdition) {
        var pathToFile = Paths.get(resourcePathDir + platformEdition.getVersion() + "_events.json");

        if (!Files.exists(pathToFile)) {
            return Collections.emptyList();
        }

        ObjectMapper mapper = new ObjectMapper();
        EventData eventData;
        try {
            eventData = mapper.readValue(Paths.get(resourcePathDir + "8_3_10_events.json").toFile(), EventData.class);
        } catch (IOException e) {
            log.error("Can't parse JSON file: " + e.getMessage());
            return Collections.emptyList();
        }

        return eventData.getEvents() == null ? Collections.emptyList() : eventData.getEvents();
    }
}
