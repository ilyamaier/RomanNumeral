package com.example.romannumeral;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testConvertToOne() throws Exception {
        mockMvc.perform(get("/romannumeral?query=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"input\":\"1\",\"output\":\"I\"}")));

        mockMvc.perform(get("/romannumeral?query=3999"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"input\":\"3999\",\"output\":\"MMMCMXCIX\"}")));
    }

    @Test
    void testConvertToOneExceptions() throws Exception {
        mockMvc.perform(get("/romannumeral?query="))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given input is null (query=).")));

        mockMvc.perform(get("/romannumeral?query=string"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given input isn't of type Integer (query=string).")));

        mockMvc.perform(get("/romannumeral?query=2.5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given input isn't of type Integer (query=2.5).")));

        mockMvc.perform(get("/romannumeral?query=0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given input isn't in range 1-3999 (query=0).")));

        mockMvc.perform(get("/romannumeral?query=4000"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given input isn't in range 1-3999 (query=4000).")));
    }

    @Test
    void testConvertRange() throws Exception {
        mockMvc.perform(get("/romannumeral?min=1&max=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"conversions\":[{\"input\":\"1\",\"output\":\"I\"},{\"input\":\"2\",\"output\":\"II\"}]}")));

        mockMvc.perform(get("/romannumeral?min=2567&max=2570"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"conversions\":[{\"input\":\"2567\",\"output\":\"MMDLXVII\"},{\"input\":\"2568\",\"output\":\"MMDLXVIII\"},{\"input\":\"2569\",\"output\":\"MMDLXIX\"},{\"input\":\"2570\",\"output\":\"MMDLXX\"}]}")));
    }

    @Test
    void testConvertRangeExceptions() throws Exception {
        mockMvc.perform(get("/romannumeral?min=1&max="))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given input is null (min=1, max=).")));

        mockMvc.perform(get("/romannumeral?min=1&max=string"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given input isn't of type Integer (min=1, max=string).")));

        mockMvc.perform(get("/romannumeral?min=1.5&max=2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given input isn't of type Integer (min=1.5, max=2).")));

        mockMvc.perform(get("/romannumeral?min=1&max=1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given min is greater than or equal to max (min=1, max=1).")));

        mockMvc.perform(get("/romannumeral?min=0&max=2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given input isn't in range 1-3999 (min=0, max=2).")));

        mockMvc.perform(get("/romannumeral?min=1&max=4000"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given input isn't in range 1-3999 (min=1, max=4000).")));

        mockMvc.perform(get("/romannumeral?min=4001&max=4002"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Given input isn't in range 1-3999 (min=4001, max=4002).")));
    }

}
