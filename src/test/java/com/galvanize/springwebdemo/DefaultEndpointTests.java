package com.galvanize.springwebdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
        mockMvc.perform(get("/api/employee/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.firstName").value("Third"))
                .andExpect(jsonPath("$.lastName").value("Employee"));
    }

    @Test
    public void employeeWithFirstNameAndLastNameParametersReturnsEmployeeWithinArray() throws Exception {
        mockMvc.perform(get("/api/employee/search")
                .param("firstName", "Fifth")
                .param("lastName", "Employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(5))
                .andExpect(jsonPath("$[0].firstName").value("Fifth"))
                .andExpect(jsonPath("$[0].lastName").value("Employee"));
    }

    @Test
    public void employeeAddSavesNewEmployee() throws Exception {
        String json = "{ \"id\": \"6\", \"firstName\": \"Sixth\", \"lastName\": \"Employee\" }";
        mockMvc.perform(post("/api/employee")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(6))
                    .andExpect(jsonPath("$.firstName").value("Sixth"))
                    .andExpect(jsonPath("$.lastName").value("Employee"));
    }

}
