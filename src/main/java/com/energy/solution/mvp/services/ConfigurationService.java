package com.energy.solution.mvp.services;

import com.energy.solution.mvp.api.DeviceConfigurationDto;
import com.energy.solution.mvp.api.GetResponseConfigurationDto;
import com.energy.solution.mvp.api.ResponseConfigurationDto;
import com.energy.solution.mvp.models.Configuration;
import com.energy.solution.mvp.repositories.ConfigurationRepository;
import com.energy.solution.mvp.utils.ResponseConfigurationCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    public Optional<GetResponseConfigurationDto> getConfiguration(Long configurationId) {
        Optional<Configuration> deviceConfiguration = configurationRepository.findById(configurationId);
        return deviceConfiguration.map(ResponseConfigurationCreator::toGetResponse);
    }

    public Optional<ResponseConfigurationDto> updateConfiguration(DeviceConfigurationDto deviceConfigurationDto,
                                                                  Long configurationId) {
        Optional<Configuration> deviceConfiguration = configurationRepository.findById(configurationId);
        return deviceConfiguration.map(devConf -> {
            devConf.setConfiguration(deviceConfigurationDto.getConfiguration());
            devConf.setModifiedAt(ZonedDateTime.now().toString());
            return configurationRepository.save(devConf);
        }).map(ResponseConfigurationCreator::toResponse);
    }

    public ResponseConfigurationDto createConfiguration(DeviceConfigurationDto deviceConfigurationDto) {
        Configuration configuration = new Configuration();
        configuration.setDeviceIdentifier(deviceConfigurationDto.getIdentifier());
        configuration.setConfiguration(deviceConfigurationDto.getConfiguration());
        configuration.setCreatedAt(ZonedDateTime.now().toString());
        configuration.setModifiedAt(ZonedDateTime.now().toString());
        return ResponseConfigurationCreator.toResponse(configurationRepository.save(configuration));
    }

    public void deleteConfiguration(Long configurationId) {
        configurationRepository.deleteById(configurationId);
    }
}
