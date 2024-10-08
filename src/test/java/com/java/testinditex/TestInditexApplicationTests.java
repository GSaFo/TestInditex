package com.java.testinditex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/schema.sql", "/data.sql"})
class TestInditexApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private Integer brandId = 1;
    private Integer productId = 35455;
    private String endpoint = "/prices/getPrice";
    private String param1Name = "date";
    private String param2Name = "brandId";
    private String param3Name = "productId";

    @Test
    public void test1() throws Exception {
        String date = "2020-06-14-10.00.00";
        mockMvc.perform(get(endpoint).param(param1Name, date).param(param2Name, brandId.toString()).param(param3Name, productId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14-00.00.00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31-23.59.59"))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void test2() throws Exception {
        String date = "2020-06-14-16.00.00";
        mockMvc.perform(get(endpoint).param(param1Name, date).param(param2Name, brandId.toString()).param(param3Name, productId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14-15.00.00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-14-18.30.00"))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    public void test3() throws Exception {
        String date = "2020-06-14-21.00.00";
        mockMvc.perform(get(endpoint).param(param1Name, date).param(param2Name, brandId.toString()).param(param3Name, productId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14-00.00.00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31-23.59.59"))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void test4() throws Exception {
        String date = "2020-06-15-10.00.00";
        mockMvc.perform(get(endpoint).param(param1Name, date).param(param2Name, brandId.toString()).param(param3Name, productId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15-00.00.00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-15-11.00.00"))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    public void test5() throws Exception {
        String date = "2020-06-16-21.00.00";
        mockMvc.perform(get(endpoint).param(param1Name, date).param(param2Name, brandId.toString()).param(param3Name, productId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15-16.00.00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31-23.59.59"))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.price").value(38.95));
    }

    /**
     * Test añadido para comprobar el caso de que no encuentre ningun price
     *
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        String date = "2023-06-13-21.00.00";
        mockMvc.perform(get(endpoint).param(param1Name, date).param(param2Name, brandId.toString()).param(param3Name, productId.toString()))
                .andExpect(status().isNoContent());
    }

    /**
     * Test añadido para comprobar el caso de parametros incorrectos
     *
     * @throws Exception
     */
    @Test
    public void test7() throws Exception {
        String date = "test";
        mockMvc.perform(get(endpoint).param(param1Name, date).param(param2Name, brandId.toString()).param(param3Name, productId.toString()))
                .andExpect(status().isNotAcceptable());
    }


    /**
     * Test añadido para comprobar el caso de falta de parametros
     *
     * @throws Exception
     */
    @Test
    public void test8() throws Exception {
        String date = "test";
        mockMvc.perform(get(endpoint).param(param1Name, date).param(param2Name, brandId.toString()).param(param3Name, productId.toString()))
                .andExpect(status().isNotAcceptable());
    }
}
