package com.bnt.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnt.model.Example;
import com.bnt.repository.ExampleRepository;
import com.bnt.service.ExampleService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    ExampleRepository exampleRepository;

    @InjectMocks
    ExampleService exampleService;

  @Test
  void testSaveData(){
    Example expectedResult = new Example(1L, "Supriya", "7768083050");
    when(exampleRepository.saveData(expectedResult)).thenReturn(expectedResult);
		Example actualResult = exampleService.saveData(expectedResult);
		assertEquals(expectedResult, actualResult);
	
  }

   @Test
	void getAllTest(){
		List<Example> expectedResult = new ArrayList<>();
		expectedResult.add(new Example(1L, "Jayesh", "987654321"));
		expectedResult.add(new Example(2L, "Naushad", "8907654321"));
		when(exampleRepository.getAll()).thenReturn(expectedResult);
		List<Example> actualResult = exampleService.getAll();
		assertEquals(expectedResult, actualResult);
	}

    @Test
	void updateNameTest(){
		Example expectedResult = new Example(1L, "Sup", "7768083050");
		when(exampleService.updateName(expectedResult)).thenReturn(expectedResult);
        Example actualResult = exampleRepository.updateName(expectedResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deletecustomerTest(){
        int id=1;
        exampleService.delete(id);
         verify(exampleRepository, times(1)).delete(id);
    }
        
}
