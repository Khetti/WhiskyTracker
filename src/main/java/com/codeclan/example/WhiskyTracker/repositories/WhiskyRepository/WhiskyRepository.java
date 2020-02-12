package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhiskyRepository extends JpaRepository<Whisky, Long>, WhiskyRepositoryCustom {

    List<Whisky> findByYear(int year);
    List<Whisky> findByDistilleryIdAndAge(Long id, int age);
    List<Whisky> findByDistilleryRegionIgnoreCase(String region);
}
