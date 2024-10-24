package com.d3d.repository;

import java.sql.Timestamp;
import java.util.List;

import com.d3d.data.CalculatorHistory;

public interface CalculatorRepository {
    int save(CalculatorHistory calcHistory);
    List<CalculatorHistory> findAll(Timestamp fromDate, Timestamp toDate) throws Exception;
}
