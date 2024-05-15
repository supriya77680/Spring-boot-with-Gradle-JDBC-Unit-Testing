package com.bnt.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bnt.controller.ExampleController;
import com.bnt.model.Example;
import com.bnt.repository.ExampleRepository;
import com.bnt.service.ExampleService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ControllerTest {

    @Mock
    ExampleRepository exampleRepository;

    @Mock
    ExampleService exampleService;

    @InjectMocks
    ExampleController exampleController;

    @Test
    void getCheckTest(){
		String expectedResult = "Hello this is alive";
        String actualResult =  exampleController.getCheck();
		assertEquals(expectedResult, actualResult);
	}

    @Test
	void saveDataTest(){
		Example expectedResult = new Example(1L, "Supriya", "7768083050");
		when(exampleService.saveData(expectedResult)).thenReturn(expectedResult);
		Example actualResult = exampleController.saveData(expectedResult);
		assertEquals(expectedResult, actualResult);
	}

    @Test
	void getAllTest(){
		List<Example> expectedResult = new ArrayList<>();
		expectedResult.add(new Example(1L, "Jayesh", "987654321"));
		expectedResult.add(new Example(2L, "Naushad", "8907654321"));
		when(exampleService.getAll()).thenReturn(expectedResult);
		List<Example> actualResult = exampleController.getAll();
		assertEquals(expectedResult, actualResult);
	}

    @Test
	void updateNameTest(){
		Example expectedResult = new Example();
		expectedResult.setId(1L);
		expectedResult.setName("Sup");
		expectedResult.setMobile("7768083050");
		when(exampleController.updateName(1L, "Sup", "7768083050")).thenReturn(expectedResult);
        Example actualResult = exampleController.updateName(1L, "Sup", "7768083050");
        assertEquals(expectedResult, actualResult);
	}

    @Test
	void deleteDataTest(){
		ResponseEntity<Void> expectedResult = exampleController.deleteData(1);
		verify(exampleService).delete(1);
		assertEquals(HttpStatus.NO_CONTENT, expectedResult.getStatusCode());
		
	}
    
    
}
