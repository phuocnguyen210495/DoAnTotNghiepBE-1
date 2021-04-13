package com.example.demo.controller;

import com.example.demo.model.Service;
import com.example.demo.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public ResponseEntity<Iterable<Service>> getAllService() {
        return new ResponseEntity<>(serviceService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Service> createNewService(@RequestBody Service service) {
        return new ResponseEntity<>(serviceService.save(service), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getService(@PathVariable Long id) {
        Optional<Service> service = serviceService.findById(id);
        return service.map(service1 -> new ResponseEntity<>(service1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Service> updateService(@RequestBody Service service) {
        Optional<Service> serviceOptional = serviceService.findById(service.getId());
        return serviceOptional.map(service1 -> {
            service1.setId(service1.getId());
            service1.setName(service.getName());
            service1.setPrice(service.getPrice());
            service1.setStatus(service.getStatus());
            service1.setTimes(service.getTimes());
            return new ResponseEntity<>(serviceService.save(service1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<Service> deleteService(@RequestBody Service service) {
        Optional<Service> serviceOptional = serviceService.findById(service.getId());
        return serviceOptional.map(service1 -> {
            serviceService.remove(service.getId());
            return new ResponseEntity<>(service1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
