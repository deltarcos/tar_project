package com.cpe.backend.repository;

import com.cpe.backend.entity.Duration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DurationRepository extends JpaRepository<Duration, Long> {
	Duration findById(long id);
}