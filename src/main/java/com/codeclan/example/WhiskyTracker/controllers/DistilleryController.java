package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
public class DistilleryController {

    @Autowired
    private DistilleryRepository distilleryRepository;

    @Autowired
    private WhiskyRepository whiskyRepository;

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleries() {
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity<Optional<Distillery>> getDistillery(@PathVariable Long id) {
        return new ResponseEntity<>(distilleryRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/region")
    public ResponseEntity<List<Distillery>> getDistilleriesByRegion(@RequestParam(name="region") String region) {
        List<Distillery> distilleries = distilleryRepository.findByRegionIgnoreCase(region);
        return new ResponseEntity<>(distilleries, HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/{id}/whiskies")
    public ResponseEntity<List<Whisky>> getDistilleryWhiskiesByAge(@PathVariable Long id, @RequestParam(name="aged") int age) {
        List<Whisky> whiskies = whiskyRepository.findByDistilleryIdAndAge(id, age);
        return new ResponseEntity<>(whiskies, HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/whiskies")
    public ResponseEntity<List<Distillery>> getDistilleriesByWhiskyAge(@RequestParam(name="age") int age) {
        List<Distillery> distilleries = distilleryRepository.findByWhiskiesAge(age);
        return new ResponseEntity<>(distilleries, HttpStatus.OK);
    }

}
