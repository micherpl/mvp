package com.energy.solution.mvp.api;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceConfigurationDto {

    private String identifier;
    private String configuration;
}
