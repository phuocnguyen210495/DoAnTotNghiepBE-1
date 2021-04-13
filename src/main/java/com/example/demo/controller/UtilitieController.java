package com.example.demo.controller;

import com.example.demo.model.Utilitie;
import com.example.demo.service.utilitie.IUtilitieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/utilities")
public class UtilitieController {
    @Autowired
    private IUtilitieService utilitieService;

    @GetMapping
    public ResponseEntity<Iterable<Utilitie>> getAllNotification() {
        return new ResponseEntity<>(utilitieService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Utilitie> createNewUtilitie(@RequestBody Utilitie utilitie) {
        return new ResponseEntity<>(utilitieService.save(utilitie), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilitie> getUtilitie(@PathVariable Long id) {
        Optional<Utilitie> utilitieOptional = utilitieService.findById(id);
        return utilitieOptional.map(utilitie -> new ResponseEntity<>(utilitie, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Utilitie> updateUtilitie(@RequestBody Utilitie utilitie) {
        Optional<Utilitie> utilitieOptional = utilitieService.findById(utilitie.getId());
        return utilitieOptional.map(utilitie1 -> {
            utilitie1.setId(utilitie1.getId());
            utilitie1.setName(utilitie.getName());
            utilitie1.setDescription(utilitie1.getDescription());
            utilitie1.setStatus(utilitie.getStatus());
            return new ResponseEntity<>(utilitieService.save(utilitie1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<Utilitie> deleteUtilitie(@RequestBody Utilitie utilitie) {
        Optional<Utilitie> utilitieOptional = utilitieService.findById(utilitie.getId());
        return utilitieOptional.map(utilitie1 -> {
            utilitieService.remove(utilitie.getId());
            return new ResponseEntity<>(utilitie1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
