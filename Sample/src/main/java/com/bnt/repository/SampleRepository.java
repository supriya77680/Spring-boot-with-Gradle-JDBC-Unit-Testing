package com.bnt.repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bnt.model.Sample;


 @Repository
public class SampleRepository{

   @Autowired
   DataSource dataSource;

    public Sample saveData(Sample sample){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement psmt = connection.prepareStatement("INSERT INTO testtable (id, name, mobile) VALUES (?, ?, ?)");
            psmt.setLong(1, sample.getId());
            psmt.setString(2, sample.getName());
            psmt.setString(3, sample.getMobile());
            psmt.executeUpdate();
            psmt.close();
            connection.close();
        } catch (Exception e) {
        }
        return sample;
    }

     public List<Sample> getAll() {
        List<Sample> dataList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM testtable");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sample test = new Sample();
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

 
    public Sample updateName(Sample sample) {
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement psmt = con.prepareStatement("UPDATE testtable SET name = ?, mobile = ? WHERE id = ?");
            psmt.setString(1, sample.getName());
            psmt.setString(2, sample.getMobile());
            psmt.setLong(3, sample.getId());
            psmt.executeUpdate();
            psmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sample;
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