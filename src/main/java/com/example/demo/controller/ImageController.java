package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<Iterable<Image>> getAllImage() {
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Image> createNewImage(@RequestBody Image image) {
        return new ResponseEntity<>(imageService.save(image), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        return imageOptional.map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Image> updateImage(@RequestBody Image image) {
        Optional<Image> imageOptional = imageService.findById(image.getId());
        return imageOptional.map(image1 -> {
            image1.setId(image1.getId());
            image1.setLink(image.getLink());
            image1.setStatus(image.getStatus());
            image1.setHouse(image.getHouse());
            return new ResponseEntity<>(imageService.save(image1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<Image> deleteImage(@RequestBody Image image) {
        Optional<Image> categoryOptional = imageService.findById(image.getId());
        return categoryOptional.map(image1 -> {
            imageService.remove(image.getId());
            return new ResponseEntity<>(image1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
