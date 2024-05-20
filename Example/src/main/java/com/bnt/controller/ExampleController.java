package com.bnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

      @PutMapping("/update-name/{id}")
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

    @PatchMapping("/update-mobile/{id}")
    public ResponseEntity<Example> updateMobile(@PathVariable Long id, @RequestParam("mobile") String mobile) {
        Example updatedExample = exampleService.updateMobile(id, mobile);
        return new ResponseEntity<>(updatedExample, HttpStatus.OK);
    }
    
}


    /*
     * PUT: It's like taking a blank form and completely filling it out again. You provide a complete 
     * representation of the resource, and the server replaces the existing resource with the new
     *  representation. If the resource doesn't exist, it might create it.
     *
     * PATCH: It's like taking that same form but only updating the fields that have changed. 
     * You provide partial information, and the server applies those changes to the existing resource.
     * It's suitable for updating specific attributes or properties without affecting the entire resource.
     */
    

