package com.bnt.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bnt.model.Example;

@Repository
public class ExampleRepository{

   @Autowired
   DataSource dataSource;

    public ExampleRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Example saveData(Example example){
        try {
            /*
             * Connection is an interface. It establishes connection with the database where we have to give
             * url, username, password for establishing connection here datasource is doing that
             * getConnection() is method in DriverManager but here it is in DataSource.class
             */
            Connection connection = dataSource.getConnection();
            /*
             * Statement interface is a part of Connection interface. Statement, PreparedStatement, CallableStatement
             * Statement is used for executing simple SQL queries ans is static
             * PreparedStatement is used for executing parameterized queries and is more efficient.
             * CallableStatement is used for compile SQL queries.
             */
            PreparedStatement psmt = connection.prepareStatement("INSERT INTO testtable (id, name, mobile) VALUES (?, ?, ?)");
            psmt.setLong(1, example.getId());
            psmt.setString(2, example.getName());
            psmt.setString(3, example.getMobile());
            /*
             When you want to insert any data use executeUpdate and if you want to retrieve data use executeQuery 
             */
            psmt.executeUpdate();
            psmt.close();
            connection.close();
        } catch (Exception e) {
            /*
             * SQLException handles errors and exceptions related to database interactions. It allows for more 
             * accurate debugging and error resolution.
             */
        }
        return example;
    }

     public List<Example> getAll() {
        List<Example> dataList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM testtable");
            /*
             * ResultSet is a part of Statement interface.It is used to retrieve, manipulate the data from columns.
             * Whatever it is retrieving it is storing in ResultSet
             */
            ResultSet resultSet = statement.executeQuery();
            /*
             * next() method moves the cursor forward one row from its current position.
             */
            while (resultSet.next()) {
                Example test = new Example();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setMobile(resultSet.getString("mobile"));
                dataList.add(test);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    
 
    public Example updateName(Example example) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement psmt = connection.prepareStatement("UPDATE testtable SET name = ?, mobile = ? WHERE id = ?");
            psmt.setString(1, example.getName());
            psmt.setString(2, example.getMobile());
            psmt.setLong(3, example.getId());
            psmt.executeUpdate();
            psmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return example;
    }
 
    public void delete(int id){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM testtable WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
           
        }
    }

    public Example updateMobile(Long id, String mobile) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement psmt = connection.prepareStatement("UPDATE testtable SET mobile = ? WHERE id = ?");
            psmt.setString(1, mobile);
            psmt.setLong(2, id);
            psmt.executeUpdate();
            psmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Example(id, "", mobile); // return the updated example with only id and mobile set
    }




}
