package com.example.demo.controller;

import com.example.demo.model.BillService;
import com.example.demo.model.Image;
import com.example.demo.service.billservice.BillServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/billservices")
public class BillServiceController {
    @Autowired
    private BillServiceService billServiceService;

    @GetMapping
    public ResponseEntity<Iterable<BillService>> getAllBillService() {
        return new ResponseEntity<>(billServiceService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BillService> createNewBillService(@RequestBody BillService billService) {
        return new ResponseEntity<>(billServiceService.save(billService), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillService> getBillService(@PathVariable Long id) {
        Optional<BillService> billServiceOptional = billServiceService.findById(id);
        return billServiceOptional.map(billService -> new ResponseEntity<>(billService, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<BillService> updateBillService(@RequestBody BillService billService) {
        Optional<BillService> billServiceOptional = billServiceService.findById(billService.getId());
        return billServiceOptional.map(billService1 -> {
            billService1.setId(billService1.getId());
            billService1.setAmount(billService.getAmount());
            billService1.setBill(billService.getBill());
            billService1.setService(billService.getService());
            billService1.setStatus(billService.getStatus());
            return new ResponseEntity<>(billServiceService.save(billService1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<BillService> deleteBillService(@RequestBody BillService billService) {
        Optional<BillService> billServiceOptional = billServiceService.findById(billService.getId());
        return billServiceOptional.map(billService1 -> {
            billServiceService.remove(billService.getId());
            return new ResponseEntity<>(billService1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
