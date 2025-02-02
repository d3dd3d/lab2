package testcont;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.sql.Timestamp;


public class TestHomeContoller extends TestIntegrationCalculator {
    @Autowired
    protected MockMvc mockMvc;

    // @MockBean
    // JdbcCaclculatorRepository jdbcCaclculatorRepository;

    // @Autowired
    // private WebApplicationContext webApplicationContext;

    // public void setUp(){
    //     mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    // }

    @org.junit.jupiter.api.Test
    @FlywayTest
    void getList() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/home/getList")
        .param("fromDate","2023-11-01")
        .param("toDate", "2025-11-01"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }

    @org.junit.jupiter.api.Test
    @FlywayTest
    void add() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/home/add")
        .param("firstOperand","5")
        .param("firstDigit","10")
        .param("secondOperand","5")
        .param("secondDigit", "10"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals("10", content);
        System.out.println(content);
    }

    @org.junit.jupiter.api.Test
    @FlywayTest
    void diff() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/home/diff")
        .param("firstOperand","5")
        .param("firstDigit","10")
        .param("secondOperand","5")
        .param("secondDigit", "10"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals("0", content);
        System.out.println(content);
    }

    @org.junit.jupiter.api.Test
    @FlywayTest
    void multiply() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/home/multiply")
        .param("firstOperand","5")
        .param("firstDigit","10")
        .param("secondOperand","5")
        .param("secondDigit", "10"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals("25", content);
        System.out.println(content);
    }

    @org.junit.jupiter.api.Test
    @FlywayTest
    void div() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/home/div")
        .param("firstOperand","5")
        .param("firstDigit","10")
        .param("secondOperand","5")
        .param("secondDigit", "10"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals("1", content);
        System.out.println(content);
    }

}
