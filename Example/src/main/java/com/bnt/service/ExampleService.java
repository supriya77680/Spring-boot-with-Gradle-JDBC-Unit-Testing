package com.bnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.model.Example;
import com.bnt.repository.ExampleRepository;

@Service
public class ExampleService {
    
    @Autowired
    ExampleRepository exampleRepository;

      public Example saveData(Example example) {
        return exampleRepository.saveData(example);
    }

     public List<Example> getAll() {
        return exampleRepository.getAll();
    }

    public Example updateName(Example example){
        return exampleRepository.updateName(example);
    }

    public void delete(int id) {
        exampleRepository.delete(id);
    }

    public Example updateMobile(Long id, String mobile) {
        return exampleRepository.updateMobile(id, mobile);
    }


}
