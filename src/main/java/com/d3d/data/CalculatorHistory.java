package com.d3d.data;

import java.sql.Timestamp;
import java.util.UUID;

public class CalculatorHistory {
    private UUID historyId;
    private String firstOperand;
    private int firstDigit;
    private String secondOperand;
    private int secondDigit;
    private Timestamp dateHistory;
    private String operationName;
    public CalculatorHistory() {
    }
    public UUID getHistoryId() {
        return historyId;
    }
    public void setHistoryId(UUID historyId) {
        this.historyId = historyId;
    }
    public String getFirstOperand() {
        return firstOperand;
    }
    public void setFirstOperand(String firstOperand) {
        this.firstOperand = firstOperand;
    }
    public int getFirstDigit() {
        return firstDigit;
    }
    public void setFirstDigit(int firstDigit) {
        this.firstDigit = firstDigit;
    }
    public String getSecondOperand() {
        return secondOperand;
    }
    public void setSecondOperand(String secondOperand) {
        this.secondOperand = secondOperand;
    }
    public int getSecondDigit() {
        return secondDigit;
    }
    public void setSecondDigit(int secondDigit) {
        this.secondDigit = secondDigit;
    }
    public Timestamp getDateHistory() {
        return dateHistory;
    }
    public void setDateHistory(Timestamp dateHistory) {
        this.dateHistory = dateHistory;
    }
    public String getOperationName() {
        return operationName;
    }
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
