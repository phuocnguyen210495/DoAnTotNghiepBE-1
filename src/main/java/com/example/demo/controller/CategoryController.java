package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

//    @Autowired
//    private IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategory() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createNewCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        return categoryOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.findById(category.getId());
        return categoryOptional.map(category1 -> {
            category1.setId(category1.getId());
            category1.setName(category.getName());
            category1.setStatus(category.getStatus());
            return new ResponseEntity<>(categoryService.save(category1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<Category> deleteCategory(@RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.findById(category.getId());
        return categoryOptional.map(category1 -> {
            categoryService.remove(category.getId());
            return new ResponseEntity<>(category, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @GetMapping("/{id}/products")
//    public ResponseEntity<Iterable<Product>> findAllProductByCategory(@PathVariable Long id) {
//        Optional<Category> categoryOptional = categoryService.findById(id);
//        return categoryOptional.map(category -> new ResponseEntity<>(productService.findAllByCategory(category),
//                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
}
