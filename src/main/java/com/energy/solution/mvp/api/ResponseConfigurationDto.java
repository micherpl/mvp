package com.energy.solution.mvp.api;

import com.energy.solution.mvp.models.Device;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ResponseConfigurationDto {

    private String deviceId;
    private String configuration;
    private String createdAt;
    private String modifiedAt;
}
