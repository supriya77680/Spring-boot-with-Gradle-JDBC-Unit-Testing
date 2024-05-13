package com.bnt.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bnt.model.Sample;
import com.bnt.repository.SampleRepository;

@ExtendWith(MockitoExtension.class)
class SampleRepositoryApplicationTests {

    @Mock
    private DataSource dataSource;

    @InjectMocks
    private SampleRepository sampleRepository;

    @BeforeAll
    public void setup() throws Exception {
        // Mocking behavior for getConnection() method of DataSource
        Connection connection = mock(Connection.class);
        when(dataSource.getConnection()).thenReturn(connection);
        
        // Mocking behavior for prepareStatement() method of Connection
        PreparedStatement psmt = mock(PreparedStatement.class);
        when(connection.prepareStatement("INSERT INTO testtable (id, name, mobile) VALUES (?, ?, ?)")).thenReturn(psmt);
        
        // Mocking behavior for executeUpdate() method of PreparedStatement
        when(psmt.executeUpdate()).thenReturn(1);
        
        // Mocking behavior for executeQuery() method of PreparedStatement
        ResultSet resultSet = mock(ResultSet.class);
        when(psmt.executeQuery()).thenReturn(resultSet);
        
        // Mocking behavior for resultSet.next() method
        when(resultSet.next()).thenReturn(true, false);
        
        // Mocking behavior for resultSet.getLong() and resultSet.getString() methods
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("Supriya");
        when(resultSet.getString("mobile")).thenReturn("7768083050");
    }

    @Test
    void testSaveData() {
        Sample sample = new Sample();
        sample.setId(1L);
        sample.setName("Supriya");
        sample.setMobile("7768083050");
        
        assertEquals(sample, sampleRepository.saveData(sample));
    }

    @Test
    void testGetAll() {
        List<Sample> expectedDataList = new ArrayList<>();
        Sample sample = new Sample();
        sample.setId(1L);
        sample.setName("Supriya");
        sample.setMobile("7768083050");
        expectedDataList.add(sample);
        
        assertEquals(expectedDataList, sampleRepository.getAll());
    }

    @Test
    void testUpdateName() {
        Sample sample = new Sample();
        sample.setId(1L);
        sample.setName("Supriya");
        sample.setMobile("7768083050");
        
        assertEquals(sample, sampleRepository.updateName(sample));
    }

    @Test
    void delete() {
        // Assuming deletion was successful
        sampleRepository.delete(1);
        // No assertions because the method is void
    }
}

