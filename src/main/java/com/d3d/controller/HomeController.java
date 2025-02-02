package com.d3d.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d3d.data.CalculatorHistory;
import com.d3d.model.Calculator;
import com.d3d.model.Number.Number;
import com.d3d.repository.JdbcCaclculatorRepository;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {
    @Autowired
    JdbcCaclculatorRepository calcRepository;

    @GetMapping("/getList")
    public ResponseEntity<List<CalculatorHistory>> getList(@RequestParam String fromDate, @RequestParam String toDate) throws Exception{
        List<CalculatorHistory> history = new ArrayList<CalculatorHistory>();
        fromDate+=" 00:00:00";
        toDate+=" 00:00:00";
        try{
            calcRepository.findAll(Timestamp.valueOf(fromDate), Timestamp.valueOf(toDate)).forEach(history::add);;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(history.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(history,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestParam String firstOperand, @RequestParam String firstDigit,@RequestParam String secondOperand,@RequestParam String secondDigit) {
        try {
            Number num1 = Calculator.create(firstOperand, firstDigit);
            Number num2 = Calculator.create(secondOperand, secondDigit);
            int result = Calculator.add(num1, num2);
            CalculatorHistory calcHistory = new CalculatorHistory();
            calcHistory.setHistoryId(UUID.randomUUID());
            calcHistory.setFirstOperand(firstOperand);
            calcHistory.setFirstDigit(Integer.parseInt(firstDigit));
            calcHistory.setSecondOperand(secondOperand);
            calcHistory.setSecondDigit(Integer.parseInt(secondDigit));
            calcHistory.setDateHistory(new Timestamp(new Date().getTime()));
            calcHistory.setOperationName("+");
            calcRepository.save(calcHistory);
            return new ResponseEntity<>(String.valueOf(result),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/diff")
    public ResponseEntity<Integer> diff(@RequestParam String firstOperand, @RequestParam String firstDigit,@RequestParam String secondOperand,@RequestParam String secondDigit) {
        try {
            Number num1 = Calculator.create(firstOperand, firstDigit);
            Number num2 = Calculator.create(secondOperand, secondDigit);
            int result = Calculator.diff(num1, num2);
            CalculatorHistory calcHistory = new CalculatorHistory();
            calcHistory.setHistoryId(UUID.randomUUID());
            calcHistory.setFirstOperand(firstOperand);
            calcHistory.setFirstDigit(Integer.parseInt(firstDigit));
            calcHistory.setSecondOperand(secondOperand);
            calcHistory.setSecondDigit(Integer.parseInt(secondDigit));
            calcHistory.setDateHistory(new Timestamp(new Date().getTime()));
            calcHistory.setOperationName("-");
            calcRepository.save(calcHistory);
            return new ResponseEntity<>(result,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/multiply")
    public ResponseEntity<Integer> multiply(@RequestParam String firstOperand, @RequestParam String firstDigit,@RequestParam String secondOperand,@RequestParam String secondDigit) {
        try {
            Number num1 = Calculator.create(firstOperand, firstDigit);
            Number num2 = Calculator.create(secondOperand, secondDigit);
            int result = Calculator.multiply(num1, num2);
            CalculatorHistory calcHistory = new CalculatorHistory();
            calcHistory.setHistoryId(UUID.randomUUID());
            calcHistory.setFirstOperand(firstOperand);
            calcHistory.setFirstDigit(Integer.parseInt(firstDigit));
            calcHistory.setSecondOperand(secondOperand);
            calcHistory.setSecondDigit(Integer.parseInt(secondDigit));
            calcHistory.setDateHistory(new Timestamp(new Date().getTime()));
            calcHistory.setOperationName("*");
            calcRepository.save(calcHistory);
            return new ResponseEntity<>(result,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/div")
    public ResponseEntity<Integer> div(@RequestParam String firstOperand, @RequestParam String firstDigit,@RequestParam String secondOperand,@RequestParam String secondDigit) {
        try {
            Number num1 = Calculator.create(firstOperand, firstDigit);
            Number num2 = Calculator.create(secondOperand, secondDigit);
            int result = Calculator.divide(num1, num2);
            CalculatorHistory calcHistory = new CalculatorHistory();
            calcHistory.setHistoryId(UUID.randomUUID());
            calcHistory.setFirstOperand(firstOperand);
            calcHistory.setFirstDigit(Integer.parseInt(firstDigit));
            calcHistory.setSecondOperand(secondOperand);
            calcHistory.setSecondDigit(Integer.parseInt(secondDigit));
            calcHistory.setDateHistory(new Timestamp(new Date().getTime()));
            calcHistory.setOperationName("/");
            calcRepository.save(calcHistory);
            return new ResponseEntity<>(result,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
