package com.energy.solution.mvp.utils;

import com.energy.solution.mvp.api.GetResponseConfigurationDto;
import com.energy.solution.mvp.api.ResponseConfigurationDto;
import com.energy.solution.mvp.models.Device;
import com.energy.solution.mvp.models.Configuration;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ResponseConfigurationCreatorTest {

    @Test
    public void testToGetResponse() {
        // given
        String expectedConfiguration = "testConfiguration";
        String createdAtString = "2024-02-24T00:00:00+01:00[Europe/Paris]";
        ZonedDateTime expectedCreatedAt = ZonedDateTime.parse(createdAtString);

        Configuration mockConfiguration = new Configuration();
        mockConfiguration.setConfiguration(expectedConfiguration);
        mockConfiguration.setCreatedAt(createdAtString);

        // when
        GetResponseConfigurationDto result = ResponseConfigurationCreator.toGetResponse(mockConfiguration);

        // then
        assertEquals(expectedConfiguration, result.getConfiguration());
        assertEquals(expectedCreatedAt, result.getCreatedAt());
    }

    @Test
    public void testToResponse() {
        // given
        String expectedDeviceIdentifier = "device123";
        String expectedConfiguration = "testConfiguration";
        String createdAtString = "2024-02-24T00:00:00+01:00[Europe/Paris]";
        String modifiedAtString = "2024-02-24T00:00:00+01:00[Europe/Paris]";


        Configuration mockConfiguration = new Configuration();
        mockConfiguration.setDeviceIdentifier(expectedDeviceIdentifier);
        mockConfiguration.setConfiguration(expectedConfiguration);
        mockConfiguration.setCreatedAt(createdAtString);
        mockConfiguration.setModifiedAt(modifiedAtString);

        // when
        ResponseConfigurationDto result = ResponseConfigurationCreator.toResponse(mockConfiguration);

        // then
        assertEquals(expectedDeviceIdentifier, result.getDeviceId());
        assertEquals(expectedConfiguration, result.getConfiguration());
        assertEquals(createdAtString, result.getCreatedAt());
        assertEquals(modifiedAtString, result.getModifiedAt());
    }
}