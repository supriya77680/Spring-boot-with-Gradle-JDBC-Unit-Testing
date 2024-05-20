package com.bnt.repositoryTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnt.model.Example;
import com.bnt.repository.ExampleRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RepositoryTest {

    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @InjectMocks
    private ExampleRepository exampleRepository;

   @BeforeEach
    void setUp() throws Exception {
        dataSource = mock(DataSource.class);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        // Pass mock DataSource to the ExampleRepository constructor
        exampleRepository = new ExampleRepository(dataSource);

        when(dataSource.getConnection()).thenReturn(connection);
    }

    @Test
    void testSaveData() throws Exception {
        // Mock behavior for PreparedStatement
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        // Creating a sample Example object
        Example example = new Example();
        example.setId(1L);
        example.setName("Supriya");
        example.setMobile("7768083050");

        // Invoking the method under test
        exampleRepository.saveData(example);

        // Verifying the method calls and parameters
        verify(preparedStatement).setLong(1, example.getId());
        verify(preparedStatement).setString(2, example.getName());
        verify(preparedStatement).setString(3, example.getMobile());
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close();
        verify(connection).close();
    }

    @Test
    void testGetAll() throws Exception {
        // Mock behavior for PreparedStatement
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        // Mock behavior for ResultSet
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getLong("id")).thenReturn(1L, 2L);
        when(resultSet.getString("name")).thenReturn("Test1", "Test2");
        when(resultSet.getString("mobile")).thenReturn("1234567890", "0987654321");

        // Invoking the method under test
        List<Example> resultList = exampleRepository.getAll();

        // Verifying the method calls and parameters
        verify(resultSet, times(3)).next();
        verify(resultSet, times(2)).getLong("id");
        verify(resultSet, times(2)).getString("name");
        verify(resultSet, times(2)).getString("mobile");
        verify(resultSet).close();
        verify(preparedStatement).close();
        verify(connection).close();

        // Verifying the returned list
        assertEquals(2, resultList.size());
        assertEquals(1L, resultList.get(0).getId());
        assertEquals("Test1", resultList.get(0).getName());
        assertEquals("1234567890", resultList.get(0).getMobile());
        assertEquals(2L, resultList.get(1).getId());
        assertEquals("Test2", resultList.get(1).getName());
        assertEquals("0987654321", resultList.get(1).getMobile());
    }

    @Test
    void testUpdateName() throws Exception {
        // Mock behavior for PreparedStatement
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        // Creating a sample Example object
        Example example = new Example();
        example.setId(1L);
        example.setName("UpdatedName");
        example.setMobile("UpdatedMobile");

        // Invoking the method under test
        exampleRepository.updateName(example);

        // Verifying the method calls and parameters
        verify(preparedStatement).setString(1, example.getName());
        verify(preparedStatement).setString(2, example.getMobile());
        verify(preparedStatement).setLong(3, example.getId());
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close();
        verify(connection).close();
    }

    @Test
    public void testDelete() throws Exception {
        int idToDelete = 1;
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        exampleRepository.delete(idToDelete);
        verify(connection).prepareStatement("DELETE FROM testtable WHERE id = ?");
        verify(preparedStatement).setInt(1, idToDelete);
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close();
        verify(connection).close();
    }

    }
    

    
    

