package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WhiskyController {

    @Autowired
    private DistilleryRepository distilleryRepository;

    @Autowired
    private WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies() {
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity<Optional<Whisky>> getWhisky(@PathVariable Long id) {
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/year")
    public ResponseEntity<List<Whisky>> getWhiskyByYear(@RequestParam(name="year") int year) {
        List<Whisky> whiskies = whiskyRepository.findByYear(year);
        return new ResponseEntity<>(whiskies, HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/region")
    public ResponseEntity<List<Whisky>> getWhiskyByRegion(@RequestParam(name="region") String region) {
        List<Whisky> whiskies = whiskyRepository.findByDistilleryRegionIgnoreCase(region);
        return new ResponseEntity<>(whiskies, HttpStatus.OK);
    }

}
