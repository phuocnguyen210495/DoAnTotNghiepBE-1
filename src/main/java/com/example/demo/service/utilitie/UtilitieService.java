package com.example.demo.service.utilitie;

import com.example.demo.model.Utilitie;
import com.example.demo.repository.IUtilitieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilitieService implements IUtilitieService {
    @Autowired
    private IUtilitieRepository utilitieRepository;

    @Override
    public Iterable<Utilitie> findAll() {
        return utilitieRepository.findAll();
    }

    @Override
    public Optional<Utilitie> findById(Long id) {
        return utilitieRepository.findById(id);
    }

    @Override
    public Utilitie save(Utilitie utilitie) {
        return utilitieRepository.save(utilitie);
    }

    @Override
    public void remove(Long id) {
        utilitieRepository.deleteById(id);
    }
}
