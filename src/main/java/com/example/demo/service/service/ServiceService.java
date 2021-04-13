package com.example.demo.service.service;

import com.example.demo.repository.IServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceService implements IServiceService {
    @Autowired
    private IServiceRepository serviceRepository;

    @Override
    public Iterable<com.example.demo.model.Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<com.example.demo.model.Service> findById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public com.example.demo.model.Service save(com.example.demo.model.Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void remove(Long id) {
        serviceRepository.deleteById(id);
    }
}
