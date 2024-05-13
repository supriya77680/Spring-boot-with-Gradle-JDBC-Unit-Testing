package com.bnt.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.model.Sample;
import com.bnt.repository.SampleRepository;
import com.bnt.repository.SampleRepository;

@Service
public class SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    public Sample saveData(Sample sample) {
        return sampleRepository.saveData(sample);
    }

     public List<Sample> getAll() {
        return sampleRepository.getAll();
    }

    public Sample updateName(Sample sample){
        return sampleRepository.updateName(sample);
    }

    public void delete(int id) {
        sampleRepository.delete(id);
    }
}
