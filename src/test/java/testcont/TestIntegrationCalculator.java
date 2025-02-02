package testcont;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.d3d.Main;



@Testcontainers
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(initializers = { TestIntegrationCalculator.Initializer.class })
@SpringBootTest(classes = Main.class, properties ={"spring.flyway.cleanDisabled=false", "spring.flyway.schemas=public"})
@EnableAutoConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,FlywayTestExecutionListener.class,SqlScriptsTestExecutionListener.class})
public abstract class TestIntegrationCalculator {
    @Container
    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;

    static{
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:12");
        POSTGRE_SQL_CONTAINER.start();
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                    "spring.datasource.username=" + POSTGRE_SQL_CONTAINER.getUsername(),
                    "driver-class-name=org.postgresql.Driver",
                    "spring.datasource.password=" + POSTGRE_SQL_CONTAINER.getPassword())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
