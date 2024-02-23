package com.energy.solution.mvp.repositories;

import com.energy.solution.mvp.models.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
}
