import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.d3d.data.CalculatorHistory;
import com.d3d.repository.JdbcCaclculatorRepository;

import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.sql.Timestamp;


public class TestHomeContoller extends TestIntegrationCalculator {

    @Test
    public void migrate(){

    }
    
    @Autowired
    private JdbcCaclculatorRepository jdbcCaclculatorRepository;

    public CalculatorHistory newCalcHistory(String firstOperand, int firstDigit, String secondOperand, int secondDigit,
            Timestamp dateHistory, String operationName) {
        CalculatorHistory calculatorHistory = new CalculatorHistory();
        calculatorHistory.setHistoryId(UUID.randomUUID());
        calculatorHistory.setFirstOperand(firstOperand);
        calculatorHistory.setFirstDigit(firstDigit);
        calculatorHistory.setSecondOperand(secondOperand);
        calculatorHistory.setSecondDigit(secondDigit);
        calculatorHistory.setDateHistory(dateHistory);
        calculatorHistory.setOperationName(operationName);
        return calculatorHistory;
    }

    @FlywayTest(locationsForMigrate = "dbtest/repository")
    @Test
    public void findAllTest(){
        try {
            List<CalculatorHistory> result = jdbcCaclculatorRepository.findAll(new Timestamp(new Date().getTime()-100000), new Timestamp(new Date().getTime()+100000));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(false,true);
        }
        
    }



    // @Test
    // public void add(){
    //     // Scanner scan = new Scanner(System.in);
    //     // scan.nextLine();
    //     try{
    //         jdbcCaclculatorRepository.save(newCalcHistory("3", 10, "5", 8, new Timestamp(new Date().getTime()),"+"));
    //         jdbcCaclculatorRepository.findAll(new Timestamp(new Date().getTime()-100000), new Timestamp(new Date().getTime()+100000));
    //     }
    //     catch(Exception e){
    //         System.out.println(e.getMessage());
    //         Assert.assertEquals(false,true);
    //     }
        
       
    // }

}
