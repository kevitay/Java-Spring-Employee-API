package com.galvanize.springwebdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class DefaultEndpointTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testIndexEndPointNotFound() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void mathPiRouteShouldReturnPi() throws Exception {
        mockMvc.perform(get("/math/pi"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void mathFibonacciShouldReturnFibonacciSequenceTo34() throws Exception {
        mockMvc.perform(post("/math/fibonacci"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1, 1, 2, 3, 5, 8, 13, 21, 34"));
    }

    @Test
    public void controllerReturnsEmployeeWithId3() throws Exception {
        mockMvc.perform(get("api/employee/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.firstName").value("Third"))
                .andExpect(jsonPath("$.lastName").value("Employee"));
    }

}
