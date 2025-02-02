package testcont;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;



@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "test",
    tags = "@getList",
    snippets = SnippetType.CAMELCASE,
    plugin = {"pretty"}
)
public class UserStoriesTest {

}
