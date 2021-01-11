package org.silverbulleters.bsl.platform.context.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Resource {
    String nameRu;
    String nameEn;

    public Resource(@JsonProperty("nameRu") String nameRu,
                    @JsonProperty("nameEn") String nameEn) {
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }
}
