package com.example.demo.service.houseday;

import com.example.demo.model.HouseDay;
import com.example.demo.repository.IHouseDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class HouseDayService implements IHouseDayService {
    @Autowired
    private IHouseDayRepository dateRepository;

    @Override
    public HouseDay findById(Date date) {
        return dateRepository.findById(date).get();
    }

    @Override
    public Iterable<HouseDay> findAll() {
        return dateRepository.findAll();
    }

    @Override
    public HouseDay save(HouseDay houseDay) {
        return dateRepository.save(houseDay);
    }

    @Override
    public void remove(Date date) {
        dateRepository.deleteById(date);
    }

    @Override
    public Optional<HouseDay> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {
    }
}
