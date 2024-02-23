package com.energy.solution.mvp.utils;

import com.energy.solution.mvp.api.GetResponseConfigurationDto;
import com.energy.solution.mvp.api.ResponseConfigurationDto;
import com.energy.solution.mvp.models.Configuration;

import java.time.ZonedDateTime;

public class ResponseConfigurationCreator {

    public static GetResponseConfigurationDto toGetResponse(Configuration configuration) {
        return new GetResponseConfigurationDto(configuration.getConfiguration(),
                ZonedDateTime.parse(configuration.getCreatedAt()));
    }

    public static ResponseConfigurationDto toResponse(Configuration configuration) {
        return new ResponseConfigurationDto(
                configuration.getDeviceIdentifier(),
                configuration.getConfiguration(),
                configuration.getCreatedAt(),
                configuration.getModifiedAt());
    }
}
