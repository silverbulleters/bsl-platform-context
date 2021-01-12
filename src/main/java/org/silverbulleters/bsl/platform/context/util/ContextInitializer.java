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
import org.silverbulleters.bsl.platform.context.internal.ContentType;
import org.silverbulleters.bsl.platform.context.internal.EventData;
import org.silverbulleters.bsl.platform.context.internal.PlatformContextStorage;
import org.silverbulleters.bsl.platform.context.platform.Event;
import org.silverbulleters.bsl.platform.context.platform.PlatformContext;
import org.silverbulleters.bsl.platform.context.platform.PlatformEdition;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@UtilityClass
@Slf4j
public class ContextInitializer {

    // TODO подумать над параметром указание пути к внешним файлам
    private String resourceSrcDir = "";

    public static void setResourceSrcDir(String resourcePathDir) {
        ContextInitializer.resourceSrcDir = resourcePathDir;
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
        var pathToEventsFile = getPathFromResources(platformEdition.getVersion(), ContentType.EVENT);

        if (pathToEventsFile.isEmpty() || !Files.exists(pathToEventsFile.get())) {
            return Collections.emptyList();
        }

        ObjectMapper mapper = new ObjectMapper();
        EventData eventData;
        try {
            eventData = mapper.readValue(pathToEventsFile.get().toFile(), EventData.class);
        } catch (IOException e) {
            log.error("Can't parse JSON file: " + e.getMessage());
            return Collections.emptyList();
        }

        return eventData.getEvents() == null ? Collections.emptyList() : eventData.getEvents();
    }

    private Optional<Path> getPathFromResources(String version, ContentType type){
        if (!resourceSrcDir.isBlank()) {
            return Optional.of(Paths.get(resourceSrcDir,  version + "_" + type.getRepresentation() +  ".json"));
        }

        var typeDir = ContextInitializer.class.getClassLoader().getResource(type.getRepresentation());
        return Optional.ofNullable(typeDir).map(dir -> {
            try {
                return dir.toURI();
            } catch (URISyntaxException e) {
                log.error("Can't get resource file. " + e.getMessage());
                return null;
            }
        }).map(Paths::get);
    }
}
