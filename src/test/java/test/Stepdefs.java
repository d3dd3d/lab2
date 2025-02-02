package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Assert;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.Delimiter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.d3d.Main;
import com.d3d.data.CalculatorHistory;
import com.d3d.repository.JdbcCaclculatorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.spring.CucumberContextConfiguration;
import testcont.TestIntegrationCalculator;

@CucumberContextConfiguration
@SpringBootTest(classes = Main.class, properties = {"spring.flyway.enabled=false"})
public class Stepdefs extends TestIntegrationCalculator {

    @Before
    public void prepData(){
        jdbcCaclculatorRepository.execute("CREATE TABLE IF NOT EXISTS public.history_calculator\r\n" + //
                        "(\r\n" + //
                        "    historyID uuid NOT NULL,\r\n" + //
                        "    firstOperand character varying COLLATE pg_catalog.\"default\",\r\n" + //
                        "    firstDigit character varying COLLATE pg_catalog.\"default\",\r\n" + //
                        "    secondOperand character varying COLLATE pg_catalog.\"default\",\r\n" + //
                        "    secondDigit character varying COLLATE pg_catalog.\"default\",\r\n" + //
                        "    dateHistory timestamp without time zone,\r\n" + //
                        "    operationName character varying COLLATE pg_catalog.\"default\",\r\n" + //
                        "    CONSTRAINT history_calculator_pkey PRIMARY KEY (historyID)\r\n" + //
                        ");"+ " INSERT INTO public.history_calculator VALUES \r\n" + //
                                                        "('20f79530-9e20-453d-a290-e0eba057b54a','80',10,'1',2, timestamp '2024-11-08 14:35:00','+')");
    }

    @After
    public void deleteData(){
        jdbcCaclculatorRepository.execute("DROP TABLE IF EXISTS public.history_calculator;");
    }

    @Autowired
    MockMvc mockMvc;

    InputData inputData;

    @Autowired
    JdbcCaclculatorRepository jdbcCaclculatorRepository;
    public String fromDate;
    public String toDate;
    MvcResult result;

    @Дано("^начальная дата (.+), и конечная дата (.+)$")
    public void начальнаяДатаИКонечнаяДата(String arg1, String arg2) throws Throwable {
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.fromDate=arg1;//new Timestamp(dateFormat.parse(arg1).getTime());
        this.toDate=arg2;//new Timestamp(dateFormat.parse(arg2).getTime());
    }

    @Дано("^числа и системы счисления$")
    public void числаИСистемыСчисления(io.cucumber.datatable.DataTable dataTable) {
        List<String> list = dataTable.asList();
        inputData=new InputData(list.get(0),list.get(1),list.get(2),list.get(3));
        assertNotEquals(inputData, null);
    }

    @Дано("числа и системы счисления {int} и {int} и {int} и {int}")
    public void числаИСистемыСчисления(Integer int1, Integer int2, Integer int3, Integer int4) {
        inputData=new InputData(Integer.toString(int1),Integer.toString(int2),Integer.toString(int3),Integer.toString(int4));
        System.out.println(inputData);
        assertNotEquals(inputData, null);
    }

    @ParameterType("(.+)")
    public List<String> delimetr(String line){
        List<String> list = new ArrayList<String>();
        String[] lines = line.split(" , ");
        for (String string : lines) {
            list.add(string);
        };
        return list;
    }

    @Дано("числа и системы счисления делиметр {delimetr}")
    public void числаИСистемыСчисленияДелиметр(List<String> arg) {
        inputData=new InputData(arg.get(0),arg.get(1),arg.get(2),arg.get(3));
        System.out.println(inputData);
        assertNotEquals(inputData, null);
    }

    @DataTableType
    public InputData inputDataEntry(Map<String,String> entry){
        return new InputData(
            entry.get("firstOperand"),
            entry.get("firstDigit"),
            entry.get("secondOperand"),
            entry.get("secondDigit")
        );
    }

    @Дано("числа и системы счисления класс")
    public void числаИСистемыСчисленияКласс(List<InputData> inputDatas) {
        inputData=inputDatas.get(0);
        System.out.println(inputData);
        assertNotEquals(inputData, null);
    }

    @Когда("^посылаем Post запрос методу add$")
    public void посылаемPostЗапросМетодуAdd() throws Throwable {
        result = mockMvc.perform(MockMvcRequestBuilders.post("/home/add")
        .param("firstOperand",inputData.getFirstOperand())
        .param("firstDigit",inputData.getFirstDigit())
        .param("secondOperand",inputData.getSecondOperand())
        .param("secondDigit", inputData.getSecondDigit()))
        .andReturn();
    }
    

    @Когда("^посылаем Post запрос методу diff$")
    public void посылаемPostЗапросМетодуDiff() throws Throwable {
        result = mockMvc.perform(MockMvcRequestBuilders.post("/home/diff")
        .param("firstOperand",inputData.getFirstOperand())
        .param("firstDigit",inputData.getFirstDigit())
        .param("secondOperand",inputData.getSecondOperand())
        .param("secondDigit", inputData.getSecondDigit()))
        .andReturn();
    }

    @Когда("^посылаем Post запрос методу multiply$")
    public void посылаемPostЗапросМетодуMultiply() throws Throwable {
        result = mockMvc.perform(MockMvcRequestBuilders.post("/home/multiply")
        .param("firstOperand",inputData.getFirstOperand())
        .param("firstDigit",inputData.getFirstDigit())
        .param("secondOperand",inputData.getSecondOperand())
        .param("secondDigit", inputData.getSecondDigit()))
        .andReturn();
    }

    @Когда("^посылаем Post запрос методу divide$")
    public void посылаемPostЗапросМетодуDivide() throws Throwable{
        result = mockMvc.perform(MockMvcRequestBuilders.post("/home/div")
        .param("firstOperand",inputData.getFirstOperand())
        .param("firstDigit",inputData.getFirstDigit())
        .param("secondOperand",inputData.getSecondOperand())
        .param("secondDigit", inputData.getSecondDigit()))
        .andReturn();
    }

    @Когда("^отправляем get запрос серверу$")
    public void отправляемGetЗапросСерверу() throws Throwable {
        result = mockMvc.perform(MockMvcRequestBuilders.get("/home/getList")
        .param("fromDate",fromDate.toString())
        .param("toDate", toDate.toString()))
        // .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
    }

    @Тогда("^получаем список операций$")
    public void получаемСписокОпераций() throws Throwable {
        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        CalculatorHistory[] calculatorHistory = objectMapper.readValue(content, CalculatorHistory[].class);
        assertEquals(calculatorHistory[0].getHistoryId(), UUID.fromString("20f79530-9e20-453d-a290-e0eba057b54a"));
    }

    @Тогда("^получаем ошибку (\\d+)$")
    public void получаемОшибку(int arg1) throws Throwable {
        assertEquals(204,arg1);
    }

    @Тогда("получаем число-результат в {int} системе счисления")
    public void получаемЧислоРезультатВСистемеСчисления(Integer int1) throws Throwable {
        String content = result.getResponse().getContentAsString();
        assertEquals("2" , content);
    }

    @Тогда("получаем ошбику {int}")
    public void получаемОшбику(Integer int1) throws Throwable {
        assertEquals(result.getResponse().getStatus(), 400);
    }
}
