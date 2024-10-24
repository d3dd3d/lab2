import org.flywaydb.test.FlywayTestExecutionListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;



@Testcontainers
@SpringBootTest
@ActiveProfiles("test-containers")
@ContextConfiguration(initializers = { TestIntegrationCalculator.Initializer.class })
@EnableAutoConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,FlywayTestExecutionListener.class,SqlScriptsTestExecutionListener.class})
public abstract class TestIntegrationCalculator {
    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;

    static{
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:12");
        POSTGRE_SQL_CONTAINER.start();
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "database.cache.url=" + POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                    "database.cache.username=" + POSTGRE_SQL_CONTAINER.getUsername(),
                    "database.cache.password=" + POSTGRE_SQL_CONTAINER.getPassword())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    // public CalculatorHistory newCalcHistory(String firstOperand, int firstDigit, String secondOperand, int secondDigit,
    //         Timestamp dateHistory, String operationName) {
    //     CalculatorHistory calculatorHistory = new CalculatorHistory();
    //     calculatorHistory.setHistoryId(UUID.randomUUID());
    //     calculatorHistory.setFirstOperand(firstOperand);
    //     calculatorHistory.setFirstDigit(firstDigit);
    //     calculatorHistory.setSecondOperand(secondOperand);
    //     calculatorHistory.setSecondDigit(secondDigit);
    //     calculatorHistory.setDateHistory(dateHistory);
    //     calculatorHistory.setOperationName(operationName);
    //     return calculatorHistory;
    // }

    // public void insertIntoDB(){
        
    //     save(newCalcHistory("3", 10, "5", 8, new Timestamp(new Date().getTime()),"+"));
    //     save(newCalcHistory("5", 8, "5", 8, new Timestamp(new Date().getTime()),"-"));
    //     save(newCalcHistory("48", 10, "5", 8, new Timestamp(new Date().getTime()),"*"));
    //     save(newCalcHistory("110", 2, "5", 8, new Timestamp(new Date().getTime()),"/"));
    //     save(newCalcHistory("1A", 16, "5", 8, new Timestamp(new Date().getTime()),"+"));
    //     save(newCalcHistory("5", 8, "5", 8, new Timestamp(new Date().getTime()),"-"));
    //     save(newCalcHistory("10", 2, "5", 8, new Timestamp(new Date().getTime()),"*"));
    //     save(newCalcHistory("1111", 2, "5", 8, new Timestamp(new Date().getTime()),"/"));
    //     save(newCalcHistory("89", 16, "5", 8, new Timestamp(new Date().getTime()),"+"));
    //     save(newCalcHistory("3", 10, "5", 8, new Timestamp(new Date().getTime()),"-"));
    //     save(newCalcHistory("7", 8, "5", 8, new Timestamp(new Date().getTime()),"*"));
    //     save(newCalcHistory("8", 10, "5", 8, new Timestamp(new Date().getTime()),"/"));
    //     save(newCalcHistory("9", 10, "5", 8, new Timestamp(new Date().getTime()),"+"));
    //     save(newCalcHistory("10", 2, "5", 8, new Timestamp(new Date().getTime()),"-"));
    //     save(newCalcHistory("20", 16, "5", 8, new Timestamp(new Date().getTime()),"*"));
    //     save(newCalcHistory("10", 16, "5", 8, new Timestamp(new Date().getTime()),"/"));
    //     save(newCalcHistory("30", 10, "5", 8, new Timestamp(new Date().getTime()),"+"));
    //     save(newCalcHistory("50", 10, "5", 8, new Timestamp(new Date().getTime()),"-"));
    //     save(newCalcHistory("6", 8, "5", 8, new Timestamp(new Date().getTime()),"*"));
    //     save(newCalcHistory("67", 16, "5", 8, new Timestamp(new Date().getTime()),"/"));
    // }

}
