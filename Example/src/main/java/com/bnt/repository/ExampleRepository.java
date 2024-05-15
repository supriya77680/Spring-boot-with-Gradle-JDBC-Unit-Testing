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

   @Autowired
    public ExampleRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Example saveData(Example example){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement psmt = connection.prepareStatement("INSERT INTO testtable (id, name, mobile) VALUES (?, ?, ?)");
            psmt.setLong(1, example.getId());
            psmt.setString(2, example.getName());
            psmt.setString(3, example.getMobile());
            psmt.executeUpdate();
            psmt.close();
            connection.close();
        } catch (Exception e) {
        }
        return example;
    }

     public List<Example> getAll() {
        List<Example> dataList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM testtable");
            ResultSet resultSet = statement.executeQuery();
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
            Connection con = dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement("UPDATE testtable SET name = ?, mobile = ? WHERE id = ?");
            psmt.setString(1, example.getName());
            psmt.setString(2, example.getMobile());
            psmt.setLong(3, example.getId());
            psmt.executeUpdate();
            psmt.close();
            con.close();
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
        } catch (Exception e) {
           
        }
    }


}
