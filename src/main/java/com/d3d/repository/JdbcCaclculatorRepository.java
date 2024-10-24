package com.d3d.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.d3d.data.CalculatorHistory;

@Repository
public class JdbcCaclculatorRepository implements CalculatorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(CalculatorHistory calcHistory){
        try {
            jdbcTemplate.update("INSERT INTO history_calculator values (?,?,?,?,?,?,?)", 
        new Object[]{ calcHistory.getHistoryId(), calcHistory.getFirstOperand(), calcHistory.getFirstDigit(),
            calcHistory.getSecondOperand(), calcHistory.getSecondDigit(),calcHistory.getDateHistory(),
            calcHistory.getOperationName()});
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public List<CalculatorHistory> findAll(Timestamp fromDate, Timestamp toDate) throws Exception{
        String query = String.format("SELECT * FROM history_calculator where dateHistory>=timestamp '%s' and dateHistory<=timestamp'%s'", fromDate,toDate);
        try{
            return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(CalculatorHistory.class));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw new Exception();
        }
    }

}
