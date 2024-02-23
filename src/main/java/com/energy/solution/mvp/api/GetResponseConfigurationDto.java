package com.energy.solution.mvp.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class GetResponseConfigurationDto {

    @JsonProperty("konfiguracja")
    private String configuration;

    @JsonProperty("data utworzenia")
    @JsonFormat(pattern="yyyy-MM-dd")
    private ZonedDateTime createdAt;
}
