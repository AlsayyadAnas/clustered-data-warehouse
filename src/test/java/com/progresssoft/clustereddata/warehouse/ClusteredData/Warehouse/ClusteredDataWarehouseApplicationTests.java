package com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.controller.DealsController;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.model.dto.DealDTO;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.repository.DealRepository;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = {"com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse"})
class ClusteredDataWarehouseApplicationTests {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DealService dealService;

    @Autowired
    private DealRepository dealRepository;

    @InjectMocks
    private DealsController dealsController;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testSaveDeal() throws Exception {

        Random random = new Random();
        int length = 7;

        String randomUnique = IntStream.range(1, length)
                .map(i -> random.nextInt(9))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());

        DealDTO dealDTO = DealDTO.builder()
                .dealAmount(new BigDecimal("500"))
                .dealDateTime(LocalDateTime.now())
                .dealUniqueId(randomUnique)
                .orderingFromCurrency("USD")
                .orderingToCurrency("JOD")
                .build();
        String json = objectMapper.writeValueAsString(dealDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/deals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(randomUnique, response);
    }

    @Test
    void testEmptyOrderAmount() throws Exception {
        DealDTO dealDTO = DealDTO.builder()
                .dealDateTime(LocalDateTime.now())
                .dealUniqueId("123456")
                .orderingFromCurrency("USD")
                .orderingToCurrency("JOD")
                .build();
        String json = objectMapper.writeValueAsString(dealDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/deals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        String errorMessage = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();

        assertTrue(errorMessage.contains("order amount is required"));
        assertEquals(status,400);

    }


    @Test
    void testInvalidCurrency() throws Exception {

        DealDTO dealDTO = DealDTO.builder()
                .dealDateTime(LocalDateTime.now())
                .dealAmount(new BigDecimal("500"))
                .dealUniqueId("123465")
                .orderingFromCurrency("123")
                .orderingToCurrency("JOD")
                .build();
        String json = objectMapper.writeValueAsString(dealDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/deals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        String errorMessage = mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();

        assertTrue(errorMessage.contains("The currency value not valid IOS code"));
        assertEquals(status,400);

    }
}
