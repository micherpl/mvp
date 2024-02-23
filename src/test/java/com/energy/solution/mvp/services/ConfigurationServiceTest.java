package com.energy.solution.mvp.services;

import com.energy.solution.mvp.api.DeviceConfigurationDto;
import com.energy.solution.mvp.api.GetResponseConfigurationDto;
import com.energy.solution.mvp.api.ResponseConfigurationDto;
import com.energy.solution.mvp.models.Configuration;
import com.energy.solution.mvp.repositories.ConfigurationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfigurationServiceTest {

    @Mock
    private ConfigurationRepository configurationRepository;

    @InjectMocks
    private ConfigurationService configurationService;

    @Test
    public void testGetConfiguration() {
        // given
        Long configurationId =  1L;
        Configuration mockConfiguration = new Configuration();
        mockConfiguration.setConfiguration("testConfiguration");
        mockConfiguration.setCreatedAt("2024-02-24T00:00:30+01:00[Europe/Paris]");

        when(configurationRepository.findById(configurationId)).thenReturn(Optional.of(mockConfiguration));

        // when
        Optional<GetResponseConfigurationDto> result = configurationService.getConfiguration(configurationId);

        // then
        assertEquals("testConfiguration", result.get().getConfiguration());
        assertEquals("2024-02-24T00:00:30+01:00[Europe/Paris]", result.get().getCreatedAt().toString());
    }

    @Test
    public void testUpdateConfiguration() {
        // given
        Long configurationId =  1L;
        DeviceConfigurationDto deviceConfigurationDto = new DeviceConfigurationDto("d1", "updatedConfiguration");

        Configuration mockConfiguration = new Configuration();
        mockConfiguration.setConfiguration("testConfiguration");

        when(configurationRepository.findById(configurationId)).thenReturn(Optional.of(mockConfiguration));
        when(configurationRepository.save(any(Configuration.class))).thenReturn(mockConfiguration);

        // when
        Optional<ResponseConfigurationDto> result = configurationService.updateConfiguration(deviceConfigurationDto, configurationId);

        // then
        assertEquals("updatedConfiguration", result.get().getConfiguration());
    }

    @Test
    public void testCreateConfiguration() {
        // given
        DeviceConfigurationDto deviceConfigurationDto = new DeviceConfigurationDto("d1", "newConfiguration");

        Configuration mockConfiguration = new Configuration();
        mockConfiguration.setConfiguration("newConfiguration");
        mockConfiguration.setCreatedAt("2024-02-24T00:00:00+01:00[Europe/Paris]");

        when(configurationRepository.save(any(Configuration.class))).thenReturn(mockConfiguration);

        ConfigurationService configurationService = new ConfigurationService(configurationRepository);

        // when
        ResponseConfigurationDto result = configurationService.createConfiguration(deviceConfigurationDto);

        // then
        assertEquals("newConfiguration", result.getConfiguration());
    }

    @Test
    public void testDeleteConfiguration() {
        // given
        Long configurationId =  1L;

        // when
        configurationService.deleteConfiguration(configurationId);

        // then
        verify(configurationRepository).deleteById(configurationId);
    }

}