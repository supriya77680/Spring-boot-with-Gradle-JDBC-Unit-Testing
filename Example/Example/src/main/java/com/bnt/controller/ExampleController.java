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

import com.bnt.model.Example;
import com.bnt.service.ExampleService;

@RestController
public class ExampleController {

    @Autowired
    ExampleService exampleService;

     @GetMapping("/hello")
    public String getCheck(){
        return "Hello this is alive";
    }

    @PostMapping("/saveAll")    
    public Example saveData(@RequestBody Example example) {
        return exampleService.saveData(example);
    }

    @GetMapping("/getAll")
    public List<Example> getAll() {
        return exampleService.getAll();
    }

      @PutMapping("update-name/{id}")
    public Example updateName(@PathVariable Long id, @RequestParam("name") String name, @RequestParam("mobile") String mobile) {
        Example updateName = new Example(id, name, mobile);
        exampleService.updateName(updateName);
        return updateName;
    }

       @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable int id) {
        exampleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}