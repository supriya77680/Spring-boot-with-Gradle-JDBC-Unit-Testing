package com.bnt.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.model.Sample;
import com.bnt.service.SampleService;

@RestController
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/hello")
    public String getCheck(){
        return "Hello this is alive";
    }

    @PostMapping("/saveAll")    
    public Sample saveData(@RequestBody Sample sample) {
        return sampleService.saveData(sample);
    }

    @GetMapping("/getAll")
    public List<Sample> getAll() {
        return sampleService.getAll();
    }

      @PutMapping("update-name/{id}")
    public Sample updateName(@PathVariable Long id, @RequestParam("name") String name, @RequestParam("mobile") String mobile) {
        Sample updateName = new Sample(id, name, mobile);
        sampleService.updateName(updateName);
        return updateName;
    }

       @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable int id) {
        sampleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
