package org.silverbulleters.bsl.platform.context.internal;

import lombok.Getter;

public enum ContentType {
    EVENT("events"), GLOBAL_METHOD("global_methods"), TYPE_METHODS("type_methods");

    @Getter
    private final String representation;

    ContentType(String representation) {
        this.representation = representation;
    }
}
