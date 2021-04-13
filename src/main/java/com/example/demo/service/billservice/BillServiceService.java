package com.example.demo.service.billservice;

import com.example.demo.model.BillService;
import com.example.demo.repository.IBillServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillServiceService implements IBillServiceService {
    @Autowired
    private IBillServiceRepository billServiceRepository;

    @Override
    public Iterable<BillService> findAll() {
        return billServiceRepository.findAll();
    }

    @Override
    public Optional<BillService> findById(Long id) {
        return billServiceRepository.findById(id);
    }

    @Override
    public BillService save(BillService billService) {
        return billServiceRepository.save(billService);
    }

    @Override
    public void remove(Long id) {
        billServiceRepository.deleteById(id);
    }
}
