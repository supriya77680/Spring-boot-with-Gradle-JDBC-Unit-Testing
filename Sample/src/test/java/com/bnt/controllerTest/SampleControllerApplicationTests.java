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


import com.bnt.controller.SampleController;
import com.bnt.model.Sample;
import com.bnt.repository.SampleRepository;
import com.bnt.service.SampleService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SampleControllerApplicationTests {

	//create mocks that are needed to support testing of class to be tested
	@Mock
	SampleRepository sampleRepository;

	@Mock
	SampleService sampleService;

	//create class instances that need to be tested in the test class
	@InjectMocks
	SampleController sampleController;

	@Test
	void contextLoads() {
	}

	@Test

	void getCheckTest(){
		String expectedResult = "Hello this is alive";
        String actualResult =  sampleController.getCheck();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void saveDataTest(){
		Sample expectedResult = new Sample(1L, "Supriya", "7768083050");
		when(sampleService.saveData(expectedResult)).thenReturn(expectedResult);
		Sample actualResult = sampleController.saveData(expectedResult);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void getAllTest(){
		List<Sample> expectedResult = new ArrayList<>();
		expectedResult.add(new Sample(1L, "Jayesh", "987654321"));
		expectedResult.add(new Sample(2L, "Naushad", "8907654321"));
		when(sampleService.getAll()).thenReturn(expectedResult);
		List<Sample> actualResult = sampleController.getAll();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void updateNameTest(){
		Sample expectedResult = new Sample();
		expectedResult.setId(1L);
		expectedResult.setName("Sup");
		expectedResult.setMobile("7768083050");
		when(sampleController.updateName(1L, "Sup", "7768083050")).thenReturn(expectedResult);
        Sample actualResult = sampleController.updateName(1L, "Sup", "7768083050");
        assertEquals(expectedResult, actualResult);
	}

// 	@Test
//     void updateNameTest() {
//     TestDemo expectedResult = new Sample(1L, "Sup", "7768083050");
//     sampleController.updateName(1L, "Sup", "7768083050");
//     verify(sampleService).updateName(expectedResult);
// }


	// @Test
	// void deleteDataTest(){
	// 	ResponseEntity<Void> expectedResult = sampleController.deleteData(1);
	// 	when(sampleService.delete(1)).thenReturn(expectedResult);
	// 	assertEquals(expectedResult, expectedResult);
	// }

	@Test
	void deleteDataTest(){
		ResponseEntity<Void> expectedResult = sampleController.deleteData(1);
		verify(sampleService).delete(1);
		assertEquals(HttpStatus.NO_CONTENT, expectedResult.getStatusCode());
		
	}


}
