import org.flywaydb.test.FlywayTestExecutionListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

import com.d3d.Main;



@Testcontainers
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = Main.class, properties = {"spring.flyway.cleanDisabled=false", "spring.flyway.schemas=public"})
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
                    "CONTAINER.URL=" + POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                    "CONTAINER.USERNAME=" + POSTGRE_SQL_CONTAINER.getUsername(),
                    "CONTAINER.PASSWORD=" + POSTGRE_SQL_CONTAINER.getPassword())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
