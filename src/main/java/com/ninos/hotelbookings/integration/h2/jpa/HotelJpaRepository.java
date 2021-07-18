package com.ninos.hotelbookings.integration.h2.jpa;

import com.ninos.hotelbookings.integration.h2.jpa.entities.HotelJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelJpaRepository extends JpaRepository<HotelJpaEntity, Long> {
}
