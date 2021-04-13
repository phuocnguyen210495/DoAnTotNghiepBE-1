package com.example.demo.controller;

import com.example.demo.model.HouseDay;
import com.example.demo.service.houseday.IHouseDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin("*")
public class HouseDayController {
    @Autowired
    private IHouseDayService houseDayService;

    @RequestMapping(value = "/houseDays", method = RequestMethod.GET)
    public ResponseEntity<Iterable<HouseDay>> listAllHouseDay() {
        Iterable<HouseDay> houseDays = houseDayService.findAll();
        if (houseDays == null) {
            return new ResponseEntity<Iterable<HouseDay>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<HouseDay>>(houseDays, HttpStatus.OK);
    }

    @RequestMapping(value = "/houseDays/{id}", method = RequestMethod.GET)
    public ResponseEntity<HouseDay> getHouseDay(@PathVariable("id") Date id) {
        HouseDay houseDay = houseDayService.findById(id);
        if (houseDay == null) {
            return new ResponseEntity<HouseDay>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<HouseDay>(houseDay, HttpStatus.OK);
    }

    @RequestMapping(value = "/houseDays", method = RequestMethod.POST)
    public ResponseEntity<Void> createHouseDay(@RequestBody HouseDay houseDay) {
        houseDayService.save(houseDay);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/houseDays", method = RequestMethod.PUT)
    public ResponseEntity<HouseDay> updateHouseDay(@RequestBody HouseDay houseDay) {
        HouseDay houseDay1 = houseDayService.findById(houseDay.getDate());
        if (houseDay1 == null) {
            return new ResponseEntity<HouseDay>(HttpStatus.NOT_FOUND);
        }
        houseDay1.setHouse(houseDay.getHouse());
        houseDay1.setStatus(houseDay.getStatus());
        houseDayService.save(houseDay1);
        return new ResponseEntity<HouseDay>(HttpStatus.OK);
    }

    @RequestMapping(value = "/houseDays", method = RequestMethod.DELETE)
    public ResponseEntity<HouseDay> deleteHouseDay(@RequestBody HouseDay houseDay) {
        HouseDay houseDay1 = houseDayService.findById(houseDay.getDate());
        if (houseDay1 == null) {
            return new ResponseEntity<HouseDay>(HttpStatus.NOT_FOUND);
        }
        houseDayService.remove(houseDay.getDate());
        return new ResponseEntity<HouseDay>(HttpStatus.OK);
    }
}
